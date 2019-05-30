package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.aop.MyLog;
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

    //分页接口配置，有筛选参数返回筛选参数的，没有则显示全部
    @RequestMapping(value = "/inspectionPlanByPage",  method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getInspectionReportByPage(@RequestParam (name="limit") int limit,
                                            @RequestParam (name="page") int page,
                                            @RequestParam(value="inspection_person",required=false,defaultValue = "1") String inspection_person )throws Exception {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("limit",limit);
            jsonObject.put("page",page);
            if(inspection_person.equals("1")){
                Integer count = planService.getAllCount();
                jsonObject.put("allCount",count);
                jsonObject.put("data",planService.findAllPlanByPage(page,limit));
                return jsonObject.toString();
            }else {
                Integer count = planService.findCountOfPerson(inspection_person);
                jsonObject.put("data",planService.findPlanByInspectionPerson(inspection_person,page,limit));
                jsonObject.put("allCount",count);
                return jsonObject.toString();
            }

        } catch (Exception e) {
            throw new Exception("getInspectionReportByPage error!");
        }
    }

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


    //统计一共有多少计划
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


    //创建巡检计划，填写信息
    @MyLog(value = "创建巡检计划")
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

    //更新巡检计划的信息
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

    //更新状态
    @RequestMapping(value = "/planStatus", params = {"id"},method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void updateInspectionPlan(@RequestParam Integer id) throws Exception{

        try {
            planService.updateStatus(id);
        } catch (Exception e) {
            throw new Exception("updateInspectionPlan error!");
        }
    }

    //根据巡检人员id删除巡检计划
    @MyLog(value = "删除巡检计划")
    @RequestMapping(value = "/plan",params = {"id"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteReportByInspectionPerson(@RequestParam Integer id){
        try {
            planService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有巡检计划信息
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
