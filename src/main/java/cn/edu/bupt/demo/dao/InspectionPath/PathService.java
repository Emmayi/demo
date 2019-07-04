package cn.edu.bupt.demo.dao.InspectionPath;

import cn.edu.bupt.demo.entity.InspectionPath;

import java.util.List;

public interface PathService {

    InspectionPath findPathById(Integer id);

    List<InspectionPath> findAllPathByPage(Integer index, Integer pageSize);

    List<InspectionPath> findPathByArea(String area,Integer index, Integer pageSize);

    Integer getAllCount();

    Integer findCountOfArea(String area);

    String setNumber(String number);

    void save(InspectionPath inspectionPath);

    void update(InspectionPath inspectionPath);

    void deleteById(Integer id);

    List<InspectionPath> findAll();


}
