package cn.me.fdfs.service.impl;

import cn.me.fdfs.service.BaseService;
import cn.me.fdfs.service.MonitorService;
import cn.me.fdfs.util.JsshProxy;
import cn.me.fdfs.util.Tools;
import cn.me.fdfs.vo.*;
import cn.me.fdfs.vo.StorageHour;
import com.jcraft.jsch.JSchException;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: devuser Date: 12-8-20 Time: 下午9:19 To
 * change this template use File | Settings | File Templates.
 */
@Service
public class MonitorServiceImpl extends BaseService implements MonitorService {

    private static final Logger logger = LoggerFactory
            .getLogger(MonitorServiceImpl.class);

    @Override
    public List<Group> listGroupInfo() throws IOException, MyException,JSchException {
        List<Group> result = new ArrayList<Group>();
        // noinspection ConstantConditions
        ClientGlobal.init(Tools.getClassPath() + "fdfs_client.conf");
        logger.info("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
        logger.info("charset=" + ClientGlobal.g_charset);
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getConnection();
        if (trackerServer == null) {
            return result;
        }
        StructGroupStat[] groupStats = tracker.listGroups(trackerServer);
        if (groupStats == null) {
            logger.error("ERROR! list groups error, error no: "
                    + tracker.getErrorCode());
            return result;
        }
        logger.info("group count: " + groupStats.length);
        Date date = new Date();

        for (StructGroupStat groupStat : groupStats) {
            Group group = new Group();
            BeanUtils.copyProperties(groupStat, group);
            StructStorageStat[] storageStats = tracker.listStorages(
                    trackerServer, groupStat.getGroupName());
            for (StructStorageStat storageStat : storageStats) {
                Storage storage = new Storage();
                BeanUtils.copyProperties(storageStat, storage);
                storage.setCreated(date);
                storage.setCurStatus(ProtoCommon
                        .getStorageStatusCaption(storageStat.getStatus()));
                group.getStorageList().add(storage);
            }
            result.add(group);
        }

        String cmd = "ps -aux|grep fdfs";
        for (Machine machine : Tools.machines) {
            List<String> strList = new ArrayList<String>();
            if(machine.isConfigType())
                strList = Tools.exeRemoteConsole(machine.getIp(),
                        machine.getUsername(), machine.getPassword(), cmd);
            else
                strList = new JsshProxy(machine.getIp(),machine.getUsername(),machine.getPort(),machine.getSsh()).execute(cmd).getExecuteLines();
            for (String str : strList) {
                if (str.contains("storage.conf")) {
                    for (Group group : result) {
                        group.setCreated(date);
                        for (Storage storage : group.getStorageList()) {
                            if (machine.getIp().equalsIgnoreCase(
                                    storage.getIpAddr())) {
                                String[] strArrray = str.replaceAll(" +", ",")
                                        .split(",");
                                storage.setCpu(strArrray[2]);
                                storage.setMem(Float.parseFloat(strArrray[3]));


                            }

                        }
                    }
                }
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Group> listGroups() throws IOException, MyException {
        List<Group> groups = new ArrayList<Group>();
        Session session = getSession();

        StringBuilder queryString = new StringBuilder(
                "from Group as g GROUP BY groupName");
        Query query = session.createQuery(queryString.toString());

        groups = query.list();

        return groups;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Storage> listStorage(String groupName) throws IOException,
            MyException {

        Session session = getSession();

        StringBuilder queryString = new StringBuilder(
                "from Storage as s where  s.groupName='" + groupName
                        + "' group by s.ipAddr");
        Query query = session.createQuery(queryString.toString());

        return query.list();

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Storage> listStorageTop(String ipaddr) throws IOException,
            MyException {
        Session session = getSession();

        StringBuilder queryString = new StringBuilder(
                "from Storage as s where  s.ipAddr='" + ipaddr
                        + "' order by s.created desc");
        Query query = session.createQuery(queryString.toString());
        query.setMaxResults(10);
        return query.list();

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Line> getNetTrafficLines(String ip, String start, String end) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<Line> lines = new ArrayList<Line>();
        Session session = getSession();
        Date starttime, endtime;
        String entity = "Storage";
        try {
            starttime = sdf.parse(start);
            endtime = sdf.parse(end);
            long second = endtime.getTime() - starttime.getTime();
            if (second > 3600 * 1000 * 5 && second < 3600 * 1000 * 24 * 7) {
                entity = "StorageHour";
            } else if (second >= 3600 * 1000 * 24 * 7) {
                entity = "StorageDay";
            }
            logger.info(second + "");
        } catch (Exception e) {
            logger.info("date parse error use default!");
            Calendar calendar = Calendar.getInstance();
            endtime = calendar.getTime();
            calendar.add(Calendar.HOUR, -6);
            starttime = calendar.getTime();
        }
        String hql = "from "
                + entity
                + " s where s.ipAddr=:ip and s.created between :starttime and :endtime order by s.created";
        Query query = session.createQuery(hql);

        List storages = query.setParameter("ip", ip)
                .setParameter("starttime", starttime)
                .setParameter("endtime", endtime).list();
        Line uploadLine = new Line("上传流量");
        Line downLoadLine = new Line("下载流量");
        lines.add(uploadLine);
        lines.add(downLoadLine);
        for (int i = 0; i < storages.size(); i++) {
            Object obj = storages.get(i);
            Date created;
            long upload;
            long download;
            if ("Storage".equals(entity)) {
                Storage storage = (Storage) obj;
                created = storage.getCreated();
                upload = storage.getTotalUploadBytes() / (1024 * 1024);
                download = storage.getTotalDownloadloadBytes() / (1024 * 1024);
            } else if ("StorageHour".equals(entity)) {
                StorageHour storage = (StorageHour) obj;
                created = storage.getCreated();
                upload = storage.getTotalUploadBytes() / (1024 * 1024);
                download = storage.getTotalDownloadloadBytes() / (1024 * 1024);
            } else {
                StorageDay storage = (StorageDay) obj;
                created = storage.getCreated();
                upload = storage.getTotalUploadBytes() / (1024 * 1024);
                download = storage.getTotalDownloadloadBytes() / (1024 * 1024);
            }

            uploadLine.getData().add(new Long[]{created.getTime(), upload});
            downLoadLine.getData().add(
                    new Long[]{created.getTime(), download});
        }
        return lines;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Group> getAllGroups() throws IOException, MyException {
        List<Group> result = new ArrayList<Group>();
        Session session = getSession();
        String str = "from Group as g GROUP BY groupName order by g.created desc";
        Query query = session.createQuery(str);
        query.setFirstResult(0);
        query.setFetchSize(2);
        result = query.list();
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Line getListStoragesInfo(String ip, String startTime, String endTime)
            throws IOException, MyException {
        Line sc = new Line(ip);
        sc.setName(ip);
        System.out.println(startTime + "!!!!!!!!!!!!!!!!!!!" + endTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String start = null, end = null, d = sdf.format(new Date());
        try {
            start = (startTime == null || startTime.equals("")) ? "0000-00-00 00:00"
                    : startTime;
            end = (endTime == null || endTime.equals("")) ? d : endTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Session session = getSession();
        String str = "from StorageHour as s where s.ipAddr='" + ip
                + "' and s.created between '" + start + "' and '" + end + "'"
                + " order by s.created desc";
        System.out.println(str);
        Query query = session.createQuery(str);
        List<StorageHour> s = query.list();
        for (int i = s.size() - 1; i >= 0; i--) {
            Date created = s.get(i).getCreated();
            if (created == null) {
                continue;
            }
            sc.getData().add(
                    new Long[]{created.getTime(), s.get(i).getFreeMB()});
        }
        return sc;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Line> listStorageLines(String groupName) throws IOException,
            MyException {
        List<Line> lines = new ArrayList<Line>();
        Session session = getSession();
        List<Storage> storages = listStorage(groupName);
        for (Storage s : storages) {
            Query query = session
                    .createQuery("from Storage s where s.ipAddr=:ip order by s.created desc");
            List<Storage> results = query.setParameter("ip", s.getIpAddr())
                    .setMaxResults(10).list();
            Line line = new Line(s.getIpAddr()+"mem使用率");
            Line line1 = new Line(s.getIpAddr()+"cpu使用率");
            for (int i = results.size() - 1; i >= 0; i--) {
                Storage ss = results.get(i);
                line.getData()
                        .add(new Object[]{ss.getCreated().getTime(),
                                ss.getMem()});
                line1.getData().add(new Object[]{ss.getCreated().getTime(),
                        Double.parseDouble(ss.getCpu())});

            }
            lines.add(line);
            lines.add(line1);
        }
        return lines;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public StorageHour getStorageByIp(String ip) throws IOException,
            MyException {
        System.out.println(ip);
        StorageHour storages = new StorageHour();
        Session session = getSession();
        String str = "from StorageHour as s where s.ipAddr='" + ip
                + "' order by s.created desc";
        Query query = session.createQuery(str);
        query.setFirstResult(1);
        query.setMaxResults(1);
        storages = (StorageHour) query.list().get(0);
        return storages;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Line> getListFileCountStorage(String ip, String startTime,
                                              String endTime) throws IOException, MyException {
        List<Line> lines = new ArrayList<Line>();
        Session session = getSession();
        Date start = null, end = null, d1 = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String format = sdf.format(d1);
            start = (startTime == "" || startTime == null) ? sdf
                    .parse("0000-00-00 00:00") : sdf.parse(startTime);
            end = (endTime == "" || endTime == null) ? sdf.parse(format) : sdf
                    .parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Query query = session
                .createQuery("from StorageHour as s where s.ipAddr=:ip and s.created between :starttime and :endtime order by s.created");
        List<StorageHour> storages = query.setString("ip", ip)
                .setParameter("starttime", start).setParameter("endtime", end)
                .list();
        Line uploadLine = new Line("上传文件数量");
        Line downLoadLine = new Line("下载文件数量");
        lines.add(uploadLine);
        lines.add(downLoadLine);
        for (int i = 0; i <= storages.size() - 1; i++) {
            long u = 0;
            long d = 0;
            if (i > 0) {
                u = storages.get(i - 1).getSuccessUploadCount();
                d = storages.get(i - 1).getSuccessDownloadCount();
            }
            StorageHour storage = storages.get(i);
            Date created = storage.getCreated();
            if (created == null) {
                continue;
            }
            long totalUpload = storage.getSuccessUploadCount();
            long totalDownload = storage.getSuccessDownloadCount();
            uploadLine.getData().add(
                    new Long[]{created.getTime(), (totalUpload - u)});
            downLoadLine.getData().add(
                    new Long[]{created.getTime(), (totalDownload - d)});
        }

        return lines;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveFile(Fdfs_file f) throws IOException, MyException {
        Session session = getSession();
        session.save(f);
        logger.info("fdfs_file sava as" + f.getFile_id());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<GroupDay> getGroupsByName(String groupName) throws IOException,
            MyException {
        // To change body of implemented methods use File | Settings | File
        // Templates.
        List<GroupDay> result = new ArrayList<GroupDay>();
        Session session = getSession();
        String str = "from GroupDay as gd where gd.groupName=:groupName order by gd.created asc";
        Query query = session.createQuery(str);
        result = query.setParameter("groupName", groupName).list();
        return result;
    }
}
