package cn.edu.bupt.demo.dao.StaffNumber;

import cn.edu.bupt.demo.entity.StaffNumber;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.net.ssl.SNIHostName;
import java.util.List;

/**
 * @author zy
 * @date 2018/10/18 上午11:42
 */

public interface StaffService {

    StaffNumber findStaffById(Integer id);

    List<StaffNumber> findStaffByName(String name);

    String findStaffName(Integer id);

    Integer StaffCount();

    void save(StaffNumber staffNumber);

    void updateStaff(StaffNumber staffNumber);

    void deleteStaffById(Integer id) throws Exception;

    void deleteStaffByName(String name) throws Exception;

    List<StaffNumber> findAllStaff();
}
