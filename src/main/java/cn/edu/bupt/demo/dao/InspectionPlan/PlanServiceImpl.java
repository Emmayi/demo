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
    public List<InspectionPlan> findAllPlanByPage(Integer page, Integer pageSize) {
        log.trace("Executing findAllPlanByPage [{}]", page, pageSize);
        Integer index = page * pageSize;
        return planRepository.findAllPlanByPage(index,pageSize);
    }

    @Override
    public List<InspectionPlan> findPlanByInspectionPerson(String inspection_person, Integer page, Integer pageSize) {
        log.trace("Executing findPlanByInspectionPerson [{}]",inspection_person, page, pageSize);
        Integer index = page * pageSize;
        return planRepository.findPlanByInspectionPerson(inspection_person,index,pageSize);
    }

    @Override
    public List<InspectionPlan> findPlanByInspectionDate(Long inspection_date) {
        log.trace("Executing findPlanByInspectionDate [{}]", inspection_date);
        return planRepository.findPlanByInspectionDate(inspection_date);
    }

    @Override
    public Integer getAllCount() {
        log.trace("Executing getAllCount [{}]");
        Integer count = planRepository.findAllCount();
        return count;
    }

    @Override
    public Integer findCountOfPerson(String inspection_person) {
        log.trace("Executing findCountOfPerson [{}]",inspection_person);
        Integer count = planRepository.findCountOfPerson(inspection_person);
        return count;
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
    public void deleteById(Integer id) {
        log.trace("Executing deleteByInspectionPerson [{}]", id);
        planRepository.deleteById(id);
    }

    @Override
    public List<InspectionPlan> findAll() {
        log.trace("Executing findAll [{}]");
        return planRepository.findAll();
    }
}
