package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.aop.MyLog;
import cn.edu.bupt.demo.dao.EntranceWork.EntranceService;
import cn.edu.bupt.demo.entity.EntranceWork;
import cn.edu.bupt.demo.entity.StaffNumber;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zy
 * @date 2018/10/24 下午8:30
 */

@RestController
@RequestMapping("/api/v1/info")
@CrossOrigin
public class EntranceWorkController {

    @Autowired
    EntranceService entranceService;

    //分页接口配置，有筛选参数返回筛选参数的，没有则显示全部
    @RequestMapping(value = "/entranceWorkByPage",  method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getEntranceWorkByPage(@RequestParam (name="limit") int limit,
                                        @RequestParam (name="page") int page,
                                        @RequestParam(value="range",required=false,defaultValue = "1") String range )throws Exception {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("limit",limit);
            jsonObject.put("page",page);
            if(range.equals("1")){
                Integer count = entranceService.allWorkCount();
                jsonObject.put("data",entranceService.findALlByPage(page,limit));
                jsonObject.put("allCount",count);
                return jsonObject.toString();
            }else {
                Integer count = entranceService.workCountByRange(range);
                jsonObject.put("data",entranceService.findEntranceWorkByRange(range,page,limit));
                jsonObject.put("allCount",count);
                return jsonObject.toString();
            }

        } catch (Exception e) {
            throw new Exception("getEntranceWorkByPage error!");
        }
    }


    //根据入廊作业id获取入廊信息
    @RequestMapping(value = "/entranceWorkById",params = {"entranceId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getEntranceWorkById(@RequestParam Integer entranceId) throws Exception{
        try {
            return entranceService.findEntranceWorkById(entranceId).toString();
        }catch (Exception e){
            throw new Exception("getEntranceWorkById error!");
        }
    }



    //增加入廊作业的信息
    @MyLog(value = "添加新的入廊作业")
    @RequestMapping(value = "/entranceWork", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createEntranceWork(@RequestBody String workInfo) throws Exception{

        EntranceWork entranceWork = JSONObject.parseObject(workInfo, EntranceWork.class);

        try {
            entranceService.save(entranceWork);
            return entranceWork.toString();
        } catch (Exception e) {
            throw new Exception("createEntranceWork error!");
        }
    }

    //更新入廊信息
    @MyLog(value = "更新入廊作业")
    @RequestMapping(value = "/entranceWork", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateEntranceWork(@RequestBody String workInfo) throws Exception{
        EntranceWork entranceWork = JSONObject.parseObject(workInfo, EntranceWork.class);

        if(entranceWork.getId().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }

        if (entranceWork.getEvaluation() != null) {
            entranceWork.setEvaluation(entranceWork.getEvaluation());
        }
        try {
            entranceService.update(entranceWork);
            return entranceWork.toString();
        } catch (Exception e) {
            throw new Exception("createEntranceWork error!");
        }
    }

    //添加评价
    @MyLog(value = "为入廊作业添加评价")
    @RequestMapping(value = "/entranceWorkEvaluation", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateEvaluation(@RequestBody String evaluationString) throws Exception{
        JsonObject evaluationStr = new JsonParser().parse(evaluationString).getAsJsonObject();
        Integer id = evaluationStr.get("id").getAsInt();
        String evaluation = evaluationStr.get("evaluation").getAsString();
        if(id.equals("")||evaluation == null) {
            throw new RuntimeException("没有Id或者内容为空，无法更新!");
        }
        try {
            entranceService.evaluation( evaluation, id);
            return "id = "+id+", "+"evaluation = "+evaluation;
        } catch (Exception e) {
            throw new Exception("createEntranceWork error!");
        }
    }

    //通过Id删除信息
    @MyLog(value = "删除入廊作业")
    @RequestMapping(value = "/entranceWorkById",params = {"WorkId"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteWorkById(@RequestParam Integer WorkId){
        try {
            entranceService.deleteById(WorkId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //通过Date删除信息
    @RequestMapping(value = "/entranceWorkByDate",params = {"date"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteWorkByDate(@RequestParam Long date){
        try {
            entranceService.deleteByDate(date);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有的入廊作业
    @RequestMapping(value = "/entranceWork", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllEntranceWork() throws Exception{
        try {
            return entranceService.findAllWork().toString();
        }catch (Exception e){
            throw new Exception("getEntranceWorkCount error!");
        }
    }

}
