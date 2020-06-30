package com.abc.servnet;

import java.net.URI;
import java.util.*;

/**
 * 请求规范
 */
public interface NettyRequest {
    /**
     * 获取包含路径和请求参数
     * @return
     */
    URI getUri();
    /**
     * 获取请求方式（get post）
     */
    String getMethod();
    Map<String, List<String>> getParameters();
    /**
     * 获得指定名称的所有请求参数
     */
    List<String> getParameters(String name);
    /**
     * 获得指定名称的请求参数的第一个值
     */
    String getParameter(String name);

    /**
     * 获取请求路径，不包含请求参数
     * @return
     */
    String getPath();

}
