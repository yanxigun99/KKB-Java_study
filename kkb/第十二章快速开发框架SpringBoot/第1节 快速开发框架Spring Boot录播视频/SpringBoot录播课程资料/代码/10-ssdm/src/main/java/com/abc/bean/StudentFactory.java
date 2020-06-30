package com.abc.bean;

import org.springframework.stereotype.Component;

/**
 *  通过当前类的getInstance()方法可以获取到一个单例的Student对象
 *  注意：当前的代码与当前的应用程序没有任何关系
 *
 *  当前代码中存在线程安全问题：
 *  解决方案有三种：
 *  1）在方法签名上添加synchronized，使方法变为同步方法
 *  2）在存在线程安全问题的成员变量声明前添加volatile
 *  3）若存在线程安全问题的成员变量为Integer、Long、Boolean等，可以将它们
 *     定义为AtomicXxx类型
 *
 */
@Component   // 当前类是单例的
public class StudentFactory {
    private volatile Student student;

    public Student getInstance() {
        if(student == null) {
            synchronized (this) {
                if(student == null) {
                    // 以下语句的底层实现由三步构成：
                    // 1）申请堆空间m
                    // 2）使用对象的初始化数据初始化堆空间m
                    // 3）将student引用指向堆空间m
                    student = new Student("张三", 33);
                }
            }
        }
        return student;
    }
}
