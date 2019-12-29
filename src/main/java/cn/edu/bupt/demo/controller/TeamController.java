package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.annotation.Auth;
import cn.edu.bupt.demo.dao.EmergencyTeam.TeamService;
import cn.edu.bupt.demo.entity.EmergencyTeam;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/info")
@CrossOrigin
@Api(description= "应急救援队伍")
public class TeamController {
    @Autowired
    TeamService teamService;

    //分页接口配置，有筛选参数返回筛选参数的，没有则显示全部
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/emergencyTeamByPage",  method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getInspectionTeamByPage(@RequestParam (name="limit") int limit,
                                           @RequestParam (name="page") int page,
                                           @RequestParam(value="category",required=false,defaultValue = "1") String category )throws Exception {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("limit",limit);
            jsonObject.put("page",page);

            if(category.equals("1")){
                Integer count = teamService.getTeamCount();
                jsonObject.put("allCount",count);
                jsonObject.put("data",teamService.findAllByPage(page,limit));
                return jsonObject.toString();
            }else {
                Integer count = teamService.TeamCountOfCategory(category);
                jsonObject.put("data",teamService.findTeamByCategoryAndPage(category,page,limit));
                jsonObject.put("allCount",count);
                return jsonObject.toString();
            }

        } catch (Exception e) {
            throw new Exception("getInspectionTeamByPage error!");
        }
    }

    //获取所有队伍的页数
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/teamPage", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getTeamPages(@RequestParam int limit) throws Exception {
        try {
            return teamService.findTeamPageNum(limit);
        } catch (Exception e) {
            throw new Exception("getTeamPages error!");
        }
    }

    //根据id获取队伍信息
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/team",params = {"teamId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTeamById(@RequestParam Integer teamId) throws Exception{
        try {
            return teamService.findTeamById(teamId).toString();
        }catch (Exception e){
            throw new Exception("getTeamById error!");
        }
    }

  /*  //根据Name获取队伍信息
    @RequestMapping(value = "/team",params = {"teamName"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTeamByName(@RequestParam String teamName) throws Exception{
        try {
            return teamService.findTeamByName(teamName).toString();
        }catch (Exception e){
            throw new Exception("getTeamByName error!");
        }
    }

/*    //统计有多少
    @RequestMapping(value = "/teamCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getTeamCount() throws Exception{
        try {
            Integer count = teamService.getTeamCount();
            return count;
        }catch (Exception e){
            throw new Exception("getTeamCount error!");
        }
    }*/

    //增加队伍的信息
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor"})
    @RequestMapping(value = "/team", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createTeam(@RequestBody String teamInfo) throws Exception{
        JsonObject teamString = new JsonParser().parse(teamInfo).getAsJsonObject();
        EmergencyTeam emergencyTeam = Json2Team(teamString);
        try {
            teamService.save(emergencyTeam);
            return emergencyTeam.toString();
        } catch (Exception e) {
            throw new Exception("createTeam error!");
        }
    }

    private EmergencyTeam Json2Team(JsonObject teamString) {
        EmergencyTeam emergencyTeam = new EmergencyTeam();
        emergencyTeam.setName(teamString.get("name").getAsString());
        emergencyTeam.setCategory(teamString.get("category").getAsString());
        emergencyTeam.setLevel(teamString.get("level").getAsInt());
        emergencyTeam.setSpecialty(teamString.get("specialty").getAsString());
        emergencyTeam.setIntroduction(teamString.get("introduction").getAsString());
        emergencyTeam.setAffiliation(teamString.get("affiliation").getAsString());
        emergencyTeam.setPrincipal(teamString.get("principal").getAsString());
        emergencyTeam.setPhone(teamString.get("phone").getAsString());
        emergencyTeam.setLocation(teamString.get("location").getAsString());

        return emergencyTeam;
    }

    //更新
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor"})
    @RequestMapping(value = "/team", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateTeam(@RequestBody String teamInfo) throws Exception{
        JsonObject teamString = new JsonParser().parse(teamInfo).getAsJsonObject();
        if(teamString.get("team_id").getAsString().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }

        EmergencyTeam emergencyTeam = new EmergencyTeam();
        emergencyTeam.setTeam_id(teamString.get("team_id").getAsInt());
        emergencyTeam.setName(teamString.get("name").getAsString());
        emergencyTeam.setCategory(teamString.get("category").getAsString());
        emergencyTeam.setLevel(teamString.get("level").getAsInt());
        emergencyTeam.setSpecialty(teamString.get("specialty").getAsString());
        emergencyTeam.setIntroduction(teamString.get("introduction").getAsString());
        emergencyTeam.setAffiliation(teamString.get("affiliation").getAsString());
        emergencyTeam.setPrincipal(teamString.get("principal").getAsString());
        emergencyTeam.setPhone(teamString.get("phone").getAsString());
        emergencyTeam.setLocation(teamString.get("location").getAsString());


        try {
            teamService.update(emergencyTeam);
            return emergencyTeam.toString();
        } catch (Exception e) {
            throw new Exception("updateTeam error!");
        }
    }

    //通过Id删除信息
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher"})
    @RequestMapping(value = "/team",params = {"teamId"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePlanById(@RequestParam Integer teamId){
        try {
            teamService.deleteById(teamId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有的队伍
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/teamALL", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllTeam() throws Exception{
        try {
            return teamService.findAllTeam().toString();
        }catch (Exception e){
            throw new Exception("getAllTeam error!");
        }
    }

}
