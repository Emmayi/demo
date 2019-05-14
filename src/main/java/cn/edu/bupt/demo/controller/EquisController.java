package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.dao.EmergencyEquis.EquisService;
import cn.edu.bupt.demo.entity.EmergencyEquis;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/info")
@CrossOrigin
public class EquisController {

    @Autowired
    EquisService equisService;

    //配合分页设置，获取所有的物资信息
    @RequestMapping(value = "/equisByPage", params = {  "limit","page"  }, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getEquisByPage(@RequestParam int limit,
                                    @RequestParam int page) throws Exception {
        try {
            return equisService.findAllByPage(page,limit).toString();

        } catch (Exception e) {
            throw new Exception("getEquisByPage error!");
        }
    }

    //获取所有物资的页数
    @RequestMapping(value = "/equisPage", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getEquisPages(@RequestParam int limit) throws Exception {
        try {
            return equisService.findEquisPageNum(limit);
        } catch (Exception e) {
            throw new Exception("getEquisPages error!");
        }
    }

    //根据id获取物资信息
    @RequestMapping(value = "/equis",params = {"equisId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getEquisById(@RequestParam Integer equisId) throws Exception{
        try {
            return equisService.findEquisById(equisId).toString();
        }catch (Exception e){
            throw new Exception("getEquisById error!");
        }
    }

    //根据Name获取物资信息
    @RequestMapping(value = "/equis",params = {"equisName"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getEquisByName(@RequestParam String equisName) throws Exception{
        try {
            return equisService.findEquisByName(equisName).toString();
        }catch (Exception e){
            throw new Exception("getEquisByName error!");
        }
    }

    //根据Affiliation获取物资信息
    @RequestMapping(value = "/equis",params = {"affiliation"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getEquisByAffiliation(@RequestParam String affiliation) throws Exception{
        try {
            return equisService.findEquisByAffiliation(affiliation).toString();
        }catch (Exception e){
            throw new Exception("getEquisByAffiliation error!");
        }
    }

    //根据Location获取物资信息
    @RequestMapping(value = "/equis",params = {"location"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getEquisByLocation(@RequestParam String location) throws Exception{
        try {
            return equisService.findEquisByLocation(location).toString();
        }catch (Exception e){
            throw new Exception("getEquisByLocation error!");
        }
    }

    //统计有多少
    @RequestMapping(value = "/equisCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getEquisCount() throws Exception{
        try {
            Integer count = equisService.getEquisCount();
            return count;
        }catch (Exception e){
            throw new Exception("getEquisCount error!");
        }
    }

    //增加物资的信息
    @RequestMapping(value = "/equis", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createEquis(@RequestBody String equisInfo) throws Exception{
        JsonObject equisString = new JsonParser().parse(equisInfo).getAsJsonObject();
        EmergencyEquis emergencyEquis = Json2Equis(equisString);
        try {
            equisService.save(emergencyEquis);
            return emergencyEquis.toString();
        } catch (Exception e) {
            throw new Exception("createEquis error!");
        }
    }

    private EmergencyEquis Json2Equis(JsonObject equisString) {
        EmergencyEquis emergencyEquis = new EmergencyEquis();
        emergencyEquis.setName(equisString.get("name").getAsString());
        emergencyEquis.setCategory(equisString.get("category").getAsString());
        emergencyEquis.setQuantity(equisString.get("quantity").getAsInt());
        emergencyEquis.setModel(equisString.get("model").getAsString());
        emergencyEquis.setPurchase_date(equisString.get("purchase_date").getAsLong());
        emergencyEquis.setManufacturer(equisString.get("manufacturer").getAsString());
        emergencyEquis.setManufacture_date(equisString.get("manufacture_date").getAsLong());
        emergencyEquis.setValid_until(equisString.get("valid_until").getAsLong());
        emergencyEquis.setUse_description(equisString.get("use_description").getAsString());
        emergencyEquis.setPerformance_description(equisString.get("performance_description").getAsString());
        emergencyEquis.setAffiliation(equisString.get("affiliation").getAsString());
        emergencyEquis.setLocation(equisString.get("location").getAsString());

        return emergencyEquis;
    }

    //更新
    @RequestMapping(value = "/equis", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateEquis(@RequestBody String equisInfo) throws Exception{
        JsonObject equisString = new JsonParser().parse(equisInfo).getAsJsonObject();
        if(equisString.get("equis_id").getAsString().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }

        EmergencyEquis emergencyEquis = new EmergencyEquis();
        emergencyEquis.setEquis_id(equisString.get("equis_id").getAsInt());
        emergencyEquis.setName(equisString.get("name").getAsString());
        emergencyEquis.setCategory(equisString.get("category").getAsString());
        emergencyEquis.setQuantity(equisString.get("quantity").getAsInt());
        emergencyEquis.setModel(equisString.get("model").getAsString());
        emergencyEquis.setPurchase_date(equisString.get("purchase_date").getAsLong());
        emergencyEquis.setManufacturer(equisString.get("manufacturer").getAsString());
        emergencyEquis.setManufacture_date(equisString.get("manufacture_date").getAsLong());
        emergencyEquis.setValid_until(equisString.get("valid_until").getAsLong());
        emergencyEquis.setUse_description(equisString.get("use_description").getAsString());
        emergencyEquis.setPerformance_description(equisString.get("performance_description").getAsString());
        emergencyEquis.setAffiliation(equisString.get("affiliation").getAsString());
        emergencyEquis.setLocation(equisString.get("location").getAsString());


        try {
            equisService.update(emergencyEquis);
            return emergencyEquis.toString();
        } catch (Exception e) {
            throw new Exception("updateEquis error!");
        }
    }

    //通过Id删除信息
    @RequestMapping(value = "/equis",params = {"id"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteEquisById(@RequestParam Integer id){
        try {
            equisService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有的物资
    @RequestMapping(value = "/equisALL", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllEquis() throws Exception{
        try {
            return equisService.findAllEquis().toString();
        }catch (Exception e){
            throw new Exception("getAllEquis error!");
        }
    }


}
