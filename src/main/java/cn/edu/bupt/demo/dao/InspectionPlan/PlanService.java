package cn.edu.bupt.demo.dao.InspectionPlan;

import cn.edu.bupt.demo.entity.InspectionPlan;

import java.util.List;

public interface PlanService {

    InspectionPlan findPlanById(Integer id);

    List<InspectionPlan> findPlanByInspectionPerson(String inspection_person);

    List<InspectionPlan> findPlanByInspectionDate(Long inspection_date);

    Integer getAllCount();

    void save(InspectionPlan inspectionPlan);

    void update(InspectionPlan inspectionPlan);

    void deleteByInspectionPerson(String inspection_person);

    List<InspectionPlan> findAll();

}
