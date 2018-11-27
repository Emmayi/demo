package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.dao.EmergencySupplies.SuppliesService;
import cn.edu.bupt.demo.entity.EmergencySupplies;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author zy
 * @date 2018/11/26 下午7:06
 */

@RestController
@RequestMapping("/api/v1/info")
@CrossOrigin
public class SuppliesController {

    @Autowired
    SuppliesService suppliesService;

    //配合分页设置，获取所有的物资信息
    @RequestMapping(value = "/suppliesByPage", params = {  "limit","page"  }, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getSuppliesByPage(@RequestParam int limit,
                                @RequestParam int page) throws Exception {
        try {
            return suppliesService.findAllByPage(page,limit).toString();

        } catch (Exception e) {
            throw new Exception("getSuppliesByPage error!");
        }
    }

    //获取所有物资的页数
    @RequestMapping(value = "/suppliesPage", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getSuppliesPages(@RequestParam int limit) throws Exception {
        try {
            return suppliesService.findSuppliesPageNum(limit);
        } catch (Exception e) {
            throw new Exception("getSuppliesPages error!");
        }
    }

    //根据id获取物资信息
    @RequestMapping(value = "/supplies",params = {"supplyId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getSupplyById(@RequestParam Integer supplyId) throws Exception{
        try {
            return suppliesService.findSuppliesById(supplyId).toString();
        }catch (Exception e){
            throw new Exception("getSupplyById error!");
        }
    }

    //根据Name获取物资信息
    @RequestMapping(value = "/supplies",params = {"supplyName"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getSupplyByName(@RequestParam String supplyName) throws Exception{
        try {
            return suppliesService.findSuppliesByName(supplyName).toString();
        }catch (Exception e){
            throw new Exception("getSupplyByName error!");
        }
    }

    //根据Affiliation获取物资信息
    @RequestMapping(value = "/supplies",params = {"affiliation"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getSupplyByAffiliation(@RequestParam String affiliation) throws Exception{
        try {
            return suppliesService.findSuppliesByAffiliation(affiliation).toString();
        }catch (Exception e){
            throw new Exception("getSupplyByAffiliation error!");
        }
    }

    //根据Location获取物资信息
    @RequestMapping(value = "/supplies",params = {"location"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getSupplyByLocation(@RequestParam String location) throws Exception{
        try {
            return suppliesService.findSuppliesByLocation(location).toString();
        }catch (Exception e){
            throw new Exception("getSupplyByLocation error!");
        }
    }

    //统计有多少
    @RequestMapping(value = "/suppliesCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getSuppliesCount() throws Exception{
        try {
            Integer count = suppliesService.getSuppliesCount();
            return count;
        }catch (Exception e){
            throw new Exception("getSuppliesCount error!");
        }
    }

    //增加物资的信息
    @RequestMapping(value = "/supplies", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createSupply(@RequestBody String supplyInfo) throws Exception{
        JsonObject supplyString = new JsonParser().parse(supplyInfo).getAsJsonObject();
        EmergencySupplies emergencySupplies = Json2Supply(supplyString);
        try {
            suppliesService.save(emergencySupplies);
            return emergencySupplies.toString();
        } catch (Exception e) {
            throw new Exception("createSupply error!");
        }
    }

    private EmergencySupplies Json2Supply(JsonObject supplyString) {
        EmergencySupplies emergencySupplies = new EmergencySupplies();
        emergencySupplies.setName(supplyString.get("name").getAsString());
        emergencySupplies.setCategory(supplyString.get("category").getAsString());
        emergencySupplies.setQuantity(supplyString.get("quantity").getAsInt());
        emergencySupplies.setModel(supplyString.get("model").getAsString());
        emergencySupplies.setPurchase_date(supplyString.get("purchase_date").getAsLong());
        emergencySupplies.setManufacturer(supplyString.get("manufacturer").getAsString());
        emergencySupplies.setManufacture_date(supplyString.get("manufacture_date").getAsLong());
        emergencySupplies.setValid_until(supplyString.get("valid_until").getAsLong());
        emergencySupplies.setUse_description(supplyString.get("use_description").getAsString());
        emergencySupplies.setPerformance_description(supplyString.get("performance_description").getAsString());
        emergencySupplies.setAffiliation(supplyString.get("affiliation").getAsString());
        emergencySupplies.setLocation(supplyString.get("location").getAsString());

        return emergencySupplies;
    }

    //更新
    @RequestMapping(value = "/supplies", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateSupply(@RequestBody String supplyInfo) throws Exception{
        JsonObject supplyString = new JsonParser().parse(supplyInfo).getAsJsonObject();
        if(supplyString.get("supply_id").getAsString().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }

        EmergencySupplies emergencySupplies = new EmergencySupplies();
        emergencySupplies.setName(supplyString.get("name").getAsString());
        emergencySupplies.setCategory(supplyString.get("category").getAsString());
        emergencySupplies.setQuantity(supplyString.get("quantity").getAsInt());
        emergencySupplies.setModel(supplyString.get("model").getAsString());
        emergencySupplies.setPurchase_date(supplyString.get("purchase_date").getAsLong());
        emergencySupplies.setManufacturer(supplyString.get("manufacturer").getAsString());
        emergencySupplies.setManufacture_date(supplyString.get("manufacture_date").getAsLong());
        emergencySupplies.setValid_until(supplyString.get("valid_until").getAsLong());
        emergencySupplies.setUse_description(supplyString.get("use_description").getAsString());
        emergencySupplies.setPerformance_description(supplyString.get("performance_description").getAsString());
        emergencySupplies.setAffiliation(supplyString.get("affiliation").getAsString());
        emergencySupplies.setLocation(supplyString.get("location").getAsString());


        try {
            suppliesService.update(emergencySupplies);
            return emergencySupplies.toString();
        } catch (Exception e) {
            throw new Exception("updateSupply error!");
        }
    }

    //通过Id删除信息
    @RequestMapping(value = "/supplies",params = {"id"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePlanById(@RequestParam Integer id){
        try {
            suppliesService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有的物资
    @RequestMapping(value = "/suppliesALL", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllSupplies() throws Exception{
        try {
            return suppliesService.findAllSupplies().toString();
        }catch (Exception e){
            throw new Exception("getAllSupplies error!");
        }
    }


}
