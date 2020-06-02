package com.abc.tomcat;

import com.abc.servnet.NettyRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.net.*;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;

public class DefaultBettyRequest implements NettyRequest {
    private HttpRequest request;
    public DefaultBettyRequest(HttpRequest request){
        this.request=request;
    }
    public URI getUri() {
        return request.uri();
    }

    public String getMethod() {
        return request.method();
    }

    public Map<String, List<String>> getParameters() {
        //对请求URI进行解码
        QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
        return decoder.parameters();
    }

    public List<String> getParameters(String name) {
        Map<String, List<String>> parameters = this.getParameters();
        return parameters.get(name);
    }

    public String getParameter(String name) {
        List<String> list = this.getParameters(name);
        if(list==null||list.size()==0){
            return null;
        }
        return list.get(0) ;
    }

    public String getPath() {
        QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
        return decoder.path();
    }
}
