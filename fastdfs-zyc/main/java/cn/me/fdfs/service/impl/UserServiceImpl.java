package cn.me.fdfs.service.impl;

import cn.me.fdfs.service.BaseService;
import cn.me.fdfs.service.UserService;
import cn.me.fdfs.vo.User;
import cn.me.fdfs.vo.WarningUser;
import org.csource.common.MyException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wanglt
 * Date: 12-8-31                              WarningServiceImpl
 * Time: 上午9:44
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserServiceImpl  extends BaseService implements UserService {
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<User> userlist(String username) throws IOException, MyException {
        //To change body of implemented methods use File | Settings | File Templates.
        List<User> users = new ArrayList<User>();
        Session session = getSession();
        StringBuilder queryString = new StringBuilder("from User as u ");
        if(username!=null&&username!=""){
            queryString.append("where u.name like '%"+username+"%'");
        }
        Query query = session.createQuery(queryString.toString());
        users = query.list();
        return users;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateOrSaveUser(User user) throws IOException, MyException {
        //To change body of implemented methods use File | Settings | File Templates.
        Session session = getSession();
        session.saveOrUpdate(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User findById(String id) throws IOException, MyException {
      //To change body of implemented methods use File | Settings | File Templates.
        User u=new User();
        Session session = getSession();
        u= (User) session.get(WarningUser.class,id);
        return u;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delUser(String id) throws IOException, MyException {
        //To change body of implemented methods use File | Settings | File Templates.
        User u=new User();
        u.setId(id);
        Session session = getSession();
        session.delete(u);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean login(String name, String password) throws IOException, MyException {
        //To change body of implemented methods use File | Settings | File Templates.
        List<User> users = new ArrayList<User>();
        Session session = getSession();
        StringBuilder queryString = new StringBuilder("from User as u where u.name=:name and u.psword=:password");

        Query query = session.createQuery(queryString.toString());
        users = query.setParameter("name",name)
                .setParameter("password",password)
                .list();
        boolean res=false;
        if(!users.isEmpty()){
             res=true;
        }
        return res;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User findByName(String name) throws IOException, MyException {
         //To change body of implemented methods use File | Settings | File Templates.
        User user = null;
        List<User> users = new ArrayList<User>();
        Session session = getSession();
        StringBuilder queryString = new StringBuilder("from User as u where u.name=:name ");

        Query query = session.createQuery(queryString.toString());
        users = query.setParameter("name",name)
                .list();
        if(!users.isEmpty()){
                user=users.get(0);
        }
        return user;
    }
}
