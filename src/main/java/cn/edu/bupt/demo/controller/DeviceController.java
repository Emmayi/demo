package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.aop.MyLog;
import cn.edu.bupt.demo.dao.SysLog.SysLogService;
import cn.edu.bupt.demo.entity.SysLog;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Set;

/**
 * @author zy
 * @date 2019/5/28 上午11:07
 */

@RestController
@RequestMapping("api/v1/info")
@CrossOrigin
@Api(description= "获取设备数据")
public class DeviceController {

    HttpLogin httpLogin = new HttpLogin();


    @RequestMapping(value = "/allDevice",method = RequestMethod.GET)
    @ResponseBody
    public String findAllDevice() throws Exception{

        JSONObject result = new JSONObject();

        try {
            httpLogin.httplogin();

            String device = httpLogin.findDevice();
            JSONArray jsonArray = JSONArray.parseArray(device);
            if(jsonArray.size()>0){
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String id = jsonObject.get("id").toString();
                String attributes =  httpLogin.findAttributes(id);
                //解析字段返回
                JSONArray ja = JSONArray.parseArray(attributes);
                Iterator<Object> it = ja.iterator();
                while (it.hasNext()) {
                    JSONObject ob = (JSONObject) it.next();
                    //打印出遍历出的jsonObject
                    result.put(ob.get("key").toString(),ob.get("value"));
                }

            }else {
                throw new Exception("data is null");
            }

            return result.toJSONString();
        }catch (Exception e){
            throw new Exception("findAllDevice error!");
        }
    }

}
