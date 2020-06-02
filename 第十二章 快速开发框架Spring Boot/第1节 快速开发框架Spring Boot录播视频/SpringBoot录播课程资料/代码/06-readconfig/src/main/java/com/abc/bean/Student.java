package com.abc.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:custom.properties", encoding = "UTF-8")
@ConfigurationProperties("abc.student")
@Data
public class Student {
    private String name;
    private int age;
    private double score;
}
