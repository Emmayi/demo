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
    public List<EntranceWork> findALlByPage(Integer page, Integer pageSize) {
        log.trace("Executing findALlByPage [{}]", page,pageSize);
        return entranceRepository.findAllByPage(page,pageSize);
    }

    @Override
    public Integer findEntranceWorkPageNum(Integer size) {
        log.trace("Executing findEntranceWorkPageNum [{}]", size);
        Integer num = (entranceRepository.AllWorkCount()+size-1)/size;
        return num;
    }

    @Override
    public EntranceWork findEntranceWorkById(Integer id) {
        log.trace("Executing findEntranceWorkById [{}]", id);
        return entranceRepository.findEntranceWorkById(id);
    }

    @Override
    public List<EntranceWork> findEntranceWorkByRange(String  activity_range) {
        log.trace("Executing findEntranceWorkByRange [{}]", activity_range);
        return entranceRepository.findEntranceWorkByRange(activity_range);
    }

    @Override
    public Integer allWorkCount() {
        log.trace("Executing allWorkCount [{}]");
        Integer count = entranceRepository.AllWorkCount();
        return count;
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
    public void evaluation(String evaluation,Integer id ) {
        log.trace("Executing evaluation [{}]");
        entranceRepository.evaluation(evaluation,id);
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
