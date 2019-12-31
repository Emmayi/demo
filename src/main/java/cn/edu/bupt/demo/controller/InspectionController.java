package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.annotation.Auth;
import cn.edu.bupt.demo.aop.MyLog;
import cn.edu.bupt.demo.dao.InspectionReport.InspectionRepository;
import cn.edu.bupt.demo.dao.InspectionReport.InspectionService;
import cn.edu.bupt.demo.entity.InspectionReport;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zy
 * @date 2018/10/26 上午10:12
 * 巡检报告
 */

@RestController
@RequestMapping("api/v1/info")
@CrossOrigin
@Api(description= "巡检报告")
public class InspectionController {

    @Autowired
    private InspectionService inspectionService;

    @Autowired
    private InspectionRepository inspectionRepository;


    private String storePath= "/home/xuhao/zy/info/InspectionReport";//存放上传的文件路径
//    private String storePath= "/Users/zy/Desktop/file";//存放上传的文件路径

    //分页接口配置，有筛选参数返回筛选参数的，没有则显示全部
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/inspectionReportByPage",  method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getInspectionReportByPage(@RequestParam (name="limit") int limit,
                                        @RequestParam (name="page") int page,
                                        @RequestParam(value="date",required=false,defaultValue = "1") Long date )throws Exception {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("limit",limit);
            jsonObject.put("page",page);
            if(date==1){
                Integer count = inspectionService.getAllCount();
                jsonObject.put("allCount",count);
                jsonObject.put("data",inspectionService.findReportByPage(page,limit));
                return jsonObject.toString();
            }else {
                Integer count = inspectionRepository.findDayCount(date);
                jsonObject.put("data",inspectionService.findReportByCalendarDate(date,page,limit));
                jsonObject.put("allCount",count);
                return jsonObject.toString();
            }

        } catch (Exception e) {
            throw new Exception("getInspectionReportByPage error!");
        }
    }

    //通过Id查找巡检报告的信息
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
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
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor"})
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
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor"})
    @RequestMapping(value = "/inspectionByPlanId",params = {"id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getInspectionByInspectionPerson(@RequestParam Integer id) throws Exception{
        try {
            return inspectionService.findReportByPlanId(id).toString();
        }catch (Exception e){
            throw new Exception("getInspectionByInspectionPerson error!");
        }
    }

    //创建巡检报告，填写信息
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @MyLog(value = "添加新的巡检报告")
    @RequestMapping(value = "/inspection", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createInspectionReport(@RequestBody String reportInfo) throws Exception{
        InspectionReport inspectionReport = JSONObject.parseObject(reportInfo, InspectionReport.class);
        try {
            inspectionService.save(inspectionReport);
            return inspectionReport.toString();
        } catch (Exception e) {
            throw new Exception("createInspectionReport error!");
        }
    }

    //更新巡检报告的信息
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @MyLog(value = "更新巡检报告内容")
    @RequestMapping(value = "/inspection", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateInspectionReport(@RequestBody String reportInfo) throws Exception{
        InspectionReport inspectionReport = JSONObject.parseObject(reportInfo, InspectionReport.class);

        if(inspectionReport.getId().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }
        try {
            inspectionService.update(inspectionReport);
            return inspectionReport.toString();
        } catch (Exception e) {
            throw new Exception("updateInspectionReport error!");
        }
    }

    //根据Id删除巡检报告信息
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher"})
    @MyLog(value = "删除巡检报告")
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
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher"})
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
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor"})
    @RequestMapping(value = "/allReport",method = RequestMethod.GET)
    @ResponseBody
    public String findAllReport() throws Exception{
        try {
            return inspectionService.findAll().toString();
        }catch (Exception e){
            throw new Exception("findAllReport error!");
        }
    }


    //上传图片、视频，使用type区分
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/inspection/upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("type") String type) throws Exception{

        try {
            if (file.isEmpty()) {
                return "文件为空";
            }
            String fileName = file.getOriginalFilename();
            fileName = URLDecoder.decode(fileName,"UTF-8");
            String newPath = storePath+"/"+type;
            File filePath = new File(newPath, fileName);
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();//如果目录不存在，创建目录
            }

            String path = newPath+File.separator+fileName;
            File dest = new File(path);
            file.transferTo(dest);
            return "上传成功";

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    //删除文件接口
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher"})
    @RequestMapping(value = "/inspection/delete/{type}/{fileName}/{fileType}", method = RequestMethod.DELETE)
    public void deleteFile(@PathVariable("type") String type,
                           @PathVariable("fileName") String fileName,
                           @PathVariable("fileType") String fileType) throws UnsupportedEncodingException {

        System.out.println("name1: "+fileName);
        File file = new File(storePath+"/"+type+"/"+fileName+"."+fileType);
        System.out.println(file.getName()+"|"+file.exists());
        if(file.exists()){
            System.out.println(file.delete());
        }

    }

}
