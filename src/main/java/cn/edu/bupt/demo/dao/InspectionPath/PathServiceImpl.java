package cn.edu.bupt.demo.dao.InspectionPath;

import cn.edu.bupt.demo.entity.InspectionPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zy
 * @date 2019/5/29 上午11:36
 */

@Slf4j
@Service
public class PathServiceImpl implements PathService {

    @Autowired
    PathRepository pathRepository;

    @Override
    public InspectionPath findPathById(Integer id) {
        log.trace("Executing findPathById [{}]", id);
        return pathRepository.findPathById(id);
    }

    @Override
    public List<InspectionPath> findAllPathByPage(Integer page, Integer pageSize) {
        log.trace("Executing findAllPathByPage [{}]", page, pageSize);
        Integer index = page * pageSize;
        return pathRepository.findAllPathByPage(index, pageSize);
    }

    @Override
    public List<InspectionPath> findPathByArea(String area, Integer page, Integer pageSize) {
        log.trace("Executing findPathByArea [{}]", area, page, pageSize);
        Integer index = page * pageSize;
        return pathRepository.findPathByArea(area, index, pageSize);
    }

    @Override
    public Integer getAllCount() {
        log.trace("Executing getAllCount [{}]");
        Integer count = pathRepository.findAllCount();
        return count;
    }

    @Override
    public Integer findCountOfArea(String area) {
        log.trace("Executing findCountOfPerson [{}]", area);
        Integer count = pathRepository.findCountOfArea(area);
        return count;
    }

    @Override
    public void save(InspectionPath inspectionPath) {
        log.trace("Executing save [{}]", inspectionPath);
        pathRepository.save(inspectionPath);
    }

    @Override
    public void update(InspectionPath inspectionPath) {
        log.trace("Executing update [{}]", inspectionPath);
        pathRepository.update(inspectionPath);
    }

    @Override
    public void deleteById(Integer id) {
        log.trace("Executing deleteById [{}]", id);
        pathRepository.deleteById(id);
    }

    @Override
    public List<InspectionPath> findAll() {
        log.trace("Executing findAll [{}]");
        return pathRepository.findAll();
    }

    @Override
    public String setNumber(String id) {
        int Len = 6 - id.length();
        String num = "LX";
        StringBuilder sb = new StringBuilder();
        sb.append(num);
        for (int i = 0; i < Len; i++) {
            sb.append("0");
        }
        sb.append(id);
        String number = "" + sb;
        return number;
    }
}
