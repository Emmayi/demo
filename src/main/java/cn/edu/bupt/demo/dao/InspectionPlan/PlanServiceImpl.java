package cn.edu.bupt.demo.dao.InspectionPlan;

import cn.edu.bupt.demo.entity.InspectionPlan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zy
 * @date 2019/4/19 上午9:51
 */
@Slf4j
@Service
public class PlanServiceImpl implements PlanService{

    @Autowired
    PlanRepository planRepository;

    @Override
    public InspectionPlan findPlanById(Integer id) {
        log.trace("Executing findPlanById [{}]", id);
        return planRepository.findPlanById(id);
    }

    @Override
    public List<InspectionPlan> findPlanByInspectionPerson(String inspection_person) {
        log.trace("Executing findPlanByInspectionPerson [{}]", inspection_person);
        return planRepository.findPlanByInspectionPerson(inspection_person);
    }

    @Override
    public List<InspectionPlan> findPlanByInspectionDate(Long inspection_date) {
        log.trace("Executing findPlanByInspectionDate [{}]", inspection_date);
        return planRepository.findPlanByInspectionDate(inspection_date);
    }

    @Override
    public Integer getAllCount() {
        log.trace("Executing getAllCount [{}]");
        return planRepository.findAllCount();
    }

    @Override
    public void save(InspectionPlan inspectionPlan) {
        log.trace("Executing save [{}]", inspectionPlan);
        planRepository.save(inspectionPlan);
    }

    @Override
    public void update(InspectionPlan inspectionPlan) {
        log.trace("Executing save [{}]", inspectionPlan);
        planRepository.update(inspectionPlan);
    }

    @Override
    public void deleteByInspectionPerson(String inspection_person) {
        log.trace("Executing deleteByInspectionPerson [{}]", inspection_person);
        planRepository.deleteByInspectionPerson(inspection_person);
    }

    @Override
    public List<InspectionPlan> findAll() {
        log.trace("Executing findAll [{}]");
        return planRepository.findAll();
    }
}
