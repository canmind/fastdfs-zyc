package cn.me.fdfs.service.impl;

import cn.me.fdfs.service.BaseService;
import cn.me.fdfs.service.WarningService;
import cn.me.fdfs.vo.PageInfo;
import cn.me.fdfs.vo.WarningData;
import cn.me.fdfs.vo.WarningUser;
import com.mysql.jdbc.StringUtils;
import org.csource.common.MyException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wanglt
 * Date: 12-8-28
 * Time: 上午10:38
 * To change this template use File | Settings | File Templates.
 */
@Service
public class WarningServiceImpl extends BaseService implements WarningService {
    private static final Logger logger = LoggerFactory
            .getLogger(WarningServiceImpl.class);
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateWarning(WarningData wd) throws IOException, MyException {
        //To change body of implemented methods use File | Settings | File Templates.
        Session session = getSession();
        session.saveOrUpdate(wd);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<WarningData> findWarning() throws IOException, MyException {
        //To change body of implemented methods use File | Settings | File Templates.
        List<WarningData> warningDatas = new ArrayList<WarningData>();
        Session session = getSession();
        StringBuilder queryString = new StringBuilder("from WarningData as w");
        Query query = session.createQuery(queryString.toString());
        warningDatas = query.list();
        return warningDatas;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<WarningData> findWarning(WarningData wd,PageInfo pageInfo) throws IOException, MyException {
        //To change body of implemented methods use File | Settings | File Templates.
        List<WarningData> warningDatas = new ArrayList<WarningData>();
        Session session = getSession();
        StringBuilder queryString = new StringBuilder("from WarningData as wd ");
        if(!StringUtils.isNullOrEmpty(wd.getWdIpAddr())){
            queryString.append("where wd.wdIpAddr like '%"+wd.getWdIpAddr()+"%'");
        }
        Query query = session.createQuery(queryString.toString());
        pageInfo.setTotalCount(query.list().size());
        query.setMaxResults(pageInfo.getNumPerPage());
        query.setFirstResult((pageInfo.getPageNum()-1)*pageInfo.getNumPerPage());
        warningDatas = query.list();
        return warningDatas;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public WarningData findById(String id) throws IOException, MyException {
        //To change body of implemented methods use File | Settings | File Templates.
        WarningData wd=new WarningData();
        Session session = getSession();
        wd= (WarningData) session.get(WarningData.class,id);
        return wd;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delWarning(String id) throws IOException, MyException {
        //To change body of implemented methods use File | Settings | File Templates.
        WarningData wd=new WarningData();
        wd.setId(id);
        Session session = getSession();
       session.delete(wd);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<WarningData> findByIp(String ip) throws IOException, MyException {
         //To change body of implemented methods use File | Settings | File Templates.
        List<WarningData> warningDatas = new ArrayList<WarningData>();
        Session session = getSession();
        Query query = session
                .createQuery("from WarningData wd where wd.wdIpAddr=:ip");
        warningDatas = query.setString("ip", ip).list();
        return warningDatas;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<WarningUser> findWarUser() throws IOException, MyException {
        //To change body of implemented methods use File | Settings | File Templates.
        List<WarningUser> warningUsers = new ArrayList<WarningUser>();
        Session session = getSession();
        StringBuilder queryString = new StringBuilder("from WarningUser as w");
        Query query = session.createQuery(queryString.toString());
        warningUsers = query.list();
        return warningUsers;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<WarningUser> findWarUser(WarningUser wu,PageInfo pageInfo) throws IOException, MyException {
        //To change body of implemented methods use File | Settings | File Templates.
        List<WarningUser> warningUsers = new ArrayList<WarningUser>();
        Session session = getSession();
        StringBuilder queryString = new StringBuilder("from WarningUser as w ");
        if(!StringUtils.isNullOrEmpty(wu.getName())){
            queryString.append("where w.name like '%"+wu.getName()+"%'");
        }
        Query query = session.createQuery(queryString.toString());
        pageInfo.setTotalCount(query.list().size());
        query.setMaxResults(pageInfo.getNumPerPage());
        query.setFirstResult((pageInfo.getPageNum()-1)*pageInfo.getNumPerPage());
        warningUsers = query.list();
        return warningUsers;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public WarningUser findUserId(String id) throws IOException, MyException {
        //To change body of implemented methods use File | Settings | File Templates.
        WarningUser wu=new WarningUser();
        Session session = getSession();
        wu= (WarningUser) session.get(WarningUser.class,id);
        return wu;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delWarUser(String id) throws IOException, MyException {
        //To change body of implemented methods use File | Settings | File Templates.
        WarningUser wu=new WarningUser();
        wu.setId(id);
        Session session = getSession();
        session.delete(wu);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateWarUser(WarningUser wu) throws IOException, MyException {
        //To change body of implemented methods use File | Settings | File Templates.
        Session session = getSession();
        session.saveOrUpdate(wu);
    }
}
