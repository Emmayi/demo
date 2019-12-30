package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.annotation.Auth;
import cn.edu.bupt.demo.aop.MyLog;
import cn.edu.bupt.demo.dao.PointLanlon.PointLanlonRepository;
import cn.edu.bupt.demo.entity.PointLanlon;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author zy
 * @date 2019/12/30 上午11:25
 */

@RestController
@RequestMapping("api/v1/info")
@CrossOrigin
@Api(description= "起点/终点-经纬度对应")
public class PointLanlonController {

    @Autowired
    private PointLanlonRepository pointLanlonRepository;

    //查询所有的起点、终点名称
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/pointName", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllPointName() throws Exception{
        try {
            return pointLanlonRepository.findAllPoint().toString();
        }catch (Exception e){
            throw new Exception("getAllPointName error!");
        }
    }

    //通过名称查询经纬度
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/lanlon",  params = {  "point"  },method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllPointName(@RequestParam String point) throws Exception{
        try {
            return pointLanlonRepository.findAllPoint().toString();
        }catch (Exception e){
            throw new Exception("getAllPointName error!");
        }
    }


    //创建起点、终点，填写信息
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @MyLog(value = "创建起点/终点")
    @RequestMapping(value = "/point", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createPointLanlon(@RequestBody String pointLanlonInfo) throws Exception{

        PointLanlon pointLanlon = JSONObject.parseObject(pointLanlonInfo, PointLanlon.class);

        try {
            pointLanlonRepository.save(pointLanlon);
            return pointLanlon.toString();
        } catch (Exception e) {
            throw new Exception("createPointLanlon error!");
        }
    }

    //更新起点、终点信息
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor"})
    @RequestMapping(value = "/point", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updatePointLanlon(@RequestBody String pointLanlonInfo) throws Exception{

        PointLanlon pointLanlon = JSONObject.parseObject(pointLanlonInfo, PointLanlon.class);
        if(pointLanlon.getId().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }

        try {
            pointLanlonRepository.update(pointLanlon);
            return pointLanlon.toString();
        } catch (Exception e) {
            throw new Exception("updatePointLanlon error!");
        }
    }

}
