package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.dao.StaffNumber.StaffService;
import cn.edu.bupt.demo.entity.StaffNumber;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author zy
 * @date 2018/10/18 下午4:48
 */

//@Api2Doc(id = "demo1", name = "用户接口1")
//@ApiComment(seeClass = StaffNumber.class)
@Slf4j
@RestController
@RequestMapping("/api/v1/info")
@CrossOrigin
public class StaffController {

    @Autowired
    private StaffService staffService;

    //根据工作人员id获取工作人员信息
    @RequestMapping(value = "/staffById",params = {"staffId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getStaffById(@RequestParam Integer staffId) throws Exception{
        try {
            return staffService.findStaffById(staffId).toString();
        }catch (Exception e){
            throw new Exception("getStaffById error!");
        }
    }

    //根据工作人员name获取工作人员信息
    @RequestMapping(value = "/staffByName",params = {"staffName"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getStaffByName(@RequestParam String staffName) throws Exception{
        try {
            return staffService.findStaffByName(staffName).toString();
        }catch (Exception e){
            throw new Exception("getStaffByName error!");
        }
    }

    //创建Staff，填写信息
    @RequestMapping(value = "/staff", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createStaff(@RequestBody String staffInfo) throws Exception{
        StaffNumber staffNumber = JSONObject.parseObject(staffInfo, StaffNumber.class);
        try {
            staffService.save(staffNumber);
            return staffNumber.toString();
        } catch (Exception e) {
            throw new Exception("createStaff error!");
        }
    }

    //更新staff信息
    @RequestMapping(value = "/staff", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateStaff(@RequestBody String staffInfo) throws Exception{
        StaffNumber staffNumber = JSONObject.parseObject(staffInfo, StaffNumber.class);
        if(staffNumber.getId().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }
        try {
            staffService.updateStaff(staffNumber);
            return staffNumber.toString();
        } catch (Exception e) {
            throw new Exception("updateStaff error!");
        }
    }

    //根据StaffId统计一共有多少
    @RequestMapping(value = "/staffCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer staffCountById() throws Exception{
        try {
            Integer count = staffService.StaffCount();
            return count;
        } catch (Exception e) {
            throw new Exception("staffCountById error!");
        }
    }

    //根据StaffId查找StaffName
    @RequestMapping(value = "/staffName",params = {"staffId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String findStaffName(@RequestParam Integer staffId) throws Exception{
        try {
            return staffService.findAllStaffName(staffId).toString();
        } catch (Exception e) {
            throw new Exception("findStaffName error!");
        }
    }

    //根据工作人员Id删除工作人员信息
    @RequestMapping(value = "/staffId",params = {"staffId"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteStaffById(@RequestParam Integer staffId){
        try {
            staffService.deleteStaffById(staffId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //根据工作人员Name删除工作人员信息
    @RequestMapping(value = "/staffName",params = {"staffName"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteStaffById(@RequestParam String staffName){
        try {
            staffService.deleteStaffByName(staffName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有工作人员信息
    @RequestMapping(value = "/allStaff",method = RequestMethod.GET)
    @ResponseBody
    public String findAllStaff() throws Exception{
        try {
            return staffService.findAllStaff().toString();
        }catch (Exception e){
            throw new Exception("findAllStaff error!");
        }
    }


}
