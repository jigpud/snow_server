package com.jigpud.snow.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jigpud.snow.util.jackson.CustomSerializerProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author : jigpud
 */
@Configuration
public class JacksonConfig {
    @Bean
    ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper =  builder.createXmlMapper(false).build();
        objectMapper.setSerializerProvider(new CustomSerializerProvider());
        return objectMapper;
    }
}
