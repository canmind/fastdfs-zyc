package cn.me.fdfs.service.impl;

import cn.me.fdfs.service.BaseService;
import cn.me.fdfs.service.FileDataService;
import cn.me.fdfs.service.JobService;
import cn.me.fdfs.service.WarningService;
import cn.me.fdfs.util.BuildMail;
import cn.me.fdfs.util.JsshProxy;
import cn.me.fdfs.util.Tools;
import cn.me.fdfs.vo.*;
import com.jcraft.jsch.JSchException;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: devuser
 * Date: 12-8-28
 * Time: 下午5:08
 * To change this template use File | Settings | File Templates.
 */
@Service
public class JobServiceImpl extends BaseService implements JobService {

    private static final Logger logger = LoggerFactory
            .getLogger(JobServiceImpl.class);


    @Autowired
    private WarningService warningService;


    @Autowired
    private FileDataService fileDataService;

    Map<String, Date> datemap = new HashMap<String, Date>();



    @Override
    @Scheduled(cron = "0 0/1 * * * ?")
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateGroupByMinute() throws IOException, MyException, JSchException {
        logger.info("group minute data upate begin...");
        List<Group> groups = getGroupInfoByMinute();

        Session session = getSession();
        for (Group group : groups) {
            session.save(group);
        }
        logger.info("group minute data upated end");
    }

    @Override
    @Scheduled(cron = "0 0 0/1 * * ?")
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateGroupByHour() throws IOException, MyException, JSchException{
        logger.info("group hour data upate begin...");
        List<GroupHour> groups = getGroupInfoByHour();
        Session session = getSession();
        for (GroupHour group : groups) {
            session.save(group);
        }
        logger.info("group hour data upated end");
    }

    @Override
    @Scheduled(cron = "0 0 0 0/1 * ?")
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateGroupByDay() throws IOException, MyException, JSchException {
        logger.info("group day data upate begin...");
        List<GroupDay> groups = getGroupInfoByDay();
        Session session = getSession();
        for (GroupDay group : groups) {
            session.save(group);
        }
        logger.info("group day data upated end");
    }

    private List<Group> getGroupInfoByMinute() throws IOException, MyException, JSchException {

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
        for (StructGroupStat groupStat : groupStats) {
            Group group = new Group();
            BeanUtils.copyProperties(groupStat, group);
            StructStorageStat[] storageStats = tracker.listStorages(
                    trackerServer, groupStat.getGroupName());
            for (StructStorageStat storageStat : storageStats) {

                Storage storage = new Storage();

                BeanUtils.copyProperties(storageStat, storage);
                storage.setId(null);
                System.out.println("getGroupInfoByMinute: storageId:"+storage.getId());
                storage.setCurStatus(ProtoCommon
                        .getStorageStatusCaption(storageStat.getStatus()));

                storage.setGroup(group);
                storage.setGroupName(group.getGroupName());
                group.getStorageList().add(storage);
            }
            result.add(group);
        }

        Date date = new Date();
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
                                storage.setCreated(date);
                                //warning
                                warning(storage);

                            }
                            warningOffline(storage);
                        }
                    }
                }
            }
        }

        return result;
    }


    private List<GroupHour> getGroupInfoByHour() throws IOException, MyException,JSchException {
        List<GroupHour> result = new ArrayList<GroupHour>();
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
        for (StructGroupStat groupStat : groupStats) {
            GroupHour group = new GroupHour();
            BeanUtils.copyProperties(groupStat, group);
            StructStorageStat[] storageStats = tracker.listStorages(
                    trackerServer, groupStat.getGroupName());
            for (StructStorageStat storageStat : storageStats) {
                StorageHour storage = new StorageHour();
                BeanUtils.copyProperties(storageStat, storage);
                storage.setCurStatus(ProtoCommon
                        .getStorageStatusCaption(storageStat.getStatus()));
                storage.setId(null);
                System.out.println("getGroupInfoByHour: storageId:"+storage.getId());
                storage.setGroup(group);
                storage.setGroupName(group.getGroupName());
                group.getStorageList().add(storage);
            }
            result.add(group);
        }
        Date date = new Date();
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
                    for (GroupHour group : result) {
                        group.setCreated(date);
                        for (StorageHour storage : group.getStorageList()) {
                            if (machine.getIp().equalsIgnoreCase(
                                    storage.getIpAddr())) {
                                String[] strArrray = str.replaceAll(" +", ",")
                                        .split(",");
                                storage.setCpu(strArrray[2]);
                                storage.setMem(Float.parseFloat(strArrray[3]));
                                storage.setCreated(date);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private List<GroupDay> getGroupInfoByDay() throws IOException, MyException, JSchException {
        List<GroupDay> result = new ArrayList<GroupDay>();
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
        for (StructGroupStat groupStat : groupStats) {
            GroupDay group = new GroupDay();
            BeanUtils.copyProperties(groupStat, group);
            StructStorageStat[] storageStats = tracker.listStorages(
                    trackerServer, groupStat.getGroupName());
            for (StructStorageStat storageStat : storageStats) {
                StorageDay storage = new StorageDay();
                BeanUtils.copyProperties(storageStat, storage);

                storage.setCurStatus(ProtoCommon
                        .getStorageStatusCaption(storageStat.getStatus()));
                storage.setGroup(group);
                storage.setId(null);
                storage.setGroupName(group.getGroupName());
                System.out.println("getGroupInfoByDay: storageId:"+storage.getId());
                group.getStorageList().add(storage);
            }
            result.add(group);
        }
        Date date = new Date();
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
                    for (GroupDay group : result) {
                        group.setCreated(date);
                        for (StorageDay storage : group.getStorageList()) {
                            if (machine.getIp().equalsIgnoreCase(
                                    storage.getIpAddr())) {
                                String[] strArrray = str.replaceAll(" +", ",")
                                        .split(",");
                                storage.setCpu(strArrray[2]);
                                storage.setMem(Float.parseFloat(strArrray[3]));
                                storage.setCreated(date);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private void warning(Storage storage) throws IOException, MyException {
        List<WarningData> warningDatas = warningService.findByIp(storage.getIpAddr());
        StringBuffer stringBuffer = new StringBuffer("异常服务器：" + storage.getIpAddr() + "</br>");
        if (!warningDatas.isEmpty()) {
            float wdCup = Float.parseFloat(warningDatas.get(0).getWdCpu());
            float wdMem = warningDatas.get(0).getWdMem();
            long wdFreeMB = warningDatas.get(0).getWdFreeMB();
            boolean res = true;
            if (Float.parseFloat(storage.getCpu()) > wdCup) {
                stringBuffer.append("cpu使用率当前值为： " + storage.getCpu() + "% 大于预警值：" + wdCup + "%</br>");
                res = false;
            }
            if (storage.getMem() > wdMem) {
                stringBuffer.append("内存使用率当前值为： " + storage.getMem() + "% 大于预警值：" + wdMem + "%</br>");
                res = false;
            }
            if (storage.getFreeMB() < wdFreeMB) {
                stringBuffer.append("可用空间当前值为： " + storage.getFreeMB() + "MB 小于预警值：" + wdFreeMB + "MB</br>");
                res = false;
            }
            if (!res) {
                BuildMail buildMail = new BuildMail();
                List<WarningUser> warningUser = new ArrayList<WarningUser>();
                warningUser = warningService.findWarUser();
                for (WarningUser wu : warningUser) {
                    buildMail.sendWarning("VivaMe维我", wu.getEmail(), "dfs预警报告", stringBuffer.toString());
                }
            }
        }
    }

    public void warningOffline(Storage storage) throws IOException, MyException {
        List<WarningData> warningDatas = warningService.findByIp(storage.getIpAddr());
        boolean res = false;
        StringBuffer stringBuffer = new StringBuffer("异常服务器 ：" + storage.getIpAddr() + "</br>");
        if (storage.getCurStatus().equals("OFFLINE")) {
            stringBuffer.append("服务器停止工作");
            if (datemap.containsKey(storage.getIpAddr())) {
                Date offdate = datemap.get(storage.getIpAddr());
                Date now = new Date();
                long temp = now.getTime() - offdate.getTime();
                if (temp >= 1000 * 60 * 60) {
                    datemap.put(storage.getIpAddr(), new Date());
                    res = true;
                }
            } else {
                datemap.put(storage.getIpAddr(), new Date());
                res = true;
            }
        } else {
            if (datemap.containsKey(storage.getIpAddr())) {
                datemap.remove(storage.getIpAddr());
            }
        }
        if (res) {
            BuildMail buildMail = new BuildMail();
            List<WarningUser> warningUser = new ArrayList<WarningUser>();
            warningUser = warningService.findWarUser();
            for (WarningUser wu : warningUser) {
                buildMail.sendWarning("VivaMe维我", wu.getEmail(), "dfs预警报告", stringBuffer.toString());
            }
        }
    }

    /**
     * 每天定时读取logger日志，并入库
     */
    @Override
    @Scheduled(cron = "0 0 01 * * ?")
    public void readDataFromLoggerToDataBase()throws  JSchException{
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        Date d = c.getTime();
        String date = df.format(d);
        for (Machine machine : Tools.machines) {
            String cmd = "cat "+machine.getLogpath()+"/fastdfs_" + date + ".log";
            List<String> strList = new ArrayList<String>();
            if(machine.isConfigType())
                strList = Tools.exeRemoteConsole(machine.getIp(),
                        machine.getUsername(), machine.getPassword(), cmd);
            else
                strList = new JsshProxy(machine.getIp(),machine.getUsername(),machine.getPort(),machine.getSsh()).execute(cmd).getExecuteLines();
            for (String str : strList) {
                String data[] = str.split(" ");
                if (data[8].equals("200")) {
                    //去数据库对应的表tbdownloadfilerecord中查询有没有fileId和对应的ip的DownloadFileRecord存在；
                    DownloadFileRecord downloadFileRecord = fileDataService.getDownloadFileRecordByIpAndFileId(machine.getIp(), data[6]);
                    if (downloadFileRecord != null) {
                        downloadFileRecord.setAccessCount(downloadFileRecord.getAccessCount() + 1);
                    } else {
                        downloadFileRecord = new DownloadFileRecord();
                        downloadFileRecord.setFileId(data[6]);
                        downloadFileRecord.setSrc_ip(machine.getIp());
                        downloadFileRecord.setAccessCount(1);
                    }
                    fileDataService.saveDF(downloadFileRecord);
                }
            }
        }
    }
}





