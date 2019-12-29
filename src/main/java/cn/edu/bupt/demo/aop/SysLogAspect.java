package cn.edu.bupt.demo.aop;

import cn.edu.bupt.demo.dao.SysLog.SysLogService;
import cn.edu.bupt.demo.entity.SysLog;
import io.swagger.models.auth.In;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * @author zy
 * @date 2019/5/29 下午8:02
 * 系统日志：切面处理类
 */

@Aspect
@Component
public class SysLogAspect {
    private final static Logger log = org.slf4j.LoggerFactory.getLogger(SysLogAspect.class);

    @Autowired
    private SysLogService sysLogService;

    //表示匹配带有自定义注解的方法
    @Pointcut("@annotation(cn.edu.bupt.demo.aop.MyLog)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result =null;
        long beginTime = System.currentTimeMillis();

        try {
            log.info("我在目标方法之前执行！");
            result = point.proceed();
            long endTime = System.currentTimeMillis();
            insertLog(point,endTime-beginTime);
        } catch (Throwable e) {
            // TODO Auto-generated catch block
        }
        return result;
    }

    private void insertLog(ProceedingJoinPoint point ,long time) {
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        SysLog sys_log = new SysLog();

        MyLog userAction = method.getAnnotation(MyLog.class);
        if (userAction != null) {
            // 注解上的描述
            sys_log.setAction(userAction.value());
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //从session中获取当前登陆人id
//        Integer userid = Integer.valueOf(request.getQueryString());

        Integer userid = Integer.valueOf(request.getParameter("user_id"));
        System.out.println(userid);

        String role = sysLogService.role(userid);
        System.out.println(role);

        String account = sysLogService.account(userid);
        System.out.println(account);

        String username = sysLogService.userName(userid);
        System.out.println(username);



//		String userid = (Long)SecurityUtils.getSubject().getSession().getAttribute("user_id");
//		String username = (Long)SecurityUtils.getSubject().getSession().getAttribute("username");


        sys_log.setUser_id(userid.toString());

        sys_log.setUser_name(username);

        sys_log.setCreate_Time(new java.sql.Timestamp(new Date().getTime()));

        sys_log.setAccount(account);

        sys_log.setRole(role);

        log.info(sys_log.toString());

        sysLogService.save(sys_log);
    }

}
