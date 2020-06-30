package proxy.target;

import java.lang.reflect.InvocationHandler;

public class UserServiceImpl2 implements UserService {

	// private UserService service = new UserServiceImpl();
	private InvocationHandler h;

	@Override
	public void saveUser() {
		// service.saveUser();
		// h.invoke(proxy, method, args);
	}

}
