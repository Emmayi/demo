package cn.edu.bupt.demo.dao.SysLog;

import cn.edu.bupt.demo.entity.SysLog;

import java.util.List;

/**
 * @author zy
 * @date 2019/5/29 下午9:25
 */
public interface SysLogService {
    List<SysLog> findLogByPage(Integer page, Integer pageSize);

    Integer findAllCount();

    void save(SysLog sysLog);

    String account(Integer user_id);

    String userName(Integer user_id);

    String role(Integer user_id);
}
