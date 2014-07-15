package cn.me.fdfs.action;

import cn.me.fdfs.service.MonitorService;
import cn.me.fdfs.service.WarningService;
import cn.me.fdfs.vo.*;
import com.jcraft.jsch.JSchException;
import com.mysql.jdbc.StringUtils;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wanglt
 * Date: 12-8-27
 * Time: 下午10:02
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/warning")
public class WarningAction {
    @Autowired
    private WarningService warningService;
    @Autowired
    private MonitorService monitorService;

    @RequestMapping("/warningValue")
    public ModelAndView warningValue(String wdIpAddr, PageInfo pageInfo) throws IOException, MyException {
        ModelAndView mv = new ModelAndView("warning/warningValue.jsp");
        WarningData wd = new WarningData();
        wd.setWdIpAddr(wdIpAddr);
        List<WarningData> warningDataLists = warningService.findWarning(wd, pageInfo);
        for (WarningData warningDataList : warningDataLists) {
            List<Storage> storage = monitorService.listStorageTop(warningDataList.getWdIpAddr());
            if(!storage.isEmpty())
            warningDataList.setWdGroupName(storage.get(0).getGroupName());
        }
        mv.addObject("warningValues", warningDataLists);
        mv.addObject("wdIpAddr", wdIpAddr);
        mv.addObject("pageInfoList", pageInfo);
        return mv;
    }

    @RequestMapping("/warningEdit")
    public ModelAndView warningEdit(String id) throws IOException, MyException {
        ModelAndView mv = new ModelAndView("warning/warningEdit.jsp");
        if (!StringUtils.isNullOrEmpty(id)) {
            WarningData wd = warningService.findById(id);
            mv.addObject("id", wd.getId());
            mv.addObject("wdIpAddr", wd.getWdIpAddr());
            mv.addObject("wdFreeMB", wd.getWdFreeMB());
            mv.addObject("wdCpu", wd.getWdCpu());
            mv.addObject("wdMem", wd.getWdMem());
        }

        return mv;
    }

    @ResponseBody
    @RequestMapping("/saveWarning")
    public Message saveWarning(String warningdataid, String ips, String wdFreeMB, String wdCpu, String wdMem) throws IOException, MyException,JSchException {
        Message message = null;
        String result = "操作成功";
        message = new Message();
        List<Group> groupList = monitorService.listGroupInfo();
        for (Group group : groupList) {
            for (Storage storage : group.getStorageList()) {
                if (storage.getIpAddr().equals(ips)) {
                    if (storage.getCurStatus().equals("ACTIVE")) {
                        /*   if(Float.parseFloat(storage.getCpu())<Float.parseFloat(wdCpu)){
                           message.setStatusCode("300");
                           message.setMessage("cup预警值小于当前cpu使用率");
                           return message;
                       }else if(storage.getMem()<Float.parseFloat(wdMem)){
                           message.setStatusCode("300");
                           message.setMessage("内存预警值小于当前内存使用率");
                           return message;
                       }else */
                        if (storage.getFreeMB() < Long.decode(wdFreeMB)*1024) {
                            message.setStatusCode("300");
                            message.setMessage("容量预警值大于当前可用容量");
                            return message;
                        }
                    } else {
                        message.setStatusCode("300");
                        message.setMessage("服务器已停止工作");
                        return message;
                    }
                }
            }
        }

        WarningData wd = new WarningData();
        if (!StringUtils.isNullOrEmpty(warningdataid)) {
            wd.setId(warningdataid);
        }
        wd.setWdIpAddr(ips);
        wd.setWdCpu(wdCpu);
        wd.setWdFreeMB(Long.decode(wdFreeMB));
        wd.setWdMem(Float.parseFloat(wdMem));
        warningService.updateWarning(wd);
        // message=new Message("200",result,"warningValue","warningValue","closeCurrent","");
        message.setStatusCode("200");
        message.setMessage(result);
        return message;
    }

    @ResponseBody
    @RequestMapping("/delWarning")
    public Message delWarning(String ids) throws IOException, MyException {
        Message message = null;
        String[] id = ids.split(",");
        for (String i : id) {
            warningService.delWarning(i);
        }
        //Message message =new Message("200","删除成功","warningValue","warningValue","","");
        message = new Message();
        message.setStatusCode("200");
        message.setMessage("操作成功");
        return message;
    }

    @ResponseBody
    @RequestMapping("/selectIp")
    public List<Message> selectIp() throws IOException, MyException, JSchException {
        List<Message> ips = new ArrayList<Message>();
        List<Group> groupList = monitorService.listGroupInfo();
        for (Group group : groupList) {
            List<Storage> storageList = group.getStorageList();
            for (Storage storage : storageList) {
                if ((warningService.findByIp(storage.getIpAddr())).isEmpty()) {
                    ips.add(new Message(storage.getIpAddr()));
                }
            }
        }
        return ips;
    }


    @RequestMapping("/warUserList")
    public ModelAndView warUserList(String wusername, PageInfo pageInfo) throws IOException, MyException {
        ModelAndView mv = new ModelAndView("warning/warUserList.jsp");
        WarningUser wu = new WarningUser();
        wu.setName(wusername);
        List<WarningUser> warningUserList = warningService.findWarUser(wu, pageInfo);
        mv.addObject("warUserLists", warningUserList);
        mv.addObject("wusername", wusername);
        mv.addObject("pageInfoList", pageInfo);
        return mv;
    }

    @RequestMapping("/warUserAdd")
    public ModelAndView warUserAdd(String id) throws IOException, MyException {
        ModelAndView mv = new ModelAndView("warning/warUserAdd.jsp");
        if (!StringUtils.isNullOrEmpty(id)) {
            WarningUser wu = warningService.findUserId(id);
            mv.addObject("id", wu.getId());
            mv.addObject("name", wu.getName());
            mv.addObject("phone", wu.getPhone());
            mv.addObject("email", wu.getEmail());
        }
        return mv;
    }

    @ResponseBody
    @RequestMapping("/delWarUser")
    public Message delWarUser(String ids) throws IOException, MyException {
        Message message = null;
        String[] id = ids.split(",");
        for (String i : id) {
            warningService.delWarUser(i);
        }
        //Message message =new Message("200","删除成功","warUserList","warUserList","","");
        message = new Message();
        message.setStatusCode("200");
        message.setMessage("操作成功");
        return message;
    }

    @ResponseBody
    @RequestMapping("/saveWarUser")
    public Message saveWarUser(String wuid, String wuname, String wuphone, String wuemail) throws IOException, MyException {
        Message message = null;
        String result = "操作成功";
        WarningUser wu = new WarningUser();
        if (wuphone.length() > 11) {
             result="操作失败";
            message = new Message();
            message.setStatusCode("304");
            message.setMessage("电话号较长");
        } else {
            if (!StringUtils.isNullOrEmpty(wuid)) {
                wu.setId(wuid);
            } else {

            }
            wu.setName(wuname);
            wu.setPhone(wuphone);
            wu.setEmail(wuemail);
            warningService.updateWarUser(wu);
            //  Message message =new Message("200",result,"warUserList","warUserList","closeCurrent","");
            message = new Message();
            message.setStatusCode("200");
            message.setMessage("操作成功");
        }
        return message;
    }

}
