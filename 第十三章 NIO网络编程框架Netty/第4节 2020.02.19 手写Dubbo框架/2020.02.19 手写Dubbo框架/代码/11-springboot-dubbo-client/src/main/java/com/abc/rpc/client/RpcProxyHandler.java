package com.abc.rpc.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

@Component
public class RpcProxyHandler
               extends SimpleChannelInboundHandler<Object> {

    private Object result;
    public Object getResult() {
        return this.result;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg)
                       throws Exception {
        // msg即为服务端的传来的方法调用结果
        this.result = msg;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
