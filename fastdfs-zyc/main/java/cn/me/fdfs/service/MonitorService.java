package cn.me.fdfs.service;

import cn.me.fdfs.vo.*;
import com.jcraft.jsch.JSchException;
import org.csource.common.MyException;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: devuser Date: 12-8-20 Time: 下午9:03 To
 * change this template use File | Settings | File Templates.
 */
public interface MonitorService {

	List<Group> listGroupInfo() throws IOException, MyException,JSchException;

	List<Group> listGroups() throws IOException, MyException;

	List<Storage> listStorage(String groupName) throws IOException, MyException;

	List<Storage> listStorageTop(String ipaddr) throws IOException, MyException;

	List<Line> listStorageLines(String groupName) throws IOException,
			MyException;

	List<Line> getNetTrafficLines(String ip, String start, String end);

	Line getListStoragesInfo(String ip, String startTime, String endTime)
			throws IOException, MyException;

	StorageHour getStorageByIp(String ip) throws IOException, MyException;

	List<Group> getAllGroups() throws IOException, MyException;

	List<Line> getListFileCountStorage(String ip, String startTime,
                                       String endTime) throws IOException, MyException;

	void saveFile(Fdfs_file f) throws IOException, MyException;

	List<GroupDay> getGroupsByName(String groupName) throws IOException,
			MyException;

}
