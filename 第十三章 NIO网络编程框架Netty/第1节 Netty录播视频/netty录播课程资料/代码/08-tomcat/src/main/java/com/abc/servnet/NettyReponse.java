package com.abc.servnet;

public interface NettyReponse {
    /**
     * 将响应写入到Channel
     * @param content
     */
    void write(String content) throws Exception;
}
