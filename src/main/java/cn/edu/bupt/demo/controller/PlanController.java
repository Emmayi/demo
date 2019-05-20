package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.dao.InspectionPlan.PlanService;
import cn.edu.bupt.demo.entity.InspectionPlan;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author zy
 * @date 2019/4/19 上午9:56
 */

@RestController
@RequestMapping("api/v1/info")
@CrossOrigin
public class PlanController {

    @Autowired
    private PlanService planService;

    //通过Id查找巡检计划的信息
    @RequestMapping(value = "/planById",params = {"id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPlanById(@RequestParam Integer id) throws Exception{
        try {
            return planService.findPlanById(id).toString();
        }catch (Exception e){
            throw new Exception("getPlanById error!");
        }
    }

    //通过巡检人查找巡检计划的信息
    @RequestMapping(value = "/planByPerson",params = {"person"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPlanByInspectionPerson(@RequestParam String person) throws Exception{
        try {
            return planService.findPlanByInspectionPerson(person).toString();
        }catch (Exception e){
            throw new Exception("getPlanByInspectionPerson error!");
        }
    }

    //通过巡检日期查找巡检报告信息
    @RequestMapping(value = "/planByInspectionDate",params = {"date"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPlanByInspectionDate(@RequestParam Long date) throws Exception{
        try {
            return planService.findPlanByInspectionDate(date).toString();
        }catch (Exception e){
            throw new Exception("getPlanByInspectionDate error!");
        }
    }


    //统计一共有多少报告
    @RequestMapping(value = "/plan", method = RequestMethod.GET)
    @ResponseBody
    public Integer getAllCount() throws Exception{
        try {
            Integer count = planService.getAllCount();
            return count;
        } catch (Exception e) {
            throw new Exception("getAllCount error!");
        }
    }


    //创建巡检报告，填写信息
    @RequestMapping(value = "/plan", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createInspectionPlan(@RequestBody String reportInfo) throws Exception{

        InspectionPlan inspectionPlan = JSONObject.parseObject(reportInfo, InspectionPlan.class);

        try {
            planService.save(inspectionPlan);
            return inspectionPlan.toString();
        } catch (Exception e) {
            throw new Exception("createInspectionPlan error!");
        }
    }

    //更新巡检报告的信息
    @RequestMapping(value = "/plan", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateInspectionPlan(@RequestBody String reportInfo) throws Exception{

        InspectionPlan inspectionPlan = JSONObject.parseObject(reportInfo, InspectionPlan.class);

        if(inspectionPlan.getId().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }

        try {
            planService.update(inspectionPlan);
            return inspectionPlan.toString();
        } catch (Exception e) {
            throw new Exception("updateInspectionPlan error!");
        }
    }

    //根据巡检人员Name删除巡检计划
    @RequestMapping(value = "/plan",params = {"inspectionPerson"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteReportByInspectionPerson(@RequestParam String inspectionPerson){
        try {
            planService.deleteByInspectionPerson(inspectionPerson);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有巡检报告信息
    @RequestMapping(value = "/allPlan",method = RequestMethod.GET)
    @ResponseBody
    public String findAllReport() throws Exception{
        try {
            return planService.findAll().toString();
        }catch (Exception e){
            throw new Exception("findAllReport error!");
        }
    }


}
