package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.dao.InspectionReport.InspectionService;
import cn.edu.bupt.demo.entity.InspectionReport;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author zy
 * @date 2018/10/26 上午10:12
 */

@RestController
@RequestMapping("api/v1/info")
@CrossOrigin
public class InspectionController {

    @Autowired
    private InspectionService inspectionService;

    //通过Id查找巡检报告的信息
    @RequestMapping(value = "/inspectionById",params = {"reportId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getInspectionById(@RequestParam Integer reportId) throws Exception{
        try {
            return inspectionService.findReportById(reportId).toString();
        }catch (Exception e){
            throw new Exception("getInspectionById error!");
        }
    }

    //通过值班人查找巡检报告的信息
    @RequestMapping(value = "/inspectionByDutyPerson",params = {"dutyPerson"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getInspectionByDutyPerson(@RequestParam String dutyPerson) throws Exception{
        try {
            return inspectionService.findReportByDutyPerson(dutyPerson).toString();
        }catch (Exception e){
            throw new Exception("getInspectionByDutyPerson error!");
        }
    }

    //通过巡检人查找巡检报告信息
    @RequestMapping(value = "/inspectionByInspectionPerson",params = {"inspectionPerson"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getInspectionByInspectionPerson(@RequestParam String inspectionPerson) throws Exception{
        try {
            return inspectionService.findReportByInspectionPerson(inspectionPerson).toString();
        }catch (Exception e){
            throw new Exception("getInspectionByInspectionPerson error!");
        }
    }

    //通过创建日期查找巡检报告信息
    @RequestMapping(value = "/inspectionByCalendarDate",params = {"calendar_date"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getInspectionByCalendarDate(@RequestParam Long calendar_date) throws Exception{
        try {
            return inspectionService.findReportByCalendarDate(calendar_date).toString();
        }catch (Exception e){
            throw new Exception("getInspectionByCalendarDate error!");
        }
    }

    //统计一共有多少报告
    @RequestMapping(value = "/inspection", method = RequestMethod.GET)
    @ResponseBody
    public Integer getAllCount() throws Exception{
        try {
            Integer count = inspectionService.getAllCount();
            return count;
        } catch (Exception e) {
            throw new Exception("getAllCount error!");
        }
    }

    //创建巡检报告，填写信息
    @RequestMapping(value = "/inspection", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createInspectionReport(@RequestBody String reportInfo) throws Exception{
        JsonObject reportString = new JsonParser().parse(reportInfo).getAsJsonObject();
        InspectionReport inspectionReport = Json2Report(reportString);
        try {
            inspectionService.save(inspectionReport);
            return inspectionReport.toString();
        } catch (Exception e) {
            throw new Exception("createInspectionReport error!");
        }
    }

    //更新巡检报告的信息
    @RequestMapping(value = "/inspection", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateInspectionReport(@RequestBody String reportInfo) throws Exception{
        JsonObject reportString = new JsonParser().parse(reportInfo).getAsJsonObject();
        if(reportString.get("id").getAsString().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }
        InspectionReport inspectionReport = new InspectionReport();
        inspectionReport.setId(reportString.get("id").getAsInt());
        inspectionReport.setAbnormal(reportString.get("abnormal").getAsString());
        inspectionReport.setState(reportString.get("state").getAsString());
        inspectionReport.setCreate_date(reportString.get("create_date").getAsLong());
        inspectionReport.setCalendar_date(reportString.get("calendar_date").getAsLong());
        inspectionReport.setDuty_person(reportString.get("duty_person").getAsString());
        inspectionReport.setInspection_person(reportString.get("inspection_person").getAsString());
        inspectionReport.setSummary(reportString.get("summary").getAsString());
        inspectionReport.setMaintenance(reportString.get("maintenance").getAsString());
        try {
            inspectionService.update(inspectionReport);
            return inspectionReport.toString();
        } catch (Exception e) {
            throw new Exception("updateInspectionReport error!");
        }
    }

    //根据Id删除巡检报告信息
    @RequestMapping(value = "/inspectionId",params = {"inspectionId"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteReportById(@RequestParam Integer inspectionId){
        try {
            inspectionService.deleteById(inspectionId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //根据工作人员Name删除工作人员信息
    @RequestMapping(value = "/inspectionPerson",params = {"inspectionPerson"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteReportByInspectionPerson(@RequestParam String inspectionPerson){
        try {
            inspectionService.deleteByInspectionPerson(inspectionPerson);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有巡检报告信息
    @RequestMapping(value = "/allReport",method = RequestMethod.GET)
    @ResponseBody
    public String findAllReport() throws Exception{
        try {
            return inspectionService.findAll().toString();
        }catch (Exception e){
            throw new Exception("findAllReport error!");
        }
    }

    private InspectionReport Json2Report(JsonObject reportString) {
        InspectionReport inspectionReport = new InspectionReport();
        inspectionReport.setAbnormal(reportString.get("abnormal").getAsString());
        inspectionReport.setState(reportString.get("state").getAsString());
        inspectionReport.setCreate_date(reportString.get("create_date").getAsLong());
        inspectionReport.setCalendar_date(reportString.get("calendar_date").getAsLong());
        inspectionReport.setDuty_person(reportString.get("duty_person").getAsString());
        inspectionReport.setInspection_person(reportString.get("inspection_person").getAsString());
        inspectionReport.setSummary(reportString.get("summary").getAsString());
        inspectionReport.setMaintenance(reportString.get("maintenance").getAsString());
        return inspectionReport;
    }

}
