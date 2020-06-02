package com.abc.tomcat;

import com.abc.servnet.NettyReponse;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;


/**
 * tomcat 对nettyResponse规范的实习
 */


public class DefaultNettyReponse implements NettyReponse {
//创建响应对象
    private HttpRequest request;
    private ChannelHandlerContext context;

    public DefaultNettyReponse(HttpRequest request, ChannelHandlerContext context) {
        this.request = request;
        this.context = context;
    }

    @Override
    public void write(String content) throws Exception {
        FullHttpResponse reponse = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                //根据响应内容response分配空间
                Unpooled.wrappedBuffer(content.getBytes("UTF-8"))
        );
        //获得响应头
        HttpHeaders headers = reponse.headers();
        //设置响应内容
        headers.set(HttpHeaderNames.CONTENT_TYPE,"text/json");
        //设置响应长度
        headers.set(HttpHeaderNames.CONTENT_LENGTH,reponse.content().readableBytes());
        //设置缓存过期时长
        headers.set(HttpHeaderNames.EXPIRES,0);
        //若http请求连接为长连接，则设置响应连接也是长连接
        if(HttpUtil.isKeepAlive(request)){
            headers.set(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);
        }
        //将响应实例写入到Channel
        context.writeAndFlush(reponse);

    }
}
