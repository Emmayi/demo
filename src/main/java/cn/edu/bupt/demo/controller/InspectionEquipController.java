package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.dao.InspectionEquip.EquipService;
import cn.edu.bupt.demo.entity.InspectionEquip;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author zy
 * @date 2019/5/8 下午3:04
 */

@RestController
@RequestMapping("api/v1/info")
@CrossOrigin
public class InspectionEquipController {

    @Autowired
    private EquipService equipService;

    //通过Id查找信息
    @RequestMapping(value = "/equipPlanById",params = {"id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getInspecitonEquipById(@RequestParam Integer id) throws Exception{
        try {
            return equipService.findPlanById(id).toString();
        }catch (Exception e){
            throw new Exception("getInspecitonEquipById error!");
        }
    }

    //通过巡检人查找巡检计划的信息
    @RequestMapping(value = "/equipPlanByName",params = {"name"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getInspecitonEquipByName(@RequestParam String name) throws Exception{
        try {
            return equipService.findPlanByEquipName(name).toString();
        }catch (Exception e){
            throw new Exception("getInspecitonEquipByName error!");
        }
    }


    //统计一共有多少报告
    @RequestMapping(value = "/equipPlan", method = RequestMethod.GET)
    @ResponseBody
    public Integer getAllCount() throws Exception{
        try {
            Integer count = equipService.getAllCount();
            return count;
        } catch (Exception e) {
            throw new Exception("getAllCount error!");
        }
    }


    //创建巡检报告，填写信息
    @RequestMapping(value = "/equipPlan", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createInspectionEquip(@RequestBody String equipInfo) throws Exception{

        InspectionEquip inspectionEquip = JSONObject.parseObject(equipInfo, InspectionEquip.class);

        try {
            equipService.save(inspectionEquip);
            return inspectionEquip.toString();
        } catch (Exception e) {
            throw new Exception("createInspectionEquip error!");
        }
    }

    //更新巡检报告的信息
    @RequestMapping(value = "/equipPlan", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateInspectionEquip(@RequestBody String equipInfo) throws Exception{

        InspectionEquip inspectionPlan = JSONObject.parseObject(equipInfo, InspectionEquip.class);
        if(inspectionPlan.getId().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }

        try {
            equipService.update(inspectionPlan);
            return inspectionPlan.toString();
        } catch (Exception e) {
            throw new Exception("updateInspectionEquip error!");
        }
    }

    //根据Id删除巡检计划
    @RequestMapping(value = "/equipPlan",params = {"id"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteReportByInspectionPerson(@RequestParam Integer id){
        try {
            equipService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有巡检报告信息
    @RequestMapping(value = "/allEquipPlan",method = RequestMethod.GET)
    @ResponseBody
    public String findAllEquipPlan() throws Exception{
        try {
            return equipService.findAll().toString();
        }catch (Exception e){
            throw new Exception("findAllEquipPlan error!");
        }
    }

}
