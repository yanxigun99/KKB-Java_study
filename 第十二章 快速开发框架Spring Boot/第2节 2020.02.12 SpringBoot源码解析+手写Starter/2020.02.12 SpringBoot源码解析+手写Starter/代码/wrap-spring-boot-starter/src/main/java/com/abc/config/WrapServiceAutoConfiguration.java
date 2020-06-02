package com.abc.config;

import com.abc.service.WrapService;
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
@ConditionalOnClass(WrapService.class)
@EnableConfigurationProperties(WrapServiceProperties.class)
public class WrapServiceAutoConfiguration {
    @Autowired
    private WrapServiceProperties properties;

    // 注意，以下两个方法的顺序是不能颠倒的

    @Bean
    @ConditionalOnProperty(name = "wrap.service.enabled", matchIfMissing = true)
    // @ConditionalOnProperty(name = "wrap.service.enabled", havingValue = "true", matchIfMissing = true)
    public WrapService wrapService2() {
        return new WrapService(properties.getPrefix(), properties.getSuffix());
    }

    @Bean
    @ConditionalOnMissingBean
    public WrapService wrapService() {
        return new WrapService("", "");
    }

}
