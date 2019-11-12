package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.annotation.Auth;
import cn.edu.bupt.demo.aop.MyLog;
import cn.edu.bupt.demo.dao.EmergencyPlan.EmergencyRepository;
import cn.edu.bupt.demo.dao.EmergencyPlan.EmergencyService;
import cn.edu.bupt.demo.entity.EmergencyPlan;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * @author zy
 * @date 2018/11/24 下午11:04
 */

@RestController
@RequestMapping("/api/v1/info")
@CrossOrigin
@Api(description= "应急预案")
public class EmergencyPlanController {

    private String storePath= "/home/zy/file";//存放上传的文件路径

    @Autowired
    EmergencyService emergencyService;
    @Autowired
    EmergencyRepository emergencyRepository;

    //分页接口配置，有筛选参数返回筛选参数的，没有则显示全部
    @Auth(roles = {"BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/emergencyPlanByPage",  method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getEmergencyPlanByPage(@RequestParam (name="limit") int limit,
                                        @RequestParam (name="page") int page,
                                        @RequestParam(value="level",required=false,defaultValue = "0") Integer level )throws Exception {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("limit",limit);
            jsonObject.put("page",page);
            if(level==0){
                Integer count = emergencyService.getPlanCount();
                jsonObject.put("allCount",count);
                jsonObject.put("data",emergencyService.findPlanByPage(page,limit));
                return jsonObject.toString();
            }else {
                Integer count = emergencyRepository.AllPlanPageCount(level);
                jsonObject.put("data",emergencyRepository.findPlanByLevelPage(level,page,limit));
                jsonObject.put("allCount",count);
                return jsonObject.toString();
            }

        } catch (Exception e) {
            throw new Exception("getEmergencyPlanByPage error!");
        }
    }


    //获取所有应急预案的页数
    @Auth(roles = {"BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/emergencyPages", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getPlanPages(@RequestParam int limit) throws Exception {
        try {
            return emergencyService.findPlanPageNum(limit);
        } catch (Exception e) {
            throw new Exception("getPlanPages error!");
        }
    }

    //根据应急预案id获取应急预案
    @Auth(roles = {"BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/emergencyById",params = {"emergencyId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPlanById(@RequestParam Integer emergencyId) throws Exception{
        try {
            return emergencyService.findPlanById(emergencyId).toString();
        }catch (Exception e){
            throw new Exception("getPlanById error!");
        }
    }

    //根据level获取应急预案
    @Auth(roles = {"BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/emergencyByLevel",params = {"level"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPlanByLevel(@RequestParam Integer level) throws Exception{
        try {
            return emergencyService.findPlanByLevel(level).toString();
        }catch (Exception e){
            throw new Exception("getPlanByLevel error!");
        }
    }

    //增加应急预案的信息
    @Auth(roles = {"BranchDispatcher","BranchMonitor"})
    @MyLog(value = "添加新的应急预案")
    @RequestMapping(value = "/emergency", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createEmergencyPlan(@RequestBody String emergencyInfo) throws Exception{
        EmergencyPlan emergencyPlan = JSONObject.parseObject(emergencyInfo, EmergencyPlan.class);
        try {
            emergencyService.save(emergencyPlan);
            return emergencyPlan.toString();
        } catch (Exception e) {
            throw new Exception("createEmergencyPlan error!");
        }
    }

    //更新应急预案
    @Auth(roles = {"BranchDispatcher","BranchMonitor"})
    @MyLog(value = "更新应急预案")
    @RequestMapping(value = "/emergency", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateEmergencyPlan(@RequestBody String emergencyInfo) throws Exception{
        EmergencyPlan emergencyPlan = JSONObject.parseObject(emergencyInfo, EmergencyPlan.class);

        if(emergencyPlan.getEmergency_id().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }
        try {
            emergencyService.update(emergencyPlan);
            return emergencyPlan.toString();
        } catch (Exception e) {
            throw new Exception("updateEmergencyPlan error!");
        }
    }

    //通过Id删除信息
    @Auth(roles = {"BranchDispatcher"})
    @MyLog(value = "删除应急预案")
    @RequestMapping(value = "/emergency",params = {"id"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePlanById(@RequestParam Integer id){
        try {
            emergencyService.deleteById(id);
            File file = new File(storePath+"/"+id);
            if(file.exists()){
                System.out.println(file.delete());
            }else {
                System.out.println("文件夹不存在！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有的应急预案
    @Auth(roles = {"BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/emergencyALL", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllPlan() throws Exception{
        try {
            return emergencyService.findAllPlan().toString();
        }catch (Exception e){
            throw new Exception("getAllPlan error!");
        }
    }

}
