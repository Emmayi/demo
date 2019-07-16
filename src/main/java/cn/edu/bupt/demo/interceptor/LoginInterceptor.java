package cn.edu.bupt.demo.interceptor;

import cn.edu.bupt.demo.annotation.Auth;
import cn.edu.bupt.demo.dao.Role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zyf on 2019/5/21.
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    protected RoleService roleService;
    private static LoginInterceptor loginInterceptor;

    @PostConstruct
    public void init(){
        loginInterceptor = this;
        loginInterceptor.roleService = this.roleService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //
        //        System.out.println("preHandle");
        //        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
        //            System.out.println("cat cast handler to HandlerMethod.class");
        //            return true;
        //        }
        //        // 获取注解
        Auth auth = ((HandlerMethod) handler).getMethod().getAnnotation(Auth.class);
        if (auth == null) {
            System.out.println("cant find @Auth in this uri:" + request.getRequestURI());
            return true;
        }
        // 从参数中取出用户的角色身份并验证
        if (auth.roles().length > 0) {
            // 如果角色配置不为空, 则取出角色名称
            String[] roles = auth.roles();
            Set<String> authSet = new HashSet<>();
            for (String role : roles) {
                // 将角色信息加入一个set集合中
                authSet.add(role);
            }
            // 这里我为了方便是直接参数传入权限, 在实际操作中应该是从参数中获取用户Id
            // 到数据库权限表中查询用户拥有的权限集合, 与set集合中的权限进行对比完成权限校验
//            GetUserId getId = new GetUserId();
//            int id = getId.returnUserId();
            String user_id = request.getParameter("user_id");
            int id = Integer.parseInt(user_id);
            Set<String> currentRoles = loginInterceptor.roleService.findRolesNameByUserId(id);
            for(String role : currentRoles){
                if (!authSet.contains(role)) {
                    // 校验通过返回false, 否则拦截请求
                    System.out.println("permission denied");
                    response.setStatus(403);
                    return false;
                }
            }
//            String role = request.getParameter("roles");
//            //if (StringUtils.isNotBlank(role)) {
//            if (!authSet.contains(role)) {
//                // 校验通过返回false, 否则拦截请求
//                System.out.println("permission denied");
//                response.setStatus(403);
//                return false;
//            }
//            //}
        }
        // 拦截之后应该返回公共结果, 这里没做处理
        return true;
    }
//        String[] admin = auth.user();
//        if (!admin.equals(request.getParameter("user"))) {
//            System.out.println("permission denied");
//            response.setStatus(403);
//            return false;
//        }
//        return true;

}

