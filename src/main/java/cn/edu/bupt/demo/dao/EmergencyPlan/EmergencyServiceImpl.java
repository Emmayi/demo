package cn.edu.bupt.demo.dao.EmergencyPlan;

import cn.edu.bupt.demo.entity.EmergencyPlan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zy
 * @date 2018/11/24 下午10:44
 */

@Slf4j
@Service
public class EmergencyServiceImpl implements EmergencyService {

    @Autowired
    EmergencyRepository emergencyRepository;

    @Override
    public List<EmergencyPlan> findPlanByPage(Integer page, Integer pageSize) {
        log.trace("Executing findPlanByPage [{}]", page, pageSize);
        Integer index = page * pageSize;
        return emergencyRepository.findAllByPage(index, pageSize);
    }

    @Override
    public Integer findPlanPageNum(Integer size) {
        log.trace("Executing findPlanPageNum [{}]", size);
        Integer num = (emergencyRepository.AllPlanCount() + size - 1) / size;
        return num;
    }

    @Override
    public EmergencyPlan findPlanById(Integer emergency_id) {
        log.trace("Executing findPlanById [{}]", emergency_id);
        return emergencyRepository.findEmergencyPlanById(emergency_id);
    }

    @Override
    public List<EmergencyPlan> findPlanByName(String name) {
        log.trace("Executing findPlanByName [{}]", name);
        return emergencyRepository.findEmergencyPlanByName(name);
    }

    @Override
    public List<EmergencyPlan> findPlanByLevel(Integer level) {
        log.trace("Executing findPlanByLevel [{}]", level);
        return emergencyRepository.findEmergencyPlanByLevel(level);
    }

    @Override
    public List<EmergencyPlan> findPlanBySigner(String signer) {
        log.trace("Executing findPlanBySigner [{}]", signer);
        return emergencyRepository.findEmergencyPlanBySigner(signer);
    }

    @Override
    public Integer getPlanCount() {
        log.trace("Executing getPlanCount [{}]");
        Integer count = emergencyRepository.AllPlanCount();
        return count;
    }

    @Override
    public void save(EmergencyPlan emergencyPlan) {
        log.trace("Executing save [{}]");
        emergencyRepository.save(emergencyPlan);
    }

    @Override
    public void update(EmergencyPlan emergencyPlan) {
        log.trace("Executing update [{}]");
        emergencyRepository.update(emergencyPlan);
    }

    @Override
    public void deleteById(Integer emergency_id) {
        log.trace("Executing deleteById [{}]");
        emergencyRepository.deleteById(emergency_id);
    }

    @Override
    public List<EmergencyPlan> findAllPlan() {
        log.trace("Executing findAllPlan [{}]");
        return emergencyRepository.findAll();
    }
}
