package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.dao.EmergencyPlan.EmergencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zy
 * @date 2018/11/24 下午11:04
 */

@RestController
@RequestMapping("/api/v1/info")
@CrossOrigin
public class EmergencyPlanController {

    @Autowired
    EmergencyService emergencyService;

}
