package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.annotation.Auth;
import cn.edu.bupt.demo.aop.MyLog;
import cn.edu.bupt.demo.dao.InspectionPath.PathService;
import cn.edu.bupt.demo.entity.InspectionPath;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author zy
 * @date 2019/5/29 上午11:43
 */

@RestController
@RequestMapping("api/v1/info")
@CrossOrigin
@Api(description= "巡检路径")
public class InspectionPathController {

    @Autowired
    private PathService pathService;

    //分页接口配置，有筛选参数返回筛选参数的，没有则显示全部
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/inspectionPathByPage",  method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getInspectionPathByPage(@RequestParam (name="limit") int limit,
                                            @RequestParam (name="page") int page,
                                            @RequestParam(value="area",required=false,defaultValue = "1") String area)throws Exception {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("limit",limit);
            jsonObject.put("page",page);
            if(area.equals("1")){
                Integer count = pathService.getAllCount();
                jsonObject.put("allCount",count);
                jsonObject.put("data",pathService.findAllPathByPage(page,limit));
                return jsonObject.toString();
            }else {
                Integer count = pathService.findCountOfArea(area);
                jsonObject.put("data",pathService.findPathByArea(area,page,limit));
                jsonObject.put("allCount",count);
                return jsonObject.toString();
            }
        } catch (Exception e) {
            throw new Exception("getInspectionPathByPage error!");
        }
    }

    //通过Id查找巡检路径的信息
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/inspectionPath",params = {"id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getInspectionPathById(@RequestParam Integer id) throws Exception{
        try {
            return pathService.findPathById(id).toString();
        }catch (Exception e){
            throw new Exception("getInspectionPathById error!");
        }
    }

    //创建巡检路径，填写信息
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @MyLog(value = "添加新的巡检路径")
    @RequestMapping(value = "/inspectionPath", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createInspectionPath(@RequestBody String pathInfo) throws Exception{
        InspectionPath inspectionPath = JSONObject.parseObject(pathInfo, InspectionPath.class);
        try {
            inspectionPath.setNumber("LX000000");
            pathService.save(inspectionPath);
            Integer ID=inspectionPath.getId();
            String id=Integer.toString(ID);
            String number=pathService.setNumber(id);
            inspectionPath.setNumber(number);
            pathService.update(inspectionPath);
            return inspectionPath.toString();
        } catch (Exception e) {
            throw new Exception("createInspectionPath error!");
        }
    }


    //更新巡检路径的信息
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor"})
    @MyLog(value = "更新巡检路径内容")
    @RequestMapping(value = "/inspectionPath", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateInspectionPath(@RequestBody String pathInfo) throws Exception{
        InspectionPath inspectionPath = JSONObject.parseObject(pathInfo, InspectionPath.class);

        if(inspectionPath.getId().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }
        try {
            Integer ID=inspectionPath.getId();
            String id=Integer.toString(ID);
            inspectionPath.setNumber(pathService.setNumber(id));
            pathService.update(inspectionPath);
            return inspectionPath.toString();
        } catch (Exception e) {
            throw new Exception("updateInspectionPath error!");
        }
    }


    //根据Id删除巡检路径信息
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher"})
    @MyLog(value = "删除巡检路径")
    @RequestMapping(value = "/inspection",params = {"id"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePathById(@RequestParam Integer id){
        try {
            pathService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有巡检路径信息
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/allPath",method = RequestMethod.GET)
    @ResponseBody
    public String findAllPath() throws Exception{
        try {
            return pathService.findAll().toString();
        }catch (Exception e){
            throw new Exception("findAllPath error!");
        }
    }


}
