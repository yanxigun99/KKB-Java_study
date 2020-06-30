package com.abc.config;

import com.abc.service.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置类
 */
@Configuration
@ConditionalOnClass(SomeService.class)
@EnableConfigurationProperties(SomeServiceProperties.class)
public class SomeServiceAutoConfiguration {

    @Autowired
    private SomeServiceProperties properties;

    // 注意，以下两个方法的定义顺序是不能颠倒的

    @Bean
    // 当指定属性的值为true时，才会运行该方法
    // 若没有指定该属性，则其结果与指定了该属性值为true的效果相同，即指定了该属性的默认值为true
    @ConditionalOnProperty(name = "some.service.enable", havingValue = "true", matchIfMissing = true)
    public SomeService someService() {
        return new SomeService(properties.getPrefix(), properties.getSuffix());
    }

    @Bean
    // 当容器中存在SomeService类型的实例时，该方法就不会运行，否则，会运行用于创建SomeService实例
    @ConditionalOnMissingBean
    public SomeService someService2() {
        return new SomeService("", "");
    }

}
