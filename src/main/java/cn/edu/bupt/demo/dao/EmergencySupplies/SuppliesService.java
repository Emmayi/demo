package cn.edu.bupt.demo.dao.EmergencySupplies;

import cn.edu.bupt.demo.entity.EmergencySupplies;

import java.util.List;

public interface SuppliesService {

    List<EmergencySupplies> findAllByPage(Integer page, Integer pageSize);

    Integer findSuppliesPageNum(Integer size);

    EmergencySupplies findSuppliesById(Integer supply_id);

    List<EmergencySupplies> findSuppliesByName(String name);

    List<EmergencySupplies> findSuppliesByAffiliation(String affiliation);

    List<EmergencySupplies> findSuppliesByLocation(String location);

    Integer getSuppliesCount();

    void save(EmergencySupplies emergencySupplies);

    void update(EmergencySupplies emergencySupplies);

    void deleteById(Integer supply_id);

    List<EmergencySupplies> findAllSupplies();

}
