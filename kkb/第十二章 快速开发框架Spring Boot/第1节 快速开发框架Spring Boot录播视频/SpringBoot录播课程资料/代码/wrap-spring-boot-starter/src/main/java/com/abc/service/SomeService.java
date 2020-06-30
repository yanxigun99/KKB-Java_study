package com.abc.service;

import lombok.AllArgsConstructor;

/**
 * 核心业务类
 */
@AllArgsConstructor  //  全参构造器
public class SomeService {
    private String before;
    private String after;

    // 核心业务代码
    public String wrap(String word) {
        return before + word + after;
    }
}
