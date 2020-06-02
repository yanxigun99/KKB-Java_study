package com.abc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置文件中属性的封装类
 * 要读取并封装如下属性：
 *   wrap.service.prefix
 *   wrpa.service.suffix
 */
@Data
@ConfigurationProperties(prefix = "wrap.service")
public class WrapServiceProperties {
    private String prefix;
    private String suffix;
}
