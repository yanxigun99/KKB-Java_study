package com.abc.rpc.registry;


// 定义注册规范
public interface RegistryCenter {
    /**
     *
     * @param serviceName  服务名称，一般为接口名
     * @param serviceAddress  IP:Port
     */
    void register(String serviceName, String serviceAddress);
}
