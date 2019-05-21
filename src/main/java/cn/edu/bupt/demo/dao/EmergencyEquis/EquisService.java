package cn.edu.bupt.demo.dao.EmergencyEquis;

import cn.edu.bupt.demo.entity.EmergencyEquis;

import java.util.List;

public interface EquisService {

    List<EmergencyEquis> findAllByPage(Integer page, Integer pageSize);

    List<EmergencyEquis> findEquisByCategoryAndPage(String category,Integer page, Integer pageSize);

    Integer findEquisPageNum(Integer size);

    EmergencyEquis findEquisById(Integer equis_id);

/*    List<EmergencyEquis> findEquisByName(String name);

    List<EmergencyEquis> findEquisByAffiliation(String affiliation);

    List<EmergencyEquis> findEquisByLocation(String location);*/

    Integer getEquisCount();

    Integer EquisCountOfCategory(String category);

    void save(EmergencyEquis emergencyEquis);

    void update(EmergencyEquis emergencyEquis);

    void deleteById(Integer equis_id);

    List<EmergencyEquis> findAllEquis();

}

