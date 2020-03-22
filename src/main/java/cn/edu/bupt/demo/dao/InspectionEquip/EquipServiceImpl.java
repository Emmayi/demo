package cn.edu.bupt.demo.dao.InspectionEquip;

import cn.edu.bupt.demo.entity.InspectionEquip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zy
 * @date 2019/5/8 下午2:35
 */

@Slf4j
@Service
public class EquipServiceImpl implements EquipService {

    @Autowired
    EquipRepository equipRepository;

    @Override
    public List<InspectionEquip> findEquipInspectionPlanByPage(Integer page, Integer pageSize) {
        log.trace("Executing findReportByPage [{}]", page, pageSize);
        Integer index = page * pageSize;
        return equipRepository.findEquipInspectionPlanByPage(index, pageSize);
    }

    @Override
    public InspectionEquip findPlanById(Integer id) {
        log.trace("Executing findPlanById [{}]", id);
        return equipRepository.findPlanById(id);
    }

    @Override
    public List<InspectionEquip> findPlanByEquipName(String name) {
        log.trace("Executing findPlanByEquipName [{}]", name);
        return equipRepository.findPlanByEquipName(name);
    }

    @Override
    public Integer getAllCount() {
        log.trace("Executing getAllCount [{}]");
        return equipRepository.findAllCount();
    }

    @Override
    public void save(InspectionEquip inspectionEquip) {
        log.trace("Executing save [{}]", inspectionEquip);
        equipRepository.save(inspectionEquip);
    }

    @Override
    public void update(InspectionEquip inspectionEquip) {
        log.trace("Executing update [{}]", inspectionEquip);
        equipRepository.update(inspectionEquip);
    }

    @Override
    public void deleteById(Integer id) {
        log.trace("Executing deleteById [{}]", id);
        equipRepository.deleteById(id);
    }

    @Override
    public List<InspectionEquip> findAll() {
        log.trace("Executing findAll [{}]");
        return equipRepository.findAll();
    }
}
