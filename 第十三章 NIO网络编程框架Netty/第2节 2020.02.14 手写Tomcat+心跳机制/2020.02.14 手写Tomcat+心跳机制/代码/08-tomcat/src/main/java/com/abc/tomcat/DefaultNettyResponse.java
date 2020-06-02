package com.abc.tomcat;

import com.abc.servnet.NettyResponse;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.internal.StringUtil;

import java.io.UnsupportedEncodingException;

/**
 * Tomcat对NettyResponse规范的实现
 */
public class DefaultNettyResponse implements NettyResponse {

    private HttpRequest request;
    private ChannelHandlerContext context;

    public DefaultNettyResponse(HttpRequest request, ChannelHandlerContext context) {
        this.request = request;
        this.context = context;
    }

    @Override
    public void write(String content) throws Exception {
        if (StringUtil.isNullOrEmpty(content)) {
            return;
        }

        // 创建响应对象
        FullHttpResponse response = new DefaultFullHttpResponse(
                                    HttpVersion.HTTP_1_1,
                                    HttpResponseStatus.OK,
                                    // 根据响应内容为response分配空间
                                    Unpooled.wrappedBuffer(content.getBytes("UTF-8")));
        // 获取到响应头
        HttpHeaders headers = response.headers();
        // 设置响应内容类型
        headers.set(HttpHeaderNames.CONTENT_TYPE, "text/json");
        // 设置响应体长度
        headers.set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        // 设置缓存过期时长
        headers.set(HttpHeaderNames.EXPIRES, 0);

        // 若HTTP请求连接是长连接，则设置响应连接也是长连接
        if (HttpUtil.isKeepAlive(request)) {
            headers.set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

        // 将响应实例写入到Channel
        context.writeAndFlush(response);
    }
}
