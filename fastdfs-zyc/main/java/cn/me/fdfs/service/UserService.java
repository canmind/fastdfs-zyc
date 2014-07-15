package cn.me.fdfs.service;

import cn.me.fdfs.vo.User;
import org.csource.common.MyException;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wanglt
 * Date: 12-8-31
 * Time: 上午9:43
 * To change this template use File | Settings | File Templates.
 */

public interface UserService {
    List<User> userlist(String username) throws IOException, MyException;
    void updateOrSaveUser(User user) throws IOException, MyException ;
    User findById(String id) throws IOException, MyException ;
    void delUser(String id)throws IOException, MyException ;
    boolean login(String name, String password) throws IOException, MyException ;
    User  findByName(String name) throws IOException, MyException ;
}
