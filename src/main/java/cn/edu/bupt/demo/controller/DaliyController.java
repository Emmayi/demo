package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.dao.DaliyInspection.DaliyService;
import cn.edu.bupt.demo.entity.DailyInspection;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author zy
 * @date 2019/4/26 上午11:02
 */

@RestController
@RequestMapping("api/v1/info")
@CrossOrigin
public class DaliyController {
    @Autowired
    private DaliyService daliyService;

    //通过Id查找日常巡检表的信息
    @RequestMapping(value = "/dailyById",params = {"id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTableById(@RequestParam Integer id) throws Exception{
        try {
            return daliyService.findTableById(id).toString();
        }catch (Exception e){
            throw new Exception("getTableById error!");
        }
    }

    //通过巡检人查找table的信息
    @RequestMapping(value = "/dailyByPerson",params = {"person"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTableByInspectionPerson(@RequestParam String person) throws Exception{
        try {
            return daliyService.findTableByInspectionPerson(person).toString();
        }catch (Exception e){
            throw new Exception("getTableByInspectionPerson error!");
        }
    }

    //通过id查找table信息
    @RequestMapping(value = "/dailyByTime",params = {"time"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTableByInspectionDate(@RequestParam Long time) throws Exception{
        try {
            return daliyService.findTableByInspectionDate(time).toString();
        }catch (Exception e){
            throw new Exception("getTableByInspectionDate error!");
        }
    }


    //统计一共有多少table
    @RequestMapping(value = "/daily", method = RequestMethod.GET)
    @ResponseBody
    public Integer getAllCount() throws Exception{
        try {
            Integer count = daliyService.getAllCount();
            return count;
        } catch (Exception e) {
            throw new Exception("getAllCount error!");
        }
    }


    //创建table，填写信息
    @RequestMapping(value = "/daily", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createDailyInspection(@RequestBody String dailyInfo) throws Exception{

        DailyInspection dailyInspection = JSONObject.parseObject(dailyInfo, DailyInspection.class);

        try {
            daliyService.save(dailyInspection);
            return dailyInspection.toString();
        } catch (Exception e) {
            throw new Exception("createDailyInspection error!");
        }
    }

    //更新table的信息
    @RequestMapping(value = "/daily", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateDailyInspection(@RequestBody String dailyInfo) throws Exception{

        DailyInspection dailyInspection = JSONObject.parseObject(dailyInfo, DailyInspection.class);

        if(dailyInspection.getId().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }

        try {
            daliyService.update(dailyInspection);
            return dailyInspection.toString();
        } catch (Exception e) {
            throw new Exception("updateDailyInspection error!");
        }
    }


    @RequestMapping(value = "/daily",params = {"id"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteTableById(@RequestParam Integer id){
        try {
            daliyService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有table信息
    @RequestMapping(value = "/allTable",method = RequestMethod.GET)
    @ResponseBody
    public String findAllTable() throws Exception{
        try {
            return daliyService.findAll().toString();
        }catch (Exception e){
            throw new Exception("findAllTable error!");
        }
    }

}
