package cn.edu.bupt.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@PropertySource({"classpath:disconf.properties"})
//@ImportResource({"classpath:disconf.xml"})//引入disconf
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
