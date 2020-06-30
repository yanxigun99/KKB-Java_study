package com.abc.rpc.registry;

import com.abc.rpc.constant.ZKConstant;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.springframework.stereotype.Component;

// zk做注册中心的实现
@Component
public class ZKRegistryCenter implements RegistryCenter {
    private CuratorFramework curator;

    {
        curator = CuratorFrameworkFactory.builder()
                .connectString(ZKConstant.ZK_CLUSTER)
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        curator.start();
    }

    @Override
    public void register(String serviceName, String serviceAddress) {
        try {
            String servicePath = ZKConstant.ZK_DUBBO_ROOT_PATH + "/" + serviceName;
            if(curator.checkExists().forPath(servicePath) == null) {
                curator.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(servicePath, "0".getBytes());
            }
            String addressPath = servicePath + "/" + serviceAddress;
            String hostNode = curator.create()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(addressPath, "0".getBytes());
            System.out.println("Service Host Register Success! " + hostNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
