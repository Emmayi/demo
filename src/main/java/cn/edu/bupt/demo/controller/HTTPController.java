package cn.edu.bupt.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zy
 * @date 2019/1/16 下午7:57
 */

@RestController
@RequestMapping("api/v1/info")
@CrossOrigin
public class HTTPController {

    HttpControl httpControl = new HttpControl();

    @RequestMapping(value = "/allDevice",method = RequestMethod.GET)
    @ResponseBody
    public String findAllDevice() throws Exception{
        try {
            httpControl.httplogin();
            Thread.sleep(3);
            String device = httpControl.findDevice();
            return device;

        }catch (Exception e){
            throw new Exception("findAllDevice error!");
        }
    }

}
