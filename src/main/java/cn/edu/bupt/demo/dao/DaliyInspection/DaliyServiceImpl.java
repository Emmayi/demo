package cn.edu.bupt.demo.dao.DaliyInspection;

import cn.edu.bupt.demo.entity.DailyInspection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zy
 * @date 2019/4/26 上午10:36
 */

@Slf4j
@Service
public class DaliyServiceImpl implements DaliyService{

    @Autowired
    DaliyRepository daliyRepository;

    @Override
    public DailyInspection findTableById(Integer id) {
        log.trace("Executing findTableById [{}]", id);
        return daliyRepository.findTableById(id);
    }

    @Override
    public List<DailyInspection> findTableByInspectionPerson(String inspection_person) {
        log.trace("Executing findTableByInspectionPerson [{}]", inspection_person);
        return daliyRepository.findTableByInspectionPerson(inspection_person);
    }

    @Override
    public List<DailyInspection> findTableByInspectionDate(Long time) {
        log.trace("Executing findTableByInspectionDate [{}]", time);
        return daliyRepository.findTableByInspectionDate(time);
    }

    @Override
    public Integer getAllCount() {
        log.trace("Executing getAllCount [{}]");
        return daliyRepository.findAllCount();
    }

    @Override
    public void save(DailyInspection dailyInspection) {
        log.trace("Executing save [{}]", dailyInspection);
        daliyRepository.save(dailyInspection);
    }

    @Override
    public void update(DailyInspection dailyInspection) {
        log.trace("Executing update [{}]", dailyInspection);
        daliyRepository.update(dailyInspection);
    }

    @Override
    public void deleteById(Integer id) {
        log.trace("Executing deleteById [{}]", id);
        daliyRepository.deleteById(id);
    }

    @Override
    public List<DailyInspection> findAll() {
        log.trace("Executing findAll [{}]");
        return daliyRepository.findAll();
    }
}
