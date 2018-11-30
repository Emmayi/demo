package cn.edu.bupt.demo;

import cn.edu.bupt.demo.dao.EntranceWork.EntranceRepository;
import cn.edu.bupt.demo.dao.StaffNumber.StaffRepository;
import cn.edu.bupt.demo.entity.EntranceWork;
import cn.edu.bupt.demo.entity.StaffNumber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zy
 * @date 2018/11/29 下午9:09
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class TestDao {

    @Autowired
    StaffRepository staffRepository;

    @Test
    public void testCount() throws Exception {

        Integer count=staffRepository.findAllCount();
        System.out.println("count = "+count);

    }

    @Test
    public void insert() throws Exception {
        StaffNumber staffNumber = new StaffNumber();
        staffNumber.setName("zy");
        staffNumber.setGender("female");
        staffNumber.setPhone("13913813888");

        staffRepository.save(staffNumber);
    }
}
