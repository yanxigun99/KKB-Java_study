package com.abc.rpc.discovery;

// 服务发现规范
public interface ServiceDiscovery {
    /**
     *  根据服务名称返回提供者IP+Port
     * @param serviceName  服务名称，一般为业务接口名
     * @return 返回主机信息，格式为IP:Port
     */
    String discover(String serviceName);
}
