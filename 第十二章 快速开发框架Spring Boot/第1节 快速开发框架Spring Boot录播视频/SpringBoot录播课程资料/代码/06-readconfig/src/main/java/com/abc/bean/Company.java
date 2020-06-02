package com.abc.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource(value = "classpath:custom.properties", encoding = "UTF-8")
@ConfigurationProperties("ghi.company")
@Data
public class Company {
    private List<Depart> departs;
}
