package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.dao.EmergencyEquis.EquisService;
import cn.edu.bupt.demo.entity.EmergencyEquis;
import com.alibaba.fastjson.JSONObject;
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

/*    //配合分页设置，获取所有的设备信息
    @RequestMapping(value = "/equisByPage", params = {  "limit","page"  }, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getEquisByPage(@RequestParam int limit,
                                    @RequestParam int page) throws Exception {
        try {
            return equisService.findAllByPage(page,limit).toString();

        } catch (Exception e) {
            throw new Exception("getEquisByPage error!");
        }
    }*/

    //分页接口配置，有筛选参数返回筛选参数的，没有则显示全部
    @RequestMapping(value = "/emergencyEquisByPage",  method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getInspectionEquisByPage(@RequestParam (name="limit") int limit,
                                           @RequestParam (name="page") int page,
                                           @RequestParam(value="category",required=false,defaultValue = "1") String category )throws Exception {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("limit",limit);
            jsonObject.put("page",page);

            if(category.equals("1")){
                Integer count = equisService.getEquisCount();
                jsonObject.put("allCount",count);
                jsonObject.put("data",equisService.findAllByPage(page,limit));
                return jsonObject.toString();
            }else {
                Integer count = equisService.EquisCountOfCategory(category);
                jsonObject.put("data",equisService.findEquisByCategoryAndPage(category,page,limit));
                jsonObject.put("allCount",count);
                return jsonObject.toString();
            }

        } catch (Exception e) {
            throw new Exception("getInspectionEquisByPage error!");
        }
    }

    //获取所有设备的页数
    @RequestMapping(value = "/equisPage", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getEquisPages(@RequestParam int limit) throws Exception {
        try {
            return equisService.findEquisPageNum(limit);
        } catch (Exception e) {
            throw new Exception("getEquisPages error!");
        }
    }

    //根据id获取设备信息
    @RequestMapping(value = "/equis",params = {"equisId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getEquisById(@RequestParam Integer equisId) throws Exception{
        try {
            return equisService.findEquisById(equisId).toString();
        }catch (Exception e){
            throw new Exception("getEquisById error!");
        }
    }

/*    //根据Name获取设备信息
    @RequestMapping(value = "/equis",params = {"equisName"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getEquisByName(@RequestParam String equisName) throws Exception{
        try {
            return equisService.findEquisByName(equisName).toString();
        }catch (Exception e){
            throw new Exception("getEquisByName error!");
        }
    }

    //根据Affiliation获取设备信息
    @RequestMapping(value = "/equis",params = {"affiliation"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getEquisByAffiliation(@RequestParam String affiliation) throws Exception{
        try {
            return equisService.findEquisByAffiliation(affiliation).toString();
        }catch (Exception e){
            throw new Exception("getEquisByAffiliation error!");
        }
    }

    //根据Location获取设备信息
    @RequestMapping(value = "/equis",params = {"location"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getEquisByLocation(@RequestParam String location) throws Exception{
        try {
            return equisService.findEquisByLocation(location).toString();
        }catch (Exception e){
            throw new Exception("getEquisByLocation error!");
        }
    }*/

/*    //统计有多少
    @RequestMapping(value = "/equisCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getEquisCount() throws Exception{
        try {
            Integer count = equisService.getEquisCount();
            return count;
        }catch (Exception e){
            throw new Exception("getEquisCount error!");
        }
    }*/

    //增加设备的信息
    @RequestMapping(value = "/equis", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createEquis(@RequestBody String equisInfo) throws Exception{
        EmergencyEquis emergencyEquis = JSONObject.parseObject(equisInfo,EmergencyEquis.class);
        try {
            equisService.save(emergencyEquis);
            return emergencyEquis.toString();
        } catch (Exception e) {
            throw new Exception("createEquis error!");
        }
    }



    //更新
    @RequestMapping(value = "/equis", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateEquis(@RequestBody String equisInfo) throws Exception{
        EmergencyEquis emergencyEquis = JSONObject.parseObject(equisInfo,EmergencyEquis.class);
        if(emergencyEquis.getEquis_id().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }
        try {
            equisService.update(emergencyEquis);
            return emergencyEquis.toString();
        } catch (Exception e) {
            throw new Exception("updateEquis error!");
        }
 }

    //通过Id删除信息
    @RequestMapping(value = "/equis",params = {"equisId"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteEquisById(@RequestParam Integer equisId){
        try {
            equisService.deleteById(equisId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

/*    //获取所有的设备
    @RequestMapping(value = "/equisALL", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllEquis() throws Exception{
        try {
            return equisService.findAllEquis().toString();
        }catch (Exception e){
            throw new Exception("getAllEquis error!");
        }
    }*/


}
