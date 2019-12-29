package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.annotation.Auth;
import cn.edu.bupt.demo.aop.MyLog;
import cn.edu.bupt.demo.dao.PipeGalleryArea.GalleryAreaService;
import cn.edu.bupt.demo.entity.PipeGalleryArea;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/info")
@CrossOrigin
@Api(description= "管廊区域")
public class GalleryAreaController {
    @Autowired
    GalleryAreaService galleryAreaService;

    //分页接口配置，有筛选参数返回筛选参数的，没有则显示全部
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/galleryAreaByPage",  method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getInspectionGalleryAreaByPage(@RequestParam(name="limit") int limit,
                                           @RequestParam (name="page") int page,
                                           @RequestParam(value="pipe_belong",required=false,defaultValue = "1") String pipe_belong )throws Exception {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("limit",limit);
            jsonObject.put("page",page);

            if(pipe_belong.equals("1")){
                Integer count = galleryAreaService.getAreaCount();
                jsonObject.put("allCount",count);
                jsonObject.put("data",galleryAreaService.findAllByPage(page,limit));
                return jsonObject.toString();
            }else {
                Integer count = galleryAreaService.AreaCountOfPipebelong(pipe_belong);
                jsonObject.put("data",galleryAreaService.findGalleryareaBypipe_belongAndPage(pipe_belong,page,limit));
                jsonObject.put("allCount",count);
                return jsonObject.toString();
            }

        } catch (Exception e) {
            throw new Exception("getInspectionGalleryAreaByPage error!");
        }
    }

    //获取所有管廊区域的页数
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/galleryAreaPage", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getAreaPages(@RequestParam int limit) throws Exception {
        try {
            return galleryAreaService.findAreaPageNum(limit);
        } catch (Exception e) {
            throw new Exception("getAreaPages error!");
        }
    }

    //根据id获取管廊区域信息
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/galleryArea",params = {"Id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAreaById(@RequestParam Integer Id) throws Exception{
        try {
            return galleryAreaService.findAreaById(Id).toString();
        }catch (Exception e){
            throw new Exception("getAreaById error!");
        }
    }

    //增加管廊区域的信息
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor"})
    @MyLog(value = "添加新管廊区域")
    @RequestMapping(value = "/galleryArea", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String creategalleryArea(@RequestBody String galleryAreaInfo) throws Exception{
        PipeGalleryArea pipeGalleryArea = JSONObject.parseObject(galleryAreaInfo,PipeGalleryArea.class);
        try {
            pipeGalleryArea.setNumber("QY000000");
            galleryAreaService.save(pipeGalleryArea);
            Integer ID=pipeGalleryArea.getId();
            String id=Integer.toString(ID);
            String number=galleryAreaService.setNumber(id);
            pipeGalleryArea.setNumber(number);
            galleryAreaService.update(pipeGalleryArea);
            return pipeGalleryArea.toString();
        } catch (Exception e) {
            throw new Exception("createArea error!");
        }
    }


    //更新
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @MyLog(value = "更新管廊区域")
    @RequestMapping(value = "/galleryArea", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updategalleryArea(@RequestBody String galleryAreaInfo) throws Exception{
        PipeGalleryArea pipeGalleryArea = JSONObject.parseObject(galleryAreaInfo,PipeGalleryArea.class);
        if(pipeGalleryArea.getId().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }
        try {
            Integer ID=pipeGalleryArea.getId();
            String id=Integer.toString(ID);
            pipeGalleryArea.setNumber(galleryAreaService.setNumber(id));
            galleryAreaService.update(pipeGalleryArea);
            return pipeGalleryArea.toString();
        } catch (Exception e) {
            throw new Exception("updateArea error!");
        }
    }

    //通过Id删除信息
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher"})
    @MyLog(value = "删除管廊区域")
    @RequestMapping(value = "/galleryArea",params = {"Id"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteAreaById(@RequestParam Integer Id){
        try {
            galleryAreaService.deleteById(Id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有区域
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "galleryAreaAll",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    @ResponseStatus(value = HttpStatus.OK)
    public String getAllArea() throws Exception{
        try{
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("AllArea", galleryAreaService.findAll());
            return jsonObject.toString();
        }catch (Exception e){
            throw new Exception("getAllArea error!");
        }
    }

}
