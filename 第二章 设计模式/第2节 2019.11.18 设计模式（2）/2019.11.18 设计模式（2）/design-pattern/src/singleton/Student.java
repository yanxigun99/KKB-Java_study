package singleton;

/**
 * 
 * @author 怡吾宇
 *
 */
public class Student {

	private Student() {}

	/*
	 * 此处使用一个内部类来维护单例 JVM在类加载的时候，是互斥的，所以可以由此保证线程安全问题
	 */
	private static class SingletonFactory {
		private static Student student = new Student();
	}

	/* 获取实例 */
	public static Student getSingletonInstance() {
		return SingletonFactory.student;
	}

}
class Test{
	
	public static void main(String[] args) {
		Student student = Student.getSingletonInstance();
		System.out.println(student);
	}
}