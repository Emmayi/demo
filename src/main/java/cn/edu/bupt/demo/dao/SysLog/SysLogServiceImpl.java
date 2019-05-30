package cn.edu.bupt.demo.dao.SysLog;

import cn.edu.bupt.demo.entity.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zy
 * @date 2019/5/29 下午9:28
 */
@Slf4j
@Service
public class SysLogServiceImpl implements SysLogService{

    @Autowired
    SysLogRepository sysLogRepository;

    @Override
    public List<SysLog> findLogByPage(Integer page, Integer pageSize) {
        log.trace("Executing findLogByPage [{}]", page,pageSize);
        Integer index = page * pageSize;
        return sysLogRepository.findLogByPage(index, pageSize);
    }

    @Override
    public Integer findAllCount() {
        log.trace("Executing findAllCount [{}]");
        return sysLogRepository.findAllCount();
    }

    @Override
    public void save(SysLog sysLog) {
        log.trace("Executing save [{}]",sysLog);
        sysLogRepository.save(sysLog);
    }
}
