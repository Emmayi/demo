package cn.edu.bupt.demo.dao.InspectionEquip;

import cn.edu.bupt.demo.entity.InspectionEquip;

import java.util.List;

public interface EquipService {
    List<InspectionEquip> findEquipInspectionPlanByPage(Integer page, Integer pageSize);

    InspectionEquip findPlanById(Integer id);

    List<InspectionEquip> findPlanByEquipName(String name);

    Integer getAllCount();

    void save(InspectionEquip inspectionEquip);

    void update(InspectionEquip inspectionEquip);

    void deleteById(Integer id);

    List<InspectionEquip> findAll();
}
