package com.abc.rpc.server;

import com.abc.rpc.api.InvokeMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Map;

public class RpcServerHandler extends ChannelInboundHandlerAdapter {

   public Map<String, Object> registryMap;

    public RpcServerHandler(Map<String, Object> registryMap) {
        this.registryMap = registryMap;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {

        if(msg instanceof InvokeMessage) {
            // 消费者传来的调用信息
            InvokeMessage message = (InvokeMessage) msg;

            Object result = "提供者没有该方法";
            // 判断注册中心是否存在该服务
            if(registryMap.containsKey(message.getClassName())) {
                // 获取消费者所调用的提供者实例
                Object provider = registryMap.get(message.getClassName());
                result = provider.getClass()
                        .getMethod(message.getMethodName(), message.getParamTypes())
                        .invoke(provider, message.getParamValues());
            }
            ctx.writeAndFlush(result);
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
