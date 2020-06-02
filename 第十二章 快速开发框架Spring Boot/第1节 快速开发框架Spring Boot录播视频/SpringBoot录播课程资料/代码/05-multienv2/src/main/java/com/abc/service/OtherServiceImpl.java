package com.abc.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("pro")
public class OtherServiceImpl implements SomeService {
    @Override
    public String send() {
        return "调用【生产】环境下的send()实现";
    }
}
