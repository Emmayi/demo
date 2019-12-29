package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.annotation.Auth;
import cn.edu.bupt.demo.dao.SysLog.SysLogService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zy
 * @date 2019/12/29 下午4:18
 */

@RestController
@RequestMapping("api/v1/info")
@CrossOrigin
@Api(description= "用户操作日志")
public class SysLogController {

    @Autowired
    SysLogService sysLogService;

    //分页接口配置，有筛选参数返回筛选参数的，没有则显示全部
    @Auth(roles = {"BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/syslog",  method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getSysLogByPage(@RequestParam (name="limit") int limit,
                                  @RequestParam (name="page") int page)throws Exception {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("limit",limit);
            jsonObject.put("page",page);

            Integer count = sysLogService.findAllCount();
            jsonObject.put("allCount",count);
            jsonObject.put("data",sysLogService.findLogByPage(page,limit));
            return jsonObject.toString();

        } catch (Exception e) {
            throw new Exception("getSysLogByPage error!");
        }
    }


}
