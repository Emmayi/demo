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
public class EntranceWorkController {

    @Autowired
    EntranceService entranceService;

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

    //根据入廊作业date获取入廊信息
    @RequestMapping(value = "/entranceWorkByDate",params = {"date"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getEntranceWorkByDate(@RequestParam Long date) throws Exception{
        try {
            return entranceService.findEntranceWorkByDate(date).toString();
        }catch (Exception e){
            throw new Exception("getEntranceWorkByDate error!");
        }
    }

    //统计有多少入廊作业
    @RequestMapping(value = "/entranceWorkCount", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Integer getEntranceWorkCount() throws Exception{
        try {
            return entranceService.allWorkCount();
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
        entranceWork.setEvaluation(workString.get("evaluation").getAsString());
        try {
            entranceService.update(entranceWork);
            return entranceWork.toString();
        } catch (Exception e) {
            throw new Exception("createEntranceWork error!");
        }
    }

    //通过Id删除信息
    @RequestMapping(value = "/entranceWork",params = {"WorkId"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteWorkById(@RequestParam Integer WorkId){
        try {
            entranceService.deleteById(WorkId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //通过Date删除信息
    @RequestMapping(value = "/allEntranceWork",params = {"date"},method = RequestMethod.DELETE)
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
