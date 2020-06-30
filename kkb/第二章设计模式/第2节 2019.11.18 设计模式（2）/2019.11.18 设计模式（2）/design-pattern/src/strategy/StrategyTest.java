package strategy;

import org.junit.Test;

/**
 * 测试类
 * 
 * @author think
 *
 */
public class StrategyTest {

	@Test
	public void test() {
		// 环境类
		PersonContext person = new PersonContext();

		// 太远了，需要做飞机
		person.travel(10000);

		// 不太远，飞机太贵，选择火车
		person.travel(100);

		// 很近，直接选择自行车
		person.travel(1);
	}
}
