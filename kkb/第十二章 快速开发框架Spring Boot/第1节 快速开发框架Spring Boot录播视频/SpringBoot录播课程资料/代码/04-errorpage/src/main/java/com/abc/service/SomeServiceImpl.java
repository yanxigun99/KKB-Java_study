package com.abc.service;

import org.springframework.stereotype.Service;

@Service
public class SomeServiceImpl implements SomeService {
    @Override
    public String hello() {
        return "Hello Hello Hello";
    }
}
