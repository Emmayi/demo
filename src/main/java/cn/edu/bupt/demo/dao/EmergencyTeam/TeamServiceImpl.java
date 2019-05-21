package cn.edu.bupt.demo.dao.EmergencyTeam;

import cn.edu.bupt.demo.entity.EmergencyTeam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamRespository teamRespository;

    @Override
    public List<EmergencyTeam> findAllByPage(Integer page, Integer pageSize) {
        log.trace("Executing findTeamByPage [{}]", page,pageSize);
        Integer index = page * pageSize;
        return teamRespository.findAllByPage(index,pageSize);
    }

    @Override
    public Integer findTeamPageNum(Integer size) {
        log.trace("Executing findTeamPageNum [{}]", size);
        Integer num = (teamRespository.AllTeamCount()+size-1)/size;
        return num;
    }

    @Override
    public EmergencyTeam findTeamById(Integer team_id) {
        log.trace("Executing findTeamById [{}]", team_id);
        return teamRespository.findEmergencyTeamById(team_id);
    }

    @Override
    public List<EmergencyTeam> findTeamByName(String name) {
        log.trace("Executing findTeamByName [{}]", name);
        return teamRespository.findEmergencyTeamByName(name);
    }

    @Override
    public List<EmergencyTeam> findTeamByAffiliation(String affiliation) {
        log.trace("Executing findTeamByAffiliation [{}]", affiliation);
        return teamRespository.findEmergencyTeamByAffiliation(affiliation);
    }

    @Override
    public List<EmergencyTeam> findTeamByLocation(String location) {
        log.trace("Executing findTeamByLocation [{}]", location);
        return teamRespository.findEmergencyTeamByLocation(location);
    }

    @Override
    public Integer getTeamCount() {
        log.trace("Executing getTeamCount [{}]");
        Integer count = teamRespository.AllTeamCount();
        return count;
    }

    @Override
    public void save(EmergencyTeam emergencyTeam) {
        log.trace("Executing save [{}]");
        teamRespository.save(emergencyTeam);
    }

    @Override
    public void update(EmergencyTeam emergencyTeam) {
        log.trace("Executing update [{}]");
        teamRespository.update(emergencyTeam);
    }

    @Override
    public void deleteById(Integer team_id) {
        log.trace("Executing deleteById [{}]");
        teamRespository.deleteById(team_id);
    }

    @Override
    public List<EmergencyTeam> findAllTeam() {
        log.trace("Executing findAllSupplies [{}]");
        return teamRespository.findAllTeam();
    }

}
