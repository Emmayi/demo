package cn.edu.bupt.demo.dao.StaffNumber;

import cn.edu.bupt.demo.dao.DataValidationException;
import cn.edu.bupt.demo.dao.DataValidator;
import cn.edu.bupt.demo.entity.StaffNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author zy
 * @date 2018/10/18 上午11:43
 */

@Service
@Slf4j
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;
    private DataValidator<StaffNumber> staffValidator =
            new DataValidator<StaffNumber>() {

                @Override
                protected void validateDataImpl(StaffNumber staffNumber) {
                    if (StringUtils.isEmpty(staffNumber.getGender())) {
                        throw new DataValidationException("请填写性别！");
                    }

                    if (StringUtils.isEmpty(staffNumber.getName())) {
                        throw new DataValidationException("请填写姓名！");
                    }

                    if (!StringUtils.isEmpty(staffNumber.getPhone())) {
                        validatePhone(staffNumber.getPhone());
                    }

                }
            };

    @Override
    public StaffNumber findStaffById(Integer id) {
        log.trace("Executing findStaffById [{}]", id);
        return staffRepository.findStaffById(id);
    }

    @Override
    public List<StaffNumber> findStaffByName(String name) {
        log.trace("Executing findStaffByName [{}]", name);
        return staffRepository.findStaffByName(name);
    }

    @Override
    public List<String> findAllStaffName(Integer id) {
        log.trace("Executing findStaffName [{}]", id);
        return staffRepository.findAllStaffName(id);
    }

    @Override
    public String findEmailByName(Integer planId) {
        log.trace("Executing findEmailByName [{}]", planId);
        return staffRepository.findEmailByName(planId);
    }

    @Override
    public Integer StaffCount() {
        log.trace("Executing StaffCount [{}]");
        return staffRepository.findAllCount();
    }

    @Override
    public void save(StaffNumber staffNumber) {
        log.trace("Executing save [{}]", staffNumber);
        staffRepository.save(staffNumber);
    }

    @Override
    public void updateStaff(StaffNumber staffNumber) {
        log.trace("Executing updateStaff [{}]", staffNumber);
        staffRepository.update(staffNumber);
    }

    @Override
    public void deleteStaffById(Integer id) throws Exception {
        log.trace("Executing deleteStaffById, name [{}]", id);
        try {
            staffRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteStaffByName(String name) throws Exception {
        log.trace("Executing deleteStaffByName, name [{}]", name);
        try {
            staffRepository.deleteByName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<StaffNumber> findAllStaff() {
        return staffRepository.findAll();

    }
}
