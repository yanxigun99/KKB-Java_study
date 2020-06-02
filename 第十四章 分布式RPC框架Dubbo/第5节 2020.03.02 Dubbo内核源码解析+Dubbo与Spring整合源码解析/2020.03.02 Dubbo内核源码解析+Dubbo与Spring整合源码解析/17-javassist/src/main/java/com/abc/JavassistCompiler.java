package com.abc;

import javassist.*;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Modifier;

/**
 * 1）生成一个Person类的.class文件
 * 2）生成Person类的一个实例，并直接调用该实例的业务方法
 */
public class JavassistCompiler {

    public static void main(String[] args) throws Exception {
        // 获取CtClass实例的工具类实例
        // CtClass，Class Type Class
        ClassPool pool = ClassPool.getDefault();
        // 使用pool生成一个CtClass实例，并生成一个.class文件
        CtClass ctClass = genericClass(pool);
        // 创建相应实例，并调用其业务方法
        invokeInstance(ctClass);
    }

    private static CtClass genericClass(ClassPool pool)
            throws Exception {
        // 通过pool生成一个public的com.abc.Person类的字节码类
        CtClass ctClass = pool.makeClass("com.abc.Person");

        // 添加private String name属性
        CtField nameField = new CtField(pool.getCtClass("java.lang.String"), "name", ctClass);
        nameField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(nameField);

        // 添加private int age属性
        CtField ageField = new CtField(pool.getCtClass("int"), "age", ctClass);
        ageField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(ageField);

        // 添加无参构造器
        CtConstructor ctConstructor = new CtConstructor(new CtClass[] {}, ctClass);
        String body = "{\nname=\"zhangsan\";\nage=23;\n}";
        ctConstructor.setBody(body);
        ctClass.addConstructor(ctConstructor);

        // 添加getter与setter
        ctClass.addMethod(CtNewMethod.getter("getName", nameField));
        ctClass.addMethod(CtNewMethod.setter("setName", nameField));
        ctClass.addMethod(CtNewMethod.getter("getAge", ageField));
        ctClass.addMethod(CtNewMethod.setter("setAge", ageField));

        // 添加personInfo业务方法
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "personInfo", new CtClass[] {}, ctClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        StringBuffer sb = new StringBuffer();
        sb.append("{\nSystem.out.println(\"name=\"+name);\n")
          .append("System.out.println(\"age=\"+age);\n}");
        ctMethod.setBody(sb.toString());
        ctClass.addMethod(ctMethod);

        // 把生成的字节码文件写入到d盘
        byte[] bytes = ctClass.toBytecode();
        File file = new File("d:/Person.class");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.close();
        return ctClass;
    }

    private static void invokeInstance(CtClass ctClass)
            throws Exception {
        // 使用ctClass生成一个Class
        Class<?> clazz = ctClass.toClass();
        // 创建Person实例并调用其业务方法personInfo()
        Object obj = clazz.newInstance();
        obj.getClass().getMethod("personInfo", new Class[] {}).invoke(obj, new Object[] {});
    }
}
