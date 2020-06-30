package com.abc.tomcat;

import com.abc.servnet.NettyReponse;
import com.abc.servnet.NettyRequest;
import com.abc.servnet.Servnet;

/**
 * TOMCAT中默认的servnet
 * 当用户访问servnet不存在时，会自动访问默认的servnet
 */

public class DefaultServnet extends Servnet {

    @Override
    protected void doGet(NettyRequest request, NettyReponse response) throws Exception {
        //从请求中获取用户所要范围的servnet的名称
        //例如用户提交的请求为：
        String servnetName=request.getUri().split("/")[1];
        String content="404-no this Servnet:"+servnetName;
        response.write(content);

    }

    @Override
    protected void doPost(NettyRequest request, NettyReponse response)throws  Exception{

    }
}
