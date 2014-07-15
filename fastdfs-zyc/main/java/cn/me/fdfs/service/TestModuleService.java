package cn.me.fdfs.service;

import cn.me.fdfs.vo.Fdfs_file;
import cn.me.fdfs.vo.Line;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-9-4
 * Time: 下午4:43
 * To change this template use File | Settings | File Templates.
 */
public interface TestModuleService {

    List<Fdfs_file> getAllFileList();

    Fdfs_file getFileByFileId(String fileId);

    void saveFastFile(Fdfs_file f);

    Map<String,Object[]> getAllFileListByTen(String ip);

  Line getAllFileListForPie(String ip);

    List<Fdfs_file> getAllFileListByPage(String pageNum, String pageSize, String keyForSearch);

    int getCountDownLoadFile(String keyForSearch);

}
