package cn.edu.bupt.demo.dao.InspectionReport;

import cn.edu.bupt.demo.entity.InspectionReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zy
 * @date 2018/10/25 上午11:39
 */

@Service
@Slf4j
public class InspectionServiceImpl implements InspectionService{

    @Autowired
    InspectionRepository inspectionRepository;

    @Override
    public InspectionReport findReportById(Integer id) {
        log.trace("Executing findReportById [{}]", id);
        return inspectionRepository.findReportById(id);
    }

    @Override
    public List<InspectionReport> findReportByDutyPerson(String duty_person) {
        log.trace("Executing findReportByDutyPerson [{}]", duty_person);
        return inspectionRepository.findReportByDutyPerson(duty_person);
    }

    @Override
    public List<InspectionReport> findReportByPlanId(Integer plan_id) {
        log.trace("Executing findReportByPlanId [{}]", plan_id);
        return inspectionRepository.findReportByPlanId(plan_id);
    }


    @Override
    public List<InspectionReport> findReportByCalendarDate(Long calendar_date, Integer page, Integer pageSize) {
        log.trace("Executing findReportByCalendarDate [{}]", calendar_date,page,pageSize);
        Integer index = page * pageSize;
        return inspectionRepository.findReportByCreateDate(calendar_date,index,pageSize);
    }

    @Override
    public List<InspectionReport> findReportByPage(Integer page, Integer pageSize) {
        log.trace("Executing findReportByPage [{}]", page,pageSize);
        Integer index = page * pageSize;
        return inspectionRepository.findReportByPage(index, pageSize);
    }

    @Override
    public Integer getAllCount() {
        log.trace("Executing getAllCount [{}]");
        return inspectionRepository.findAllCount();
    }

    @Override
    public void save(InspectionReport inspectionReport) {
        log.trace("Executing save [{}]", inspectionReport);
        inspectionRepository.save(inspectionReport);
    }

    @Override
    public void update(InspectionReport inspectionReport) {
        log.trace("Executing update [{}]", inspectionReport);
        inspectionRepository.update(inspectionReport);
    }

    @Override
    public void deleteById(Integer id) {
        log.trace("Executing deleteById [{}]", id);
        inspectionRepository.deleteById(id);
    }

    @Override
    public void deleteByInspectionPerson(String inspection_person) {
        log.trace("Executing deleteByInspectionPerson [{}]", inspection_person);
        inspectionRepository.deleteByInspectionPerson(inspection_person);
    }

    @Override
    public List<InspectionReport> findAll() {
        log.trace("Executing findAll [{}]");
        return inspectionRepository.findAll();
    }
}
