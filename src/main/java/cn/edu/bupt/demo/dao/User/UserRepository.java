package cn.edu.bupt.demo.dao.User;

import cn.edu.bupt.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRepository {


    @Select("select u.email from inspection_plan as i, user as u where i.inspection_person = u.name and i.id = #{id}")
    String findEmailByName(Integer id);

    @Select("select count(*) from user")
    Integer findAllCount();

    @Select("select * from user where id > 0")
    List<User> findAll();

}