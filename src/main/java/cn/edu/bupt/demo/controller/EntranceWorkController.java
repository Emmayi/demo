package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.dao.EntranceWork.EntranceService;
import cn.edu.bupt.demo.entity.EntranceWork;
import cn.edu.bupt.demo.entity.StaffNumber;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    //配合分页设置，获取所有的入廊作业信息
    @RequestMapping(value = "/entranceWorkByPage", params = {  "limit","page"  }, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getEntranceWorkByPage(@RequestParam int limit,
                               @RequestParam int page) throws Exception {
        try {
            return entranceService.findALlByPage(page,limit).toString();
        } catch (Exception e) {
            throw new Exception("getEntranceWorkByPage error!");
        }
    }

    //获取所有的入廊作业的页数
    @RequestMapping(value = "/entranceWorkPages", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getEntranceWorkPages(@RequestParam int limit) throws Exception {
        try {
            return entranceService.findEntranceWorkPageNum(limit);
        } catch (Exception e) {
            throw new Exception("getEntranceWorkPages error!");
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

    //根据入廊作业activity_range获取入廊信息
    @RequestMapping(value = "/entranceWorkByRange",params = {"range"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getEntranceWorkByDate(@RequestParam String range) throws Exception{
        try {
            return entranceService.findEntranceWorkByRange(range).toString();
        }catch (Exception e){
            throw new Exception("getEntranceWorkByDate error!");
        }
    }

    //统计有多少入廊作业
    @RequestMapping(value = "/entranceWorkCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getEntranceWorkCount() throws Exception{
        try {
            Integer count = entranceService.allWorkCount();
            return count;
        }catch (Exception e){
            throw new Exception("getEntranceWorkCount error!");
        }
    }

    //增加入廊作业的信息
    @RequestMapping(value = "/entranceWork", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createEntranceWork(@RequestBody String workInfo) throws Exception{
        JsonObject workString = new JsonParser().parse(workInfo).getAsJsonObject();
        EntranceWork entranceWork = Json2Work(workString);
        try {
            entranceService.save(entranceWork);
            return entranceWork.toString();
        } catch (Exception e) {
            throw new Exception("createEntranceWork error!");
        }
    }

    //更新入廊信息
    @RequestMapping(value = "/entranceWork", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateEntranceWork(@RequestBody String workInfo) throws Exception{
        JsonObject workString = new JsonParser().parse(workInfo).getAsJsonObject();
        if(workString.get("id").getAsString().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }
        EntranceWork entranceWork = new EntranceWork();
        entranceWork.setId(workString.get("id").getAsInt());
        entranceWork.setDuration(workString.get("duration").getAsString());
        entranceWork.setDate(workString.get("date").getAsLong());
        entranceWork.setWork_number(workString.get("work_number").getAsInt());
        entranceWork.setActivity_range(workString.get("activity_range").getAsString());
        if (workString.get("evaluation") != null) {
            entranceWork.setEvaluation(workString.get("evaluation").getAsString());
        }
        try {
            entranceService.update(entranceWork);
            return entranceWork.toString();
        } catch (Exception e) {
            throw new Exception("createEntranceWork error!");
        }
    }

    //添加评价
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

    private EntranceWork Json2Work(JsonObject workString) {
        EntranceWork entranceWork = new EntranceWork();
        entranceWork.setDuration(workString.get("duration").getAsString());
        entranceWork.setDate(workString.get("date").getAsLong());
        entranceWork.setWork_number(workString.get("work_number").getAsInt());
        entranceWork.setActivity_range(workString.get("activity_range").getAsString());

        if (workString.get("evaluation") != null) {
            entranceWork.setEvaluation(workString.get("evaluation").getAsString());
        }

        return entranceWork;
    }
}
