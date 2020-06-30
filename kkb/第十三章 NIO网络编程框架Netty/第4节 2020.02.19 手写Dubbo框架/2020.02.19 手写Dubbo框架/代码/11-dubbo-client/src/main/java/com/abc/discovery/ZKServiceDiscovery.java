package com.abc.discovery;

import com.abc.constant.ZKConstant;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * 从zk进行服务发现
 */
public class ZKServiceDiscovery implements ServiceDiscovery {
    private CuratorFramework client;
    private List<String> invokerPaths;

    public ZKServiceDiscovery() {
        // 创建并初始化zk的客户端
        client = CuratorFrameworkFactory.builder()
                // 指定要连接的zk集群
                .connectString(ZKConstant.ZK_CLUSTER)
                // 指定连接超时时限
                .connectionTimeoutMs(10000)
                // 指定会话超时时间
                .sessionTimeoutMs(4000)
                // 指定重试策略：每重试一次，休息1秒，最多重试10次
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        // 启动zk的客户端
        client.start();
    }

    @Override
    public List<String> discovery(String serviceName) throws Exception {
        String servicePath = ZKConstant.ZK_DUBBO_ROOT_PATH + "/" + serviceName;
        // 从zk中获取指定服务名称节点的所有子节点列表
       invokerPaths = client.getChildren().forPath(servicePath);
        // 为服务名称节点添加watcher监听
        registerWatcher(servicePath);
        return invokerPaths;
    }

    private void registerWatcher(String servicePath) throws Exception {
        // cache中可以缓存指定节点的所有子节点的数据
        PathChildrenCache cache = new PathChildrenCache(client, servicePath, true);

        cache.getListenable().addListener((client, event) -> {
            invokerPaths = client.getChildren().forPath(servicePath);
        });
        //启动监听
        cache.start();
    }
}
