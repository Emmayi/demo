package cn.edu.bupt.demo.dao.InspectionPlan;

import cn.edu.bupt.demo.entity.InspectionPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlanService {

    InspectionPlan findPlanById(Integer id);

    List<InspectionPlan> findAllPlanByPage(Integer index, Integer pageSize);

    List<InspectionPlan> findPlanByInspectionPerson(String inspection_person,Integer index, Integer pageSize);

    List<InspectionPlan> findPlanByInspectionDate(Long inspection_date);

    Integer getAllCount();

    Integer findCountOfPerson(String inspection_person);

    void save(InspectionPlan inspectionPlan);

    void update(InspectionPlan inspectionPlan);

    void deleteById(Integer id);

    List<InspectionPlan> findAll();

}
