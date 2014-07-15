package cn.me.fdfs.service;

import com.jcraft.jsch.JSchException;
import org.csource.common.MyException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: devuser
 * Date: 12-8-28
 * Time: 下午5:04
 * To change this template use File | Settings | File Templates.
 */
public interface JobService {
    void updateGroupByMinute() throws IOException, MyException, JSchException;
    void updateGroupByHour() throws IOException, MyException, JSchException;
    void updateGroupByDay() throws IOException, MyException, JSchException;
    void readDataFromLoggerToDataBase()throws  JSchException;
}
