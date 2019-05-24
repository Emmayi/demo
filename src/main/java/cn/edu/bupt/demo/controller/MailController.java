package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.dao.Mail.MailService;
import cn.edu.bupt.demo.dao.StaffNumber.StaffService;
import cn.edu.bupt.demo.entity.InspectionEquip;
import cn.edu.bupt.demo.entity.InspectionPlan;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zy
 * @date 2019/5/8 下午9:33
 */

@RestController
@RequestMapping("api/v1/info")
@CrossOrigin
public class MailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private StaffService staffService;

    /**
     *
     */
    @RequestMapping(value = "/sendGeneralMessage", method = RequestMethod.POST)
    public void sendGeneralMailMessage(@RequestBody String info) throws Exception{

        InspectionPlan inspectionPlan = JSONObject.parseObject(info, InspectionPlan.class);

        String to = staffService.findEmailByName(inspectionPlan.getId());

        try {
            mailService.sendMessageMail(inspectionPlan, to,"巡检计划表通知", "message1.ftl");

        }catch (Exception e){
            throw new Exception("sendGeneralMailMessage error!");
        }

    }

    @RequestMapping(value = "/sendEquipMessage", method = RequestMethod.POST)
    public void sendEquipMailMessage(@RequestBody String info) throws Exception{

        InspectionEquip inspectionEquip = JSONObject.parseObject(info, InspectionEquip.class);
        String to = staffService.findEmailByName(inspectionEquip.getId());
        try {
            mailService.sendMessageMail(inspectionEquip, to,"设备定期检测通知", "message2.ftl");

        }catch (Exception e){
            throw new Exception("sendEquipMailMessage error!");
        }

    }
}
