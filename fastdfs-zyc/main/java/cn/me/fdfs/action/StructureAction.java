package cn.me.fdfs.action;

import cn.me.fdfs.service.MonitorService;
import cn.me.fdfs.service.StructureService;
import cn.me.fdfs.util.Tools;
import cn.me.fdfs.vo.Group;
import cn.me.fdfs.vo.Line;
import cn.me.fdfs.vo.Storage;
import com.jcraft.jsch.JSchException;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-8-29
 * Time: 上午11:03
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/structure")
public class StructureAction {
    @Autowired
    private StructureService structureService;
    @Autowired
    private MonitorService monitorService;
    private static final Logger logger = LoggerFactory
            .getLogger(StructureAction.class);


    @RequestMapping("/netStructure")
    public ModelAndView netStructure() throws JSchException{
        ModelAndView mv = new ModelAndView("structure/netStructure.jsp");
        try {
            mv.addObject("groupInfo", monitorService.listGroupInfo());
            mv.addObject("trucker", getTrackForStruct());
        } catch (IOException e) {
           logger.error("",e);
        } catch (MyException e) {
            logger.error("", e);
        }
        return mv;

    }

    @RequestMapping("/serverInfo")
    public ModelAndView serverInfo(String ip) throws IOException, MyException,JSchException {
        ModelAndView mv = new ModelAndView("structure/serverInfo.jsp");
        if(ip.indexOf(":")>=0){
         String[] data=ip.split(":");
            ip=data[0];
        }
        List<Group> groups=monitorService.listGroupInfo();
        for(Group group:groups){
          for(Storage storage:group.getStorageList()){
              if(storage.getIpAddr().equals(ip)){
                  mv.addObject("serverInfo", storage);
              }
          }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        mv.addObject("end", sdf.format(calendar.getTime()));
        calendar.add(Calendar.HOUR, -1);
        mv.addObject("start", sdf.format(calendar.getTime()));
        return mv;
    }
    @ResponseBody
    @RequestMapping("/getForperformanceByIp")
        public List<Line> getForperformanceByIp(String ip) {
            List<Line> storageList = structureService.listStorageTopLine(ip);
        return storageList;
    }
    @ResponseBody
    @RequestMapping("/storageInfoForFile")
      public List<Line>  storageInfoForFile(String ip){
        List<Line> storageList = new ArrayList<Line>();
        storageList = structureService.listStorageAboutFile(ip);
        return storageList;
      }
    private String getTrackForStruct() {
        String result = "";
        try {
            ClientGlobal.init(Tools.getClassPath()+"fdfs_client.conf");

        } catch (IOException e) {
            logger.error("",e);
        } catch (MyException e) {
            logger.error("",e);
        }
        String configFile = Tools.getClassPath()+"fdfs_client.conf";
        FileInputStream fis = null;
        InputStreamReader isr = null;
        Properties p = new Properties();
        try {
            fis = new FileInputStream(configFile);
            isr = new InputStreamReader(fis, "UTF-8");
            p.load(isr);
            fis.close();
            isr.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error("",e);
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    logger.error("",e);
                }
            }
        }

        result = p.getProperty("tracker_server");
        return result;


    }
}


