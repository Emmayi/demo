package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.dao.DaliyInspection.DaliyService;
import cn.edu.bupt.demo.entity.DailyInspection;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
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
@Api(description= "日常巡检")
public class DaliyController {
    @Autowired
    private DaliyService daliyService;

    //分页接口配置，有筛选参数返回筛选参数的，没有则显示全部
    @RequestMapping(value = "/dailyInspectionByPage",  method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getDailyInspectionTableByPage(@RequestParam (name="limit") int limit,
                                        @RequestParam (name="page") int page,
                                        @RequestParam(value="person",required=false,defaultValue = "1") String person )throws Exception {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("limit",limit);
            jsonObject.put("page",page);
            if(person.equals("1")){
                Integer count = daliyService.getAllCount();
                jsonObject.put("allCount",count);
                jsonObject.put("data",daliyService.findAllByPage(page,limit));
                return jsonObject.toString();
            }else {
                Integer count = daliyService.findCountByPerson(person);
                jsonObject.put("data",daliyService.findTableByInspectionPerson(person,page,limit));
                jsonObject.put("allCount",count);
                return jsonObject.toString();
            }

        } catch (Exception e) {
            throw new Exception("getDailyInspectionTableByPage error!");
        }
    }


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


    //根据ID删除
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
