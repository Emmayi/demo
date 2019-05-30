package cn.edu.bupt.demo.dao.SysLog;

import cn.edu.bupt.demo.entity.SysLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zy
 * @date 2019/5/29 下午9:30
 */
@Mapper
public interface SysLogRepository {
    @Select("select * from sys_log limit #{index},#{pageSize}")
    List<SysLog> findLogByPage(@Param("index")Integer index,
                               @Param("pageSize")Integer pageSize);

    @Select("select count(*) from sys_log")
    Integer findAllCount();

    @Insert("insert into sys_log (user_id,user_name,action,create_Time) " +
            "values (#{user_id},#{user_name},#{action},#{create_Time})")
    @Options(useGeneratedKeys = true,keyProperty="id")
    void save(SysLog sysLog);
}
