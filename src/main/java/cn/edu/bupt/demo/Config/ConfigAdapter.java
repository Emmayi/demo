package cn.edu.bupt.demo.Config;


import cn.edu.bupt.demo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zyf on 2019/5/21.
 */
@Configuration
public class ConfigAdapter extends WebMvcConfigurerAdapter {

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/api/v1/info/allPlan") //需要拦截的请求
                .addPathPatterns("/api/v1/info/emergencyEquisByPage")
                .addPathPatterns("/api/v1/info/equisPage")
                .addPathPatterns("/api/v1/info/equis")
                .addPathPatterns("/api/v1/info/equisALL")
                .addPathPatterns("/api/v1/info/emergencyTeamByPage")
                .addPathPatterns("/api/v1/info/teamPage")
                .addPathPatterns("/api/v1/info/team")
                .addPathPatterns("/api/v1/info/teamALL")
                .addPathPatterns("/api/v1/info/emergencySuppliesByPage")
                .addPathPatterns("/api/v1/info/suppliesPage")
                .addPathPatterns("/api/v1/info/supplies")
                .addPathPatterns("/api/v1/info/suppliesALL")
                .addPathPatterns("/api/v1/info/emergencyPlaceByPage")
                .addPathPatterns("/api/v1/info/placePage")
                .addPathPatterns("/api/v1/info/place")
                .addPathPatterns("/api/v1/info/placeALL")
                .addPathPatterns("/api/v1/info/entranceWorkByPage")
                .addPathPatterns("/api/v1/info/entranceWorkById")
                .addPathPatterns("/api/v1/info/entranceWork")
                .addPathPatterns("/api/v1/info/entranceWorkEvaluation")
                .addPathPatterns("/api/v1/info/entranceWorkByDate")
                .addPathPatterns("/api/v1/info/emergencyPlanByPage")
                .addPathPatterns("/api/v1/info/emergencyPages")
                .addPathPatterns("/api/v1/info/emergencyById")
                .addPathPatterns("/api/v1/info/emergencyByLevel")
                .addPathPatterns("/api/v1/info/emergency")
                .addPathPatterns("/api/v1/info/emergencyALL")
                .addPathPatterns("/api/v1/info/showFile/{name}/{type}")
                .addPathPatterns("/api/v1/info/uploadFile")
                .addPathPatterns("/api/v1/info/download/{name}/{type}/{fileName}/{fileType}")
                .addPathPatterns("/api/v1/info/delete/{name}/{type}/{fileName}/{fileType}")
                .addPathPatterns("/api/v1/info/inspectionPlanByPage")
                .addPathPatterns("/api/v1/info/planById")
                .addPathPatterns("/api/v1/info/plan")
                .addPathPatterns("/api/v1/info/planStatus")
                .addPathPatterns("/api/v1/info/pipeByPage")
                .addPathPatterns("/api/v1/info/pipePage")
                .addPathPatterns("/api/v1/info/pipeGallery")
                .addPathPatterns("/api/v1/info/pipeGalleryAll")
                .addPathPatterns("/api/v1/info/galleryAreaByPage")
                .addPathPatterns("/api/v1/info/galleryAreaPage")
                .addPathPatterns("/api/v1/info/galleryArea")
                .addPathPatterns("/api/v1/info/galleryAreaAll")
                .addPathPatterns("/api/v1/info/staffById")
                .addPathPatterns("/api/v1/info/staffByName")
                .addPathPatterns("/api/v1/info/staff")
                .addPathPatterns("/api/v1/info/staffCount")
                .addPathPatterns("/api/v1/info/staffName")
                .addPathPatterns("/api/v1/info/staffId")
                .addPathPatterns("/api/v1/info/allStaff")
                .addPathPatterns("/api/v1/info/inspectionPathByPage")
                .addPathPatterns("/api/v1/info/inspectionPath")
                .addPathPatterns("/api/v1/info/inspection")
                .addPathPatterns("/api/v1/info/allPath")
                .addPathPatterns("/api/v1/info/inspectionReportByPage")
                .addPathPatterns("/api/v1/info/inspectionById")
                .addPathPatterns("/api/v1/info/inspectionByDutyPerson")
                .addPathPatterns("/api/v1/info/inspectionByPlanId")
                .addPathPatterns("/api/v1/info/inspection")
                .addPathPatterns("/api/v1/info/inspectionId")
                .addPathPatterns("/api/v1/info/inspectionPerson")
                .addPathPatterns("/api/v1/info/allReport")
                .addPathPatterns("/api/v1/info/inspection/upload")
                .addPathPatterns("/api/v1/info/inspection/delete/{id}/{type}/{fileName}/{fileType}")
/*                .addPathPatterns("/api/v1/info/")
                .addPathPatterns("/api/v1/info/")
                .addPathPatterns("/api/v1/info/")
                .addPathPatterns("/api/v1/info/")
                .addPathPatterns("/api/v1/info/")
                .addPathPatterns("/api/v1/info/")
                .addPathPatterns("/api/v1/info/")
                .addPathPatterns("/api/v1/info/")
                .addPathPatterns("/api/v1/info/")
                .addPathPatterns("/api/v1/info/")
                .addPathPatterns("/api/v1/info/")*/
                .excludePathPatterns("/api/v1/user/");  //不拦截的请求
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/api/v1/user/reservePlanById");
    }
}
