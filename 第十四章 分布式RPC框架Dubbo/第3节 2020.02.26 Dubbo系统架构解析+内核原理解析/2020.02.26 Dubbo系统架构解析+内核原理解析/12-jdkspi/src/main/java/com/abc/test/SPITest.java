package com.abc.test;

import com.abc.service.SomeService;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SPITest {
    public static void main(String[] args) {
        ServiceLoader<SomeService> loader = ServiceLoader.load(SomeService.class);
        Iterator<SomeService> iterator = loader.iterator();

        while (iterator.hasNext()) {
            SomeService service = iterator.next();
            service.hello();
        }
    }
}
