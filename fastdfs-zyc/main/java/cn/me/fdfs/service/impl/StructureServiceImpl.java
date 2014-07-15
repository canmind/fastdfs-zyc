package cn.me.fdfs.service.impl;

import cn.me.fdfs.service.BaseService;
import cn.me.fdfs.service.StructureService;
import cn.me.fdfs.vo.Line;
import cn.me.fdfs.vo.Storage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-8-30
 * Time: 上午11:42
 * To change this template use File | Settings | File Templates.
 */
@Service
public class StructureServiceImpl extends BaseService implements StructureService {
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Line> listStorageTopLine(String ip) {
        List<Line> lines = new ArrayList<Line>();
        Session session = getSession();
        Query query = session .createQuery("from Storage s where s.ipAddr=:ip order by s.created desc");
                List<Storage> results = query.setString("ip",ip)
                        .setMaxResults(10).list();
        Line line = new Line(ip);
        for (int i = results.size() - 1; i >= 0; i--) {
            Storage ss = results.get(i);
            line.getData().add(new Object[]{ss.getCreated().getTime(), ss.getMem()});
        }
        lines.add(line);
        return  lines;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Line> listStorageAboutFile(String ip) {
        List<Line> lines = new ArrayList<Line>();
        Session session = getSession();
        Query query = session .createQuery("from Storage s where s.ipAddr=:ip order by s.created desc");
        List<Storage> results = query.setString("ip",ip)
                .setMaxResults(10).list();
        Line line = new Line(ip);
        for (int i = results.size() - 1; i >= 0; i--) {
            Storage ss = results.get(i);
            line.getData().add(new Object[]{ss.getCreated().getTime(), ss.getTotalDownloadCount()});
        }
        lines.add(line);
        Line line1=new Line(ip);
        for (int i = results.size() - 1; i >= 0; i--) {
            Storage ss = results.get(i);
            line1.getData().add(new Object[]{ss.getCreated().getTime(),ss.getTotalUploadCount()});
        }
        lines.add(line1);
        return  lines;
    }

}
