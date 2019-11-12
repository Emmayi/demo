package cn.edu.bupt.demo.swagger;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zy
 * @date 2019/10/19 下午4:33
 */
@Configuration  //让Spring来加载该类配置
@EnableSwagger2 //启用Swagger2
public class Swagger2Config {

    static final Logger log= LoggerFactory.getLogger(Swagger2Config.class);
    @Bean
    public Docket createRestApi() {
        log.info("加载Swagger2");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select()
                //扫描指定包中的swagger注解
                .apis(RequestHandlerSelectors.basePackage("cn.edu.bupt.demo.controller"))
                //扫描所有有注解的api，用这种方式更灵活
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("web")
                .description("智慧管廊管理系统后台API接口文档")
                .termsOfServiceUrl("localhost:8100/")
                .version("1.0.0")
                .build();
    }

}
