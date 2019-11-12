package cn.edu.bupt.demo.controller;


import cn.edu.bupt.demo.annotation.Auth;
import cn.edu.bupt.demo.dao.PipeGallery.PipeGalleryService;
import cn.edu.bupt.demo.entity.PipeGallery;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/info")
@CrossOrigin
@Api(description= "管廊管道")
public class PipeCotroller {
    @Autowired
    PipeGalleryService pipeGalleryService;

    //分页接口配置，有筛选参数返回筛选参数的，没有则显示全部
    @Auth(roles = {"BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/pipeByPage",  method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getInspectionPipeByPage(@RequestParam(name="limit") int limit,
                                                 @RequestParam (name="page") int page,
                                                 @RequestParam(value="unit",required=false,defaultValue = "1") String unit )throws Exception {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("limit",limit);
            jsonObject.put("page",page);

            if(unit.equals("1")){
                Integer count = pipeGalleryService.getPipeCount();
                jsonObject.put("allCount",count);
                jsonObject.put("data",pipeGalleryService.findAllByPage(page,limit));
                return jsonObject.toString();
            }else {
                Integer count = pipeGalleryService.PipeCountOfunit(unit);
                jsonObject.put("data",pipeGalleryService.findPipeByunitAndPage(unit,page,limit));
                jsonObject.put("allCount",count);
                return jsonObject.toString();
            }

        } catch (Exception e) {
            throw new Exception("getInspectionPipeByPage error!");
        }
    }

    //获取所有管廊的页数
    @Auth(roles = {"BranchDispatcher","BranchMonitor"})
    @RequestMapping(value = "/pipePage", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getPipePages(@RequestParam int limit) throws Exception {
        try {
            return pipeGalleryService.findPipePageNum(limit);
        } catch (Exception e) {
            throw new Exception("getPipePages error!");
        }
    }

    //根据id获取管廊信息
    @Auth(roles = {"BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/pipeGallery",params = {"Id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPipeById(@RequestParam Integer Id) throws Exception{
        try {
            return pipeGalleryService.findPipeById(Id).toString();
        }catch (Exception e){
            throw new Exception("getPipeById error!");
        }
    }

    //增加管廊的信息
    @Auth(roles = {"BranchDispatcher","BranchMonitor"})
    @RequestMapping(value = "/pipeGallery", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createpipeGallery(@RequestBody String pipeGalleryInfo) throws Exception{
        PipeGallery pipeGallery = JSONObject.parseObject(pipeGalleryInfo,PipeGallery.class);
        try {
            pipeGallery.setNumber("GL000000");
            pipeGalleryService.save(pipeGallery);
            Integer ID=pipeGallery.getId();
            String id=Integer.toString(ID);
            String number=pipeGalleryService.setNumber(id);
            pipeGallery.setNumber(number);
            pipeGalleryService.update(pipeGallery);
            return pipeGallery.toString();
        } catch (Exception e) {
            throw new Exception("createArea error!");
        }
    }


    //更新
    @Auth(roles = {"BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/pipeGallery", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updatepipeGallery(@RequestBody String pipeGalleryInfo) throws Exception{
        PipeGallery pipeGallery = JSONObject.parseObject(pipeGalleryInfo,PipeGallery.class);
        if(pipeGallery.getId().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }
        try {
            Integer ID=pipeGallery.getId();
            String id=Integer.toString(ID);
            pipeGallery.setNumber(pipeGalleryService.setNumber(id));
            pipeGalleryService.update(pipeGallery);
            return pipeGallery.toString();
        } catch (Exception e) {
            throw new Exception("updateArea error!");
        }
    }

    //通过Id删除信息
    @Auth(roles = {"BranchDispatcher"})
    @RequestMapping(value = "/pipeGallery",params = {"Id"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteAreaById(@RequestParam Integer Id){
        try {
           pipeGalleryService.deleteById(Id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有管廊
    @Auth(roles = {"BranchDispatcher","BranchMonitor"})
    @RequestMapping(value = "pipeGalleryAll",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    @ResponseStatus(value = HttpStatus.OK)
    public String getAllPipe() throws Exception{
        try{
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("AllPipes", pipeGalleryService.findAll());
            return jsonObject.toString();
        }catch (Exception e){
            throw new Exception("getAllPipe error!");
        }
    }

}
