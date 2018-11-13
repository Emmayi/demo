package cn.edu.bupt.demo.dao.EntranceWork;

import cn.edu.bupt.demo.entity.EntranceWork;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zy
 * @date 2018/10/24 下午4:51
 */

@Slf4j
@Service
public class EntranceServiceImpl implements EntranceService{

    @Autowired
    EntranceRepository entranceRepository;

    @Override
    public EntranceWork findEntranceWorkById(Integer id) {
        log.trace("Executing findEntranceWorkById [{}]", id);
        return entranceRepository.findEntranceWorkById(id);
    }

    @Override
    public List<EntranceWork> findEntranceWorkByDate(Long date) {
        log.trace("Executing findEntranceWorkByDate [{}]", date);
        return entranceRepository.findEntranceWorkByDate(date);
    }

    @Override
    public Integer allWorkCount() {
        log.trace("Executing allWorkCount [{}]");
        return entranceRepository.AllWorkCount();
    }

    @Override
    public void save(EntranceWork entranceWork) {
        log.trace("Executing save [{}]");
        entranceRepository.save(entranceWork);
    }

    @Override
    public void update(EntranceWork entranceWork) {
        log.trace("Executing update [{}]");
        entranceRepository.update(entranceWork);
    }

    @Override
    public void deleteById(Integer id) {
        log.trace("Executing deleteById [{}]",id);
        entranceRepository.deleteById(id);
    }

    @Override
    public void deleteByDate(Long date) {
        log.trace("Executing deleteByDate [{}]",date);
        entranceRepository.deleteByDate(date);
    }

    @Override
    public List<EntranceWork> findAllWork() {
        log.trace("Executing findAllWork [{}]");
        return entranceRepository.findAll();
    }
}
