package cn.edu.bupt.demo.dao.EmergencyPlan;

import cn.edu.bupt.demo.entity.EmergencyPlan;

import java.util.List;

public interface EmergencyService {

    List<EmergencyPlan> findPlanByPage(Integer page, Integer pageSize);

    Integer findPlanPageNum(Integer size);

    EmergencyPlan findPlanById(Integer emergency_id);

    List<EmergencyPlan> findPlanByName(String name);

    List<EmergencyPlan> findPlanByLevel(Integer level);

    List<EmergencyPlan> findPlanBySigner(String signer);

    Integer getPlanCount();

    void save(EmergencyPlan emergencyPlan);

    void update(EmergencyPlan emergencyPlan);

    void deleteById(Integer emergency_id);

    List<EmergencyPlan> findAllPlan();


}
