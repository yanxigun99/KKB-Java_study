package com.abc.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class SomeServiceImpl implements SomeService {
    @Override
    public String send() {
        return "调用【开发】环境下的send()实现";
    }
}
