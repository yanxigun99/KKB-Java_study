package com.abc.rpc.provider;

import com.abc.rpc.api.SomeService;
import org.springframework.stereotype.Service;

@Service
public class SomeServiceImpl implements SomeService {
    @Override
    public String doSome(String depart) {
        return depart + "欢迎你";
    }
}
