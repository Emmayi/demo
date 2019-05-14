package cn.edu.bupt.demo.dao.EmergencyTeam;

import cn.edu.bupt.demo.entity.EmergencyTeam;

import java.util.List;

public interface TeamService {
    List<EmergencyTeam> findAllByPage(Integer page, Integer pageSize);

    Integer findTeamPageNum(Integer size);

    EmergencyTeam findTeamById(Integer team_id);

    List<EmergencyTeam> findTeamByName(String name);

    List<EmergencyTeam> findTeamByAffiliation(String affiliation);

    List<EmergencyTeam> findTeamByLocation(String location);

    Integer getTeamCount();

    void save(EmergencyTeam emergencyTeam);

    void update(EmergencyTeam emergencyTeam);

    void deleteById(Integer team_id);

    List<EmergencyTeam> findAllTeam();
}
