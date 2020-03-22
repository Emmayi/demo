package cn.edu.bupt.demo.dao.EmergencyEquis;

import cn.edu.bupt.demo.entity.EmergencyEquis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EquisServiceImpl implements EquisService {

    @Autowired
    EquisRespository equisRespository;

    @Override
    public List<EmergencyEquis> findAllByPage(Integer page, Integer pageSize) {
        log.trace("Executing findPlanByPage [{}]", page, pageSize);
        Integer index = page * pageSize;
        return equisRespository.findAllByPage(index, pageSize);
    }

    @Override
    public List<EmergencyEquis> findEquisByCategoryAndPage(String category, Integer page, Integer pageSize) {
        log.trace("Executing findEquisByCategoryAndPage[{}]", category, page, pageSize);
        Integer index = page * pageSize;
        return equisRespository.findEquisByCategoryAndPage(category, page, pageSize);
    }

    @Override
    public Integer findEquisPageNum(Integer size) {
        log.trace("Executing findEquisPageNum [{}]", size);
        Integer num = (equisRespository.AllEquisCount() + size - 1) / size;
        return num;
    }

    @Override
    public EmergencyEquis findEquisById(Integer equis_id) {
        log.trace("Executing findEquisById [{}]", equis_id);
        return equisRespository.findEmergencyEquisById(equis_id);
    }

/*    @Override
    public List<EmergencyEquis> findEquisByName(String name) {
        log.trace("Executing findEquisByName [{}]", name);
        return equisRespository.findEmergencyEquisByName(name);
    }

    @Override
    public List<EmergencyEquis> findEquisByAffiliation(String affiliation) {
        log.trace("Executing findEquisByAffiliation [{}]", affiliation);
        return equisRespository.findEmergencyEquisByAffiliation(affiliation);
    }

    @Override
    public List<EmergencyEquis> findEquisByLocation(String location) {
        log.trace("Executing findEquisByLocation [{}]", location);
        return equisRespository.findEmergencyEquisByLocation(location);
    }*/

    @Override
    public Integer getEquisCount() {
        log.trace("Executing getEquisCount [{}]");
        Integer count = equisRespository.AllEquisCount();
        return count;
    }

    @Override
    public Integer EquisCountOfCategory(String category) {
        log.trace("Executing EquisCountOfCategory[{}]");
        Integer count = equisRespository.EquisCountOfCategory(category);
        return count;
    }

    @Override
    public void save(EmergencyEquis emergencyEquis) {
        log.trace("Executing save [{}]");
        equisRespository.save(emergencyEquis);
    }

    @Override
    public void update(EmergencyEquis emergencyEquis) {
        log.trace("Executing update [{}]");
        equisRespository.update(emergencyEquis);
    }

    @Override
    public void deleteById(Integer equis_id) {
        log.trace("Executing deleteById [{}]");
        equisRespository.deleteById(equis_id);
    }

    @Override
    public List<EmergencyEquis> findAllEquis() {
        log.trace("Executing findAllSupplies [{}]");
        return equisRespository.findAllEquis();
    }
}
