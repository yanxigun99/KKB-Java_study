package com.abc.server;


import java.util.*;


public class RpcServer {
    //服务注册表
    private Map<String,Object> registryMap=new HashMap<String, Object>();
    private List<String> classCache=new ArrayList<String>();
    public void publish(String basePackage){
        getProviderClass(basePackage);
        doRegister();
    }

    private void getProviderClass(String basePackage) {
    }

    private void doRegister() {

    }
}
