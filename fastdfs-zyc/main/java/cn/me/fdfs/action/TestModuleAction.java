package cn.me.fdfs.action;

import cn.me.fdfs.service.MonitorService;
import cn.me.fdfs.service.TestModuleService;
import cn.me.fdfs.util.Tools;
import cn.me.fdfs.vo.*;
import com.jcraft.jsch.JSchException;
import com.mysql.jdbc.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-9-4
 * Time: 下午4:22
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/testModule")
public class TestModuleAction {
    @Autowired
    private TestModuleService testModuleService;
    @Autowired
    private MonitorService monitorService;
    private static final Logger logger = LoggerFactory
            .getLogger(TestModuleAction.class);

    @RequestMapping("/testDownLoad")
    public ModelAndView testDownLoad(String pageNum, String pageSize,String keyForSearch) {
        ModelAndView mv = new ModelAndView("testModule/downLoadTest.jsp");
        List<Fdfs_file> list = testModuleService.getAllFileListByPage(pageNum, pageSize,keyForSearch);
        int countDownLoadFile = testModuleService.getCountDownLoadFile(keyForSearch);
        mv.addObject("testFileCount", countDownLoadFile);
        if(!StringUtils.isNullOrEmpty(keyForSearch)){
            mv.addObject("pageNum", "1");
        }else{
            mv.addObject("pageNum", pageNum);
        }
        mv.addObject("pageSize", pageSize);
        mv.addObject("testFileList", list);
        mv.addObject("keySearch",keyForSearch);
        return mv;
    }

    @ResponseBody
    @RequestMapping("/toDownLoadToLocal")
    public Message toDownLoadToLocal(HttpServletResponse response, String fileId, String srcIpAddr, String fileName) {
        Message message = null;
        String conf_filename = Thread.currentThread().getContextClassLoader()
                .getResource("fdfs_client.conf").getPath();
        try {
            ClientGlobal.init(conf_filename);

            System.out.println("network_timeout="
                    + ClientGlobal.g_network_timeout + "ms");
            System.out.println("charset=" + ClientGlobal.g_charset);
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient1 client = new StorageClient1(trackerServer,
                    storageServer);
            byte[] bytes = client.download_file1(fileId);
            response.setHeader("content-disposition", "attachment;filename=" + fileName);
            if (bytes != null) {
                OutputStream os = response.getOutputStream();
                os.write(bytes);
                os.close();
                Fdfs_file f = testModuleService.getFileByFileId(fileId);
                if (f != null) {

                    testModuleService.saveFastFile(f);
                }
            }
        } catch (IOException e) {
            logger.error("", e);
        } catch (MyException e) {
            logger.error("", e);
        }
        return message;
    }

    @RequestMapping("/accessFile")
    public ModelAndView accessFile() throws IOException, MyException,JSchException {
        ModelAndView mv = new ModelAndView("testModule/accessFileCharts.jsp");
        List<Group> groups = monitorService.listGroupInfo();
        mv.addObject("groups", groups);

        return mv;
    }

    @ResponseBody
    @RequestMapping("/tenFileDownLoad")
    public Map<String, Object[]> tenFileDownLoad(String ip) {
        Map<String, Object[]> map = new HashMap<String, Object[]>();
        map = testModuleService.getAllFileListByTen(ip);
        return map;
    }

    @ResponseBody
    @RequestMapping("/allFilePie")
    public List<Line> allFilePie(String ip) {
        Line line = testModuleService.getAllFileListForPie(ip);
        List<Line> fileList = new ArrayList<Line>();
        fileList.add(line);
        return fileList;
    }

    @RequestMapping("/downloadByApi")
    public void downloadByApi(String fieldId,String fileName, HttpServletResponse response) throws IOException, MyException {

        ClientGlobal.init(Tools.getClassPath() + "fdfs_client.conf");
        logger.info("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
        logger.info("charset=" + ClientGlobal.g_charset);
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getConnection();
        if (trackerServer == null) {
            return;
        }

        StorageClient1 client = new StorageClient1(trackerServer, null);
        byte[] bytes = client.download_file1(fieldId);

        logger.info("length:"+bytes.length);

        response.setHeader("Content-disposition",
                "attachment; filename="+fileName);
        OutputStream os = response.getOutputStream();
        os.write(bytes);
        os.close();
    }
}
