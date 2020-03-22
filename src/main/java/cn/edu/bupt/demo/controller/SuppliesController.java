package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.annotation.Auth;
import cn.edu.bupt.demo.dao.EmergencySupplies.SuppliesService;
import cn.edu.bupt.demo.entity.EmergencySupplies;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author zy
 * @date 2018/11/26 下午7:06
 */

@RestController
@RequestMapping("/api/v1/info")
@CrossOrigin
@Api(description = "应急物资")
public class SuppliesController {

    @Autowired
    SuppliesService suppliesService;

    //分页接口配置，有筛选参数返回筛选参数的，没有则显示全部
    @Auth(roles = {"GeneralDispatcher", "GeneralMonitor", "BranchDispatcher", "BranchMonitor", "Repairman"})
    @RequestMapping(value = "/emergencySuppliesByPage", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getInspectionEquipByPage(@RequestParam(name = "limit") int limit,
                                           @RequestParam(name = "page") int page,
                                           @RequestParam(value = "category", required = false, defaultValue = "1") String category) throws Exception {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("limit", limit);
            jsonObject.put("page", page);

            if (category.equals("1")) {
                Integer count = suppliesService.getSuppliesCount();
                jsonObject.put("allCount", count);
                jsonObject.put("data", suppliesService.findAllByPage(page, limit));
                return jsonObject.toString();
            } else {
                Integer count = suppliesService.SuppliesCountOfCategory(category);
                jsonObject.put("data", suppliesService.findSuppliesByCategoryAndPage(category, page, limit));
                jsonObject.put("allCount", count);
                return jsonObject.toString();
            }

        } catch (Exception e) {
            throw new Exception("getInspectionEquipByPage error!");
        }
    }

    //获取所有物资的页数
    @Auth(roles = {"GeneralDispatcher", "GeneralMonitor", "BranchDispatcher", "BranchMonitor", "Repairman"})
    @RequestMapping(value = "/suppliesPage", params = {"limit"}, method = RequestMethod.GET)
    @ResponseBody
    public Integer getSuppliesPages(@RequestParam int limit) throws Exception {
        try {
            return suppliesService.findSuppliesPageNum(limit);
        } catch (Exception e) {
            throw new Exception("getSuppliesPages error!");
        }
    }

    //根据id获取物资信息
    @Auth(roles = {"GeneralDispatcher", "GeneralMonitor", "BranchDispatcher", "BranchMonitor", "Repairman"})
    @RequestMapping(value = "/supplies", params = {"supplyId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getSupplyById(@RequestParam Integer supplyId) throws Exception {
        try {
            return suppliesService.findSuppliesById(supplyId).toString();
        } catch (Exception e) {
            throw new Exception("getSupplyById error!");
        }
    }

    //增加物资的信息
    @Auth(roles = {"GeneralDispatcher", "GeneralMonitor", "BranchDispatcher", "BranchMonitor", "Repairman"})
    @RequestMapping(value = "/supplies", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createSupply(@RequestBody String supplyInfo) throws Exception {

        EmergencySupplies emergencySupplies = JSONObject.parseObject(supplyInfo, EmergencySupplies.class);
        try {
            suppliesService.save(emergencySupplies);
            return emergencySupplies.toString();
        } catch (Exception e) {
            throw new Exception("createSupply error!");
        }
    }


    //更新
    @Auth(roles = {"GeneralDispatcher", "GeneralMonitor", "BranchDispatcher", "BranchMonitor"})
    @RequestMapping(value = "/supplies", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateSupply(@RequestBody String supplyInfo) throws Exception {
        EmergencySupplies emergencySupplies = JSONObject.parseObject(supplyInfo, EmergencySupplies.class);
        if (emergencySupplies.getSupply_id().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }
        try {
            suppliesService.update(emergencySupplies);
            return emergencySupplies.toString();
        } catch (Exception e) {
            throw new Exception("updateSupply error!");
        }
    }

    //通过Id删除信息
    @Auth(roles = {"GeneralDispatcher", "GeneralMonitor", "BranchDispatcher"})
    @RequestMapping(value = "/supplies", params = {"supplyId"}, method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePlanById(@RequestParam Integer supplyId) {
        try {
            suppliesService.deleteById(supplyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取所有的物资
    @Auth(roles = {"GeneralDispatcher", "GeneralMonitor", "BranchDispatcher", "BranchMonitor", "Repairman"})
    @RequestMapping(value = "/suppliesALL", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllSupplies() throws Exception {
        try {
            return suppliesService.findAllSupplies().toString();
        } catch (Exception e) {
            throw new Exception("getAllSupplies error!");
        }
    }


}
