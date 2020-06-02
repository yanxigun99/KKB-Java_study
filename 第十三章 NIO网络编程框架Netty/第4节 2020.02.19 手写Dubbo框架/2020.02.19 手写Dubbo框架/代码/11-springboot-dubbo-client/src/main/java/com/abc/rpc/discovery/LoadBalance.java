package com.abc.rpc.discovery;

import java.util.List;

// 负载均衡器
public interface LoadBalance {
    String choose(List<String> servers);
}
