package proxy.factory;

import net.sf.cglib.proxy.Enhancer;
import proxy.advice.MyMethodInterceptor;

/**
 * 主要作用就是生成代理类 使用CGLib动态代理技术实现 它是基于继承的方式实现的
 * cglib底层是通过asm包去实现的字节码的编写
 * @author think
 *
 */
public class CgLibProxyFactory {

	/**
	 * @param clazz
	 * @return
	 */
	public Object getProxyByCgLib(Class<?> clazz) {
		// 创建增强器
		Enhancer enhancer = new Enhancer();
		// 设置需要增强的类的类对象
		enhancer.setSuperclass(clazz);
		// 设置回调函数
		enhancer.setCallback(new MyMethodInterceptor());
		// 获取增强之后的代理对象
		Object object = enhancer.create();

		// generatorClass(enhancer);

		return object;
	}

	/*
	 * private void generatorClass(Enhancer enhancer) { FileOutputStream out = null;
	 * try { byte[] bs = DefaultGeneratorStrategy.INSTANCE.generate(enhancer);
	 * FileOutputStream fileOutputStream = new FileOutputStream("Enhancer_cglib" +
	 * ".class"); fileOutputStream.write(bs); fileOutputStream.flush();
	 * fileOutputStream.close(); } catch (Exception e) { e.printStackTrace(); }
	 * finally { if (out != null) { try { out.close(); } catch (IOException e) { //
	 * TODO Auto-generated catch block } } }
	 * 
	 * }
	 */
}
