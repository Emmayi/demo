package cn.edu.bupt.demo;

import cn.bupt.edu.base.log.ElasticsearchRestClient;
import cn.edu.bupt.demo.channel.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@PropertySource({"classpath:disconf.properties"})
//@ImportResource({"classpath:disconf.xml"})//引入disconf
public class DemoApplication {

    public static void main(String[] args) {
        ElasticsearchRestClient.initElasticsearchRestClient("info");
        Client.Start();
        SpringApplication.run(DemoApplication.class, args);
    }
}
