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
public class DaliyServiceImpl implements DaliyService {

    @Autowired
    DaliyRepository daliyRepository;

    @Override
    public DailyInspection findTableById(Integer id) {
        log.trace("Executing findTableById [{}]", id);
        return daliyRepository.findTableById(id);
    }

    @Override
    public List<DailyInspection> findTableByInspectionPerson(String inspection_person, Integer page, Integer pageSize) {
        log.trace("Executing findTableByInspectionPerson [{}]", inspection_person, page, pageSize);
        Integer index = page * pageSize;
        return daliyRepository.findTableByInspectionPerson(inspection_person, index, pageSize);
    }

    @Override
    public List<DailyInspection> findAllByPage(Integer page, Integer pageSize) {
        log.trace("Executing findAllByPage [{}]", page, pageSize);
        Integer index = page * pageSize;
        return daliyRepository.findAllByPage(index, pageSize);
    }

    @Override
    public Integer getAllCount() {
        log.trace("Executing getAllCount [{}]");
        return daliyRepository.findAllCount();
    }

    @Override
    public Integer findCountByPerson(String inspection_person) {
        log.trace("Executing findCountByPerson [{}]");
        return daliyRepository.findCountByPerson(inspection_person);
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
