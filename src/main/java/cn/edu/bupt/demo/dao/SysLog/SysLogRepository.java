package cn.edu.bupt.demo.dao.SysLog;

import cn.edu.bupt.demo.entity.SysLog;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.apache.ibatis.annotations.*;

import java.beans.IntrospectionException;
import java.util.List;

/**
 * @author zy
 * @date 2019/5/29 下午9:30
 */
@Mapper
public interface SysLogRepository {
    @Select("select * from sys_log order by id desc limit #{index},#{pageSize}")
    List<SysLog> findLogByPage(@Param("index")Integer index,
                               @Param("pageSize")Integer pageSize);

    @Select("select count(*) from sys_log")
    Integer findAllCount();

    @Insert("insert into sys_log (user_id,account,user_name,action,create_Time,role) " +
            "values (#{user_id},#{account},#{user_name},#{action},#{create_Time},#{role})")
    @Options(useGeneratedKeys = true,keyProperty="id",keyColumn = "id")
    void save(SysLog sysLog);


    @Select("select email from `user` where id = #{id}")
    String account (Integer id);

    @Select("select name from `user` where id = #{id}")
    String userName (Integer id);

    @Select("select description from role where id in (select role_id from role_user_relation where user_id = #{user_id})")
    String role (Integer user_id);
}
