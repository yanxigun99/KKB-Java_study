package com.abc.servnet;

import java.io.UnsupportedEncodingException;

/**
 * 响应规范
 */
public interface NettyResponse {
    /**
     * 将响应写入到Channel
     * @param content
     */
    void write(String content) throws Exception;
}
