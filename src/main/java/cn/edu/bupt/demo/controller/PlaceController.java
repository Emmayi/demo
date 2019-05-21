package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.dao.EmergencyPlace.PlaceService;
import cn.edu.bupt.demo.entity.EmergencyPlace;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/info")
@CrossOrigin
public class PlaceController {
    @Autowired
    PlaceService placeService;

    //配合分页设置，获取所有的物资信息
    @RequestMapping(value = "/placeByPage", params = {  "limit","page"  }, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPlaceByPage(@RequestParam int limit,
                                @RequestParam int page) throws Exception {
        try {
            return placeService.findAllByPage(page,limit).toString();

        } catch (Exception e) {
            throw new Exception("getPlaceByPage error!");
        }
    }

    //获取所有物资的页数
    @RequestMapping(value = "/placePage", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getPlacePages(@RequestParam int limit) throws Exception {
        try {
            return placeService.findPlacePageNum(limit);
        } catch (Exception e) {
            throw new Exception("getPlacePages error!");
        }
    }

    //根据id获取物资信息
    @RequestMapping(value = "/place",params = {"placeId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPlaceById(@RequestParam Integer placeId) throws Exception{
        try {
            return placeService.findPlaceById(placeId).toString();
        }catch (Exception e){
            throw new Exception("getPlaceById error!");
        }
    }

    //根据Name获取物资信息
    @RequestMapping(value = "/place",params = {"placeName"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPlaceByName(@RequestParam String placeName) throws Exception{
        try {
            return placeService.findPlaceByName(placeName).toString();
        }catch (Exception e){
            throw new Exception("getPlaceByName error!");
        }
    }

    //根据Affiliation获取物资信息
    @RequestMapping(value = "/place",params = {"affiliation"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPlaceByAffiliation(@RequestParam String affiliation) throws Exception{
        try {
            return placeService.findPlaceByAffiliation(affiliation).toString();
        }catch (Exception e){
            throw new Exception("getPlaceByAffiliation error!");
        }
    }

    //根据Location获取物资信息
    @RequestMapping(value = "/place",params = {"location"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPlaceByLocation(@RequestParam String location) throws Exception{
        try {
            return placeService.findPlaceByLocation(location).toString();
        }catch (Exception e){
            throw new Exception("getPlaceByLocation error!");
        }
    }

    //统计有多少
    @RequestMapping(value = "/placeCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getPlaceCount() throws Exception{
        try {
            Integer count = placeService.getPlaceCount();
            return count;
        }catch (Exception e){
            throw new Exception("getPlaceCount error!");
        }
    }

    //增加物资的信息
    @RequestMapping(value = "/place", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createPlace(@RequestBody String placeInfo) throws Exception{
        JsonObject placeString = new JsonParser().parse(placeInfo).getAsJsonObject();
        EmergencyPlace emergencyPlace = Json2Place(placeString);
        try {
            placeService.save(emergencyPlace);
            return emergencyPlace.toString();
        } catch (Exception e) {
            throw new Exception("createPlace error!");
        }
    }

    private EmergencyPlace Json2Place(JsonObject placeString) {
        EmergencyPlace emergencyPlace = new EmergencyPlace();
        emergencyPlace.setName(placeString.get("name").getAsString());
        emergencyPlace.setCategory(placeString.get("category").getAsString());
        emergencyPlace.setArea(placeString.get("area").getAsInt());
        emergencyPlace.setCapacity(placeString.get("capacity").getAsInt());
        emergencyPlace.setLocation(placeString.get("location").getAsString());
        emergencyPlace.setIntroduction(placeString.get("introduction").getAsString());
        emergencyPlace.setAffiliation(placeString.get("affiliation").getAsString());
        emergencyPlace.setPrincipal(placeString.get("principal").getAsString());
        emergencyPlace.setTelephone(placeString.get("telephone").getAsString());
        emergencyPlace.setCellphone(placeString.get("cellphone").getAsString());


        return emergencyPlace;
    }

    //更新
    @RequestMapping(value = "/place", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updatePlace(@RequestBody String placeInfo) throws Exception{
        JsonObject placeString = new JsonParser().parse(placeInfo).getAsJsonObject();
        if(placeString.get("place_id").getAsString().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }

        EmergencyPlace emergencyPlace = new EmergencyPlace();
        emergencyPlace.setName(placeString.get("name").getAsString());
        emergencyPlace.setCategory(placeString.get("category").getAsString());
        emergencyPlace.setArea(placeString.get("area").getAsInt());
        emergencyPlace.setCapacity(placeString.get("capacity").getAsInt());
        emergencyPlace.setLocation(placeString.get("location").getAsString());
        emergencyPlace.setIntroduction(placeString.get("introduction").getAsString());
        emergencyPlace.setAffiliation(placeString.get("affiliation").getAsString());
        emergencyPlace.setPrincipal(placeString.get("principal").getAsString());
        emergencyPlace.setTelephone(placeString.get("telephone").getAsString());
        emergencyPlace.setCellphone(placeString.get("cellphone").getAsString());



        try {
            placeService.update(emergencyPlace);
            return emergencyPlace.toString();
        } catch (Exception e) {
            throw new Exception("updatePlace error!");
        }
    }

    //通过Id删除信息
    @RequestMapping(value = "/place",params = {"id"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePlanById(@RequestParam Integer id){
        try {
            placeService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有的物资
    @RequestMapping(value = "/placeALL", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllPlace() throws Exception{
        try {
            return placeService.findAllPlace().toString();
        }catch (Exception e){
            throw new Exception("getAllPlace error!");
        }
    }
}
