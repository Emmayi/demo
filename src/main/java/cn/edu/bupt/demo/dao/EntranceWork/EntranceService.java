package cn.edu.bupt.demo.dao.EntranceWork;

import cn.edu.bupt.demo.entity.EntranceWork;
import org.omg.IOP.ENCODING_CDR_ENCAPS;

import java.util.List;

public interface EntranceService {

    EntranceWork findEntranceWorkById(Integer id);

    List<EntranceWork> findEntranceWorkByDate(Long date);

    Integer allWorkCount();

    void save(EntranceWork entranceWork);

    void update(EntranceWork entranceWork);

    void deleteById(Integer id);

    void deleteByDate(Long date);

    List<EntranceWork> findAllWork();

}
