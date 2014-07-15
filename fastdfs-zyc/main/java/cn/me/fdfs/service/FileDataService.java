package cn.me.fdfs.service;

import cn.me.fdfs.vo.DownloadFileRecord;
import cn.me.fdfs.vo.Fdfs_file;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-9-3
 * Time: 下午7:03
 * To change this template use File | Settings | File Templates.
 */
public interface FileDataService {
   List<Fdfs_file> getFileListByGroupName(String groupName);

    /**
     *   根据ip和fileId去数据库中查找是否存在DownloadFileRecord对象
     * @param ip
     * @param fileId
     * @return
     */
    DownloadFileRecord getDownloadFileRecordByIpAndFileId(String ip, String fileId);
    //保存 DownloadFileRecord对象
    void saveDF(DownloadFileRecord downloadFileRecord);
}