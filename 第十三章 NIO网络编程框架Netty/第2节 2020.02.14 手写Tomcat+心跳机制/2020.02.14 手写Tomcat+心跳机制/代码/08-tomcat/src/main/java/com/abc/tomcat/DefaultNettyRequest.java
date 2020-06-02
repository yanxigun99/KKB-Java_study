package com.abc.tomcat;

import com.abc.servnet.NettyRequest;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

/**
 * Tomcat对NettyRequest的实现
 */
public class DefaultNettyRequest implements NettyRequest {

    private HttpRequest request;

    public DefaultNettyRequest(HttpRequest request) {
        this.request = request;
    }

    @Override
    public String getUri() {
        return request.uri();
    }

    @Override
    public String getMethod() {
        return request.method().name();
    }

    @Override
    public Map<String, List<String>> getParameters() {
        // 对请求URI进行解码
        QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
        return decoder.parameters();
    }

    @Override
    public List<String> getParameters(String name) {
        Map<String, List<String>> parameters = this.getParameters();
        return parameters.get(name);
    }

    @Override
    public String getParameter(String name) {
        List<String> list = this.getParameters(name);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public String getPath() {
        QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
        return decoder.path();
    }
}
