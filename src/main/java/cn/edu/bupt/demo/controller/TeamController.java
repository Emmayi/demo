package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.dao.EmergencyTeam.TeamService;
import cn.edu.bupt.demo.entity.EmergencyTeam;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/info")
@CrossOrigin
public class TeamController {
    @Autowired
    TeamService teamService;

    //配合分页设置，获取所有的物资信息
    @RequestMapping(value = "/teamByPage", params = {  "limit","page"  }, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTeamByPage(@RequestParam int limit,
                                    @RequestParam int page) throws Exception {
        try {
            return teamService.findAllByPage(page,limit).toString();

        } catch (Exception e) {
            throw new Exception("getTeamByPage error!");
        }
    }

    //获取所有物资的页数
    @RequestMapping(value = "/teamPage", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getTeamPages(@RequestParam int limit) throws Exception {
        try {
            return teamService.findTeamPageNum(limit);
        } catch (Exception e) {
            throw new Exception("getTeamPages error!");
        }
    }

    //根据id获取物资信息
    @RequestMapping(value = "/team",params = {"teamId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTeamById(@RequestParam Integer teamId) throws Exception{
        try {
            return teamService.findTeamById(teamId).toString();
        }catch (Exception e){
            throw new Exception("getTeamById error!");
        }
    }

    //根据Name获取物资信息
    @RequestMapping(value = "/team",params = {"teamName"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTeamByName(@RequestParam String teamName) throws Exception{
        try {
            return teamService.findTeamByName(teamName).toString();
        }catch (Exception e){
            throw new Exception("getTeamByName error!");
        }
    }

    //根据Affiliation获取物资信息
    @RequestMapping(value = "/team",params = {"affiliation"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTeamByAffiliation(@RequestParam String affiliation) throws Exception{
        try {
            return teamService.findTeamByAffiliation(affiliation).toString();
        }catch (Exception e){
            throw new Exception("getTeamByAffiliation error!");
        }
    }

    //根据Location获取物资信息
    @RequestMapping(value = "/team",params = {"location"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTeamByLocation(@RequestParam String location) throws Exception{
        try {
            return teamService.findTeamByLocation(location).toString();
        }catch (Exception e){
            throw new Exception("getTeamByLocation error!");
        }
    }

    //统计有多少
    @RequestMapping(value = "/teamCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getTeamCount() throws Exception{
        try {
            Integer count = teamService.getTeamCount();
            return count;
        }catch (Exception e){
            throw new Exception("getTeamCount error!");
        }
    }

    //增加物资的信息
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
    @RequestMapping(value = "/team",params = {"id"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePlanById(@RequestParam Integer id){
        try {
            teamService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有的物资
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
