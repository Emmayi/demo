package cn.edu.bupt.demo.dao.InspectionReport;

import cn.edu.bupt.demo.entity.InspectionReport;

import java.util.List;

/**
 * @author zy
 * @date 2018/10/25 上午11:34
 */
public interface InspectionService {

    InspectionReport findReportById(Integer id);

    List<InspectionReport> findReportByDutyPerson(String duty_person);

    List<InspectionReport> findReportByInspectionPerson(String inspection_person);

    List<InspectionReport> findReportByCondition(String condition);

    Integer getAllCount();

    void save(InspectionReport inspectionReport);

    void update(InspectionReport inspectionReport);

    void deleteById(Integer id);

    void deleteByInspectionPerson(String inspection_person);

    List<InspectionReport> findAll();

}
