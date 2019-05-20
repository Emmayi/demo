package cn.edu.bupt.demo.dao.DaliyInspection;

import cn.edu.bupt.demo.entity.DailyInspection;

import java.util.List;

public interface DaliyService {
    DailyInspection findTableById(Integer id);

    List<DailyInspection> findTableByInspectionPerson(String inspection_person,Integer page,Integer pageSize);

    List<DailyInspection> findAllByPage(Integer page,Integer pageSize);

    Integer getAllCount();

    Integer findCountByPerson(String inspection_person);

    void save(DailyInspection dailyInspection);

    void update(DailyInspection dailyInspection);

    void deleteById(Integer id);

    List<DailyInspection> findAll();
}
