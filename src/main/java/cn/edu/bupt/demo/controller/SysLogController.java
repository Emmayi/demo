package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.dao.SysLog.SysLogService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zy
 * @date 2019/5/31 下午3:48
 */

@RestController
@RequestMapping("api/v1/info")
@CrossOrigin
public class SysLogController {

    @Autowired
    SysLogService sysLogService;

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
