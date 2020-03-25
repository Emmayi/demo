package cn.edu.bupt.demo.Config;

import cn.bupt.edu.base.json2protobuf.JacksonProtobufSupport;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

@Configuration
public class JsonConfig extends JacksonProtobufSupport {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return super.jackson2ObjectMapperBuilderCustomizer();
    }

    @Bean
    public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return super.protobufHttpMessageConverter();
    }
}
