package com.skillforge.utils;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@PropertySource("classpath:application.properties")
@AllArgsConstructor
 public class GetterValueFromApplicationProperties {

    ApplicationContext context;
    public String getValue(String propertyName){
        Environment environment = context.getEnvironment();
        Optional<String> op = Optional.ofNullable(environment.getProperty(propertyName));
        return op.orElseThrow(()->new IllegalArgumentException("This property name don't exist :"+propertyName));
    }
}
