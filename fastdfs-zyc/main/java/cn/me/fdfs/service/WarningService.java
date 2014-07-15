package cn.me.fdfs.service;

import cn.me.fdfs.vo.PageInfo;
import cn.me.fdfs.vo.WarningData;
import cn.me.fdfs.vo.WarningUser;
import org.csource.common.MyException;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wanglt
 * Date: 12-8-28
 * Time: 上午10:37
 * To change this template use File | Settings | File Templates.
 */
public interface WarningService {
    void updateWarning(WarningData wd) throws IOException, MyException ;
    List<WarningData> findWarning() throws IOException, MyException ;
    List<WarningData> findWarning(WarningData wd, PageInfo pageInfo) throws IOException, MyException ;
    WarningData findById(String id) throws IOException, MyException ;
    void delWarning(String id)throws IOException, MyException ;
    List<WarningData> findByIp(String ip) throws IOException, MyException ;

    List<WarningUser> findWarUser() throws IOException, MyException ;
    List<WarningUser> findWarUser(WarningUser wu, PageInfo pageInfo) throws IOException, MyException ;
    WarningUser findUserId(String id) throws IOException, MyException ;
    void delWarUser(String id)throws IOException, MyException ;
    void updateWarUser(WarningUser wu) throws IOException, MyException ;
}
