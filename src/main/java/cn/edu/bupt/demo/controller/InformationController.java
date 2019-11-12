package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.dao.ProjectCinformation.InformationService;
import cn.edu.bupt.demo.entity.ProjectCinformation;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/info")
@CrossOrigin
public class InformationController {
    @Autowired
    InformationService informationService;

    //配合分页设置，获取所有的物资信息
    @RequestMapping(value = "/projectByPage", params = {  "limit","page"  }, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getProjectByPage(@RequestParam int limit,
                                @RequestParam int page) throws Exception {
        try {
            return informationService.findAllByPage(page,limit).toString();

        } catch (Exception e) {
            throw new Exception("getProjectByPage error!");
        }
    }

    //获取所有物资的页数
    @RequestMapping(value = "/projectPage", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getProjectPages(@RequestParam int limit) throws Exception {
        try {
            return informationService.findProjectPageNum(limit);
        } catch (Exception e) {
            throw new Exception("getProjectPages error!");
        }
    }

    //根据id获取物资信息
    @RequestMapping(value = "/project",params = {"projectId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getProjectById(@RequestParam Integer projectId) throws Exception{
        try {
            return informationService.findProjectById(projectId).toString();
        }catch (Exception e){
            throw new Exception("getProjectById error!");
        }
    }


    //根据Affiliation获取物资信息
    @RequestMapping(value = "/project",params = {"department"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getProjectByDepartment(@RequestParam String department) throws Exception{
        try {
            return informationService.findProjectByDepartment(department).toString();
        }catch (Exception e){
            throw new Exception("getProjectByDepartment error!");
        }
    }

    //根据Location获取物资信息
    @RequestMapping(value = "/project",params = {"location"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getProjectByLocation(@RequestParam String location) throws Exception{
        try {
            return informationService.findProjectByLocation(location).toString();
        }catch (Exception e){
            throw new Exception("getProjectByLocation error!");
        }
    }

    //统计有多少
    @RequestMapping(value = "/projectCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getProjectCount() throws Exception{
        try {
            Integer count = informationService.getProjectCount();
            return count;
        }catch (Exception e){
            throw new Exception("getProjectCount error!");
        }
    }

    //增加物资的信息
    @RequestMapping(value = "/project", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createProject(@RequestBody String projectInfo) throws Exception{
        JsonObject projectString = new JsonParser().parse(projectInfo).getAsJsonObject();
       ProjectCinformation projectCinformation = Json2Project(projectString);
        try {
            informationService.save(projectCinformation);
            return projectCinformation.toString();
        } catch (Exception e) {
            throw new Exception("createProject error!");
        }
    }

    private ProjectCinformation Json2Project(JsonObject projectString) {
        ProjectCinformation projectCinformation = new ProjectCinformation();
        projectCinformation.setLocation(projectString.get("location").getAsString());
        projectCinformation.setArea(projectString.get("area").getAsInt());
        projectCinformation.setDrawpoint(projectString.get("drawpoint").getAsString());
        projectCinformation.setMainequipments(projectString.get("mainequipments").getAsString());
        projectCinformation.setProequipments(projectString.get("proequipments").getAsString());
        projectCinformation.setDepartment(projectString.get("department").getAsString());
        projectCinformation.setMainusage(projectString.get("mainusage").getAsString());

        return projectCinformation;
    }

    //更新
    @RequestMapping(value = "/project", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateProject(@RequestBody String projectInfo) throws Exception{
        JsonObject projectString = new JsonParser().parse(projectInfo).getAsJsonObject();
        if(projectString.get("project_id").getAsString().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }

        ProjectCinformation projectCinformation = new ProjectCinformation();
        projectCinformation.setProject_id(projectString.get("project_id").getAsInt());
        projectCinformation.setLocation(projectString.get("location").getAsString());
        projectCinformation.setArea(projectString.get("area").getAsInt());
        projectCinformation.setDrawpoint(projectString.get("drawpoint").getAsString());
        projectCinformation.setMainequipments(projectString.get("mainequipments").getAsString());
        projectCinformation.setProequipments(projectString.get("proequipments").getAsString());
        projectCinformation.setDepartment(projectString.get("department").getAsString());
        projectCinformation.setMainusage(projectString.get("mainusage").getAsString());


        try {
            informationService.update(projectCinformation);
            return projectCinformation.toString();
        } catch (Exception e) {
            throw new Exception("updateProject error!");
        }
    }

    //通过Id删除信息
    @RequestMapping(value = "/project",params = {"id"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteProjectById(@RequestParam Integer id){
        try {
           informationService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有的物资
    @RequestMapping(value = "/projectALL", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllProject() throws Exception{
        try {
            return informationService.findAllProject().toString();
        }catch (Exception e){
            throw new Exception("getAllProject error!");
        }
    }
}
