package com.abc.discovery;

import java.util.List;

/**
 * 服务发现规范
 */
public interface ServiceDiscovery {
    /**
     *
     * @param serviceName  服务名称
     * @return  提供指定服务的主机地址
     */
    List<String> discovery(String serviceName) throws Exception;
}
