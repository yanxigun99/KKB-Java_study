package com.abc.servnet;

import java.util.List;
import java.util.Map;

/**
 * 请求规范
 */
public interface NettyRequest {
    /**
     * 获取URI，包含请求参数，即?后面的内容
     * @return
     */
    String getUri();

    /**
     * 获取请求方式（GET、POST)
     * @return
     */
    String getMethod();

    /**
     * 获取所有请求参数
     * @return
     */
    Map<String, List<String>> getParameters();

    /**
     * 获取指定名称的所有请求参数
     * @param name
     * @return
     */
    List<String> getParameters(String name);

    /**
     * 获取指定名称的请求参数的第一个值
     * @param name
     * @return
     */
    String getParameter(String name);

    /**
     * 获取请求路径，不包含请求参数的
     * @return
     */
    String getPath();

}
