package cn.edu.bupt.demo.dao.EmergencySupplies;

import cn.edu.bupt.demo.entity.EmergencySupplies;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zy
 * @date 2018/11/26 下午5:21
 */

@Slf4j
@Service
public class SuppliesServiceImpl implements SuppliesService {

    @Autowired
    SuppliesRespository suppliesRespository;

    @Override
    public List<EmergencySupplies> findAllByPage(Integer page, Integer pageSize) {
        log.trace("Executing findPlanByPage [{}]", page, pageSize);
        Integer index = page * pageSize;
        return suppliesRespository.findAllByPage(index, pageSize);
    }

    @Override
    public List<EmergencySupplies> findSuppliesByCategoryAndPage(String category, Integer page, Integer pageSize) {
        Integer index = page * pageSize;
        return suppliesRespository.findSuppliesByCategoryAndPage(category, index, pageSize);
    }

    @Override
    public Integer findSuppliesPageNum(Integer size) {
        log.trace("Executing findSuppliesPageNum [{}]", size);
        Integer num = (suppliesRespository.AllSuppliesCount() + size - 1) / size;
        return num;
    }

    @Override
    public EmergencySupplies findSuppliesById(Integer supply_id) {
        log.trace("Executing findSuppliesById [{}]", supply_id);
        return suppliesRespository.findEmergencySuppliesById(supply_id);
    }


    @Override
    public Integer getSuppliesCount() {
        log.trace("Executing getSuppliesCount [{}]");
        Integer count = suppliesRespository.AllSuppliesCount();
        return count;
    }

    @Override
    public Integer SuppliesCountOfCategory(String category) {
        log.trace("Executing SuppliesCountOfCategory [{}]");
        Integer count = suppliesRespository.SuppliesCountOfCategory(category);
        return count;
    }

    @Override
    public void save(EmergencySupplies emergencySupplies) {
        log.trace("Executing save [{}]");
        suppliesRespository.save(emergencySupplies);
    }

    @Override
    public void update(EmergencySupplies emergencySupplies) {
        log.trace("Executing update [{}]");
        suppliesRespository.update(emergencySupplies);
    }

    @Override
    public void deleteById(Integer supply_id) {
        log.trace("Executing deleteById [{}]");
        suppliesRespository.deleteById(supply_id);
    }

    @Override
    public List<EmergencySupplies> findAllSupplies() {
        log.trace("Executing findAllSupplies [{}]");
        return suppliesRespository.findAllSupplies();
    }
}
