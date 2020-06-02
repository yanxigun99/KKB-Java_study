package com.abc.tomcat;

import com.abc.servnet.NettyRequest;
import com.abc.servnet.NettyResponse;
import com.abc.servnet.Servnet;

/**
 * Tomcat中默认的Servnet
 * 当用户访问的Servnet不存在时，会自动访问该默认的Servnet
 */
public class DefaultServnet extends Servnet {
    @Override
    public void doGet(NettyRequest request, NettyResponse response) throws Exception {
        // 从请求中获取用户所要访问的Servnet的名称
        // 例如用户提交的请求为：
        // http://localhost:8888/otherservlet/abc/def?name=zs
        String servnetName = request.getUri().split("/")[1];
        String content = "404 - no this Servnet: " + servnetName;
        response.write(content);
    }

    @Override
    public void doPost(NettyRequest request, NettyResponse response) throws Exception {
        doGet(request, response);
    }
}
