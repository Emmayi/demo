package cn.edu.bupt.demo.dao.DaliyInspection;

import cn.edu.bupt.demo.entity.DailyInspection;

import java.util.List;

public interface DaliyService {
    DailyInspection findTableById(Integer id);

    List<DailyInspection> findTableByInspectionPerson(String inspection_person);

    List<DailyInspection> findTableByInspectionDate(Long time);

    Integer getAllCount();

    void save(DailyInspection dailyInspection);

    void update(DailyInspection dailyInspection);

    void deleteById(Integer id);

    List<DailyInspection> findAll();
}
