package com.abc.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    private GenericFutureListener listener;
    private ScheduledFuture<?> schedule;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        randomSend(ctx.channel());
    }

    private void randomSend(Channel channel) {
        int heartBeatInterval = new Random().nextInt(7) + 1;
        System.out.println(heartBeatInterval + "秒后发送下一次心跳");

        schedule = channel.eventLoop().schedule(() -> {
            if (channel.isActive()) {
                System.out.println("向服务器发送心跳");
                channel.writeAndFlush("~PING~");
            } else {
                System.out.println("与服务器的连接已经断开");
            }
        }, heartBeatInterval, TimeUnit.SECONDS);

        listener = (future) -> {
            randomSend(channel);
        };

        schedule.addListener(listener);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        schedule.removeListener(listener);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
