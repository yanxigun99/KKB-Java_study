package com.abc.rpc.discovery;

import com.abc.rpc.constant.ZKConstant;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ServiceDiscoveryImpl implements ServiceDiscovery {
    @Autowired
    private LoadBalance loadBalance;

    List<String> servers = Collections.synchronizedList(new ArrayList<>());

    private CuratorFramework curator;

    public ServiceDiscoveryImpl() {
        this.curator = CuratorFrameworkFactory.builder()
                .connectString(ZKConstant.ZK_CLUSTER)
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        curator.start();
    }

    @Override
    public String discover(String serviceName) {
        try {
            String servicePath =
                    ZKConstant.ZK_DUBBO_ROOT_PATH + "/" + serviceName;
            servers = curator.getChildren().forPath(servicePath);
            if(servers.size() == 0) {
                return null;
            }
            registerWatch(servicePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loadBalance.choose(servers);
    }

    // 向指定路径添加子节点列表监听watcher
    private void registerWatch(String servicePath) throws Exception {
        PathChildrenCache cache =
                new PathChildrenCache(curator, servicePath, true);
        PathChildrenCacheListener cacheListener = (client, event) -> {
            servers = client.getChildren().forPath(servicePath);
        };

        cache.getListenable().addListener(cacheListener);
        cache.start();
    }
}
