package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.dao.EmergencyPlan.EmergencyService;
import cn.edu.bupt.demo.entity.EmergencyPlan;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
public class EmergencyPlanController {

    private String storePath= "/home/zy/file";//存放上传的文件路径

    @Autowired
    EmergencyService emergencyService;

    //配合分页设置，获取所有的应急预案信息
    @RequestMapping(value = "/emergencyByPage", params = {  "limit","page"  }, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPlanByPage(@RequestParam int limit,
                                        @RequestParam int page) throws Exception {
        try {
            return emergencyService.findPlanByPage(page,limit).toString();

        } catch (Exception e) {
            throw new Exception("getPlanByPage error!");
        }
    }

    //获取所有应急预案的页数
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
    @RequestMapping(value = "/emergencyById",params = {"emergencyId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPlanById(@RequestParam Integer emergencyId) throws Exception{
        try {
            return emergencyService.findPlanById(emergencyId).toString();
        }catch (Exception e){
            throw new Exception("getPlanById error!");
        }
    }

    //根据应急预案name获取应急预案
    @RequestMapping(value = "/emergencyByName",params = {"name"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPlanByName(@RequestParam String name) throws Exception{
        try {
            return emergencyService.findPlanByName(name).toString();
        }catch (Exception e){
            throw new Exception("getPlanByName error!");
        }
    }

    //根据level获取应急预案
    @RequestMapping(value = "/emergencyByLevel",params = {"level"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPlanByLevel(@RequestParam Integer level) throws Exception{
        try {
            return emergencyService.findPlanByLevel(level).toString();
        }catch (Exception e){
            throw new Exception("getPlanByLevel error!");
        }
    }

    //根据signer获取应急预案
    @RequestMapping(value = "/emergencyBySigner",params = {"signer"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPlanBySigner(@RequestParam String signer) throws Exception{
        try {
            return emergencyService.findPlanBySigner(signer).toString();
        }catch (Exception e){
            throw new Exception("getPlanBySigner error!");
        }
    }

    //统计有多少预案
    @RequestMapping(value = "/emergencyCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getEPlanCount() throws Exception{
        try {
            Integer count = emergencyService.getPlanCount();
            return count;
        }catch (Exception e){
            throw new Exception("getEPlanCount error!");
        }
    }

    //增加应急预案的信息
    @RequestMapping(value = "/emergency", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createEmergencyPlan(@RequestBody String emergencyInfo) throws Exception{
        JsonObject planString = new JsonParser().parse(emergencyInfo).getAsJsonObject();
        EmergencyPlan emergencyPlan = Json2Plan(planString);
        try {
            emergencyService.save(emergencyPlan);
            return emergencyPlan.toString();
        } catch (Exception e) {
            throw new Exception("createEmergencyPlan error!");
        }
    }

    //更新应急预案
    @RequestMapping(value = "/emergency", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateEmergencyPlan(@RequestBody String emergencyInfo) throws Exception{
        JsonObject planString = new JsonParser().parse(emergencyInfo).getAsJsonObject();
        if(planString.get("emergency_id").getAsString().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }
        EmergencyPlan emergencyPlan = new EmergencyPlan();
        emergencyPlan.setName(planString.get("name").getAsString());
        emergencyPlan.setCategory(planString.get("category").getAsString());
        emergencyPlan.setLevel(planString.get("level").getAsInt());
        emergencyPlan.setAssociated_event_type(planString.get("associated_event_type").getAsString());
        emergencyPlan.setContent(planString.get("content").getAsString());
        emergencyPlan.setDepartment(planString.get("department").getAsString());
        emergencyPlan.setRelease_date(planString.get("release_date").getAsLong());
        emergencyPlan.setDepartment(planString.get("department").getAsString());
        emergencyPlan.setIssued(planString.get("issued").getAsString());
        emergencyPlan.setSigner(planString.get("signer").getAsString());
        emergencyPlan.setFile(planString.get("file").getAsString());
        try {
            emergencyService.update(emergencyPlan);
            return emergencyPlan.toString();
        } catch (Exception e) {
            throw new Exception("updateEmergencyPlan error!");
        }
    }

    //通过Id删除信息
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
    @RequestMapping(value = "/emergencyALL", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllPlan() throws Exception{
        try {
            return emergencyService.findAllPlan().toString();
        }catch (Exception e){
            throw new Exception("getAllPlan error!");
        }
    }

    private EmergencyPlan Json2Plan(JsonObject planString) {
        EmergencyPlan emergencyPlan = new EmergencyPlan();
        emergencyPlan.setName(planString.get("name").getAsString());
        emergencyPlan.setCategory(planString.get("category").getAsString());
        emergencyPlan.setLevel(planString.get("level").getAsInt());
        emergencyPlan.setAssociated_event_type(planString.get("associated_event_type").getAsString());
        emergencyPlan.setContent(planString.get("content").getAsString());
        emergencyPlan.setDepartment(planString.get("department").getAsString());
        emergencyPlan.setRelease_date(planString.get("release_date").getAsLong());
        emergencyPlan.setDepartment(planString.get("department").getAsString());
        emergencyPlan.setIssued(planString.get("issued").getAsString());
        emergencyPlan.setSigner(planString.get("signer").getAsString());
        emergencyPlan.setFile(planString.get("file").getAsString());

        return emergencyPlan;
    }


}
