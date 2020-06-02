package com.kkb.test;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kkb.mapper.RoleMapper;
import com.kkb.mapper.UserMapper;
import com.kkb.pojo.Role;
import com.kkb.pojo.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Test
    public void testUserRole() {
		  
		User user = userMapper.findUserByName("cuihua");
        Set<Role> roles = userMapper.getUserRoles("cuihua");
        
        for (Role role : roles) {
            role.setPermissions(roleMapper.getRolePermissions(role.getRoleCode()));
        }
        
        user.setRoles(roles);
        
        System.out.println(user);
    }

	@Test
	public void testSelect(){
		
		// 此处 null 指的是不用根据参数去查询
		// 可以调用 CRUD 相关的多种方式

		// 1. 查询所有的数据
		List<User> userList = userMapper.selectList(null);
		userList.forEach(user -> System.out.println(user.getName()));
		
		// 2. 根据 id 删除
		userMapper.deleteById(1);
		
		// 3. 添加数据
		User user = new User();
		user.setName("老王");
		user.setEmail("laowang@kkb.com");
		user.setAge(18);
		userMapper.insert(user);
		
		// 4. 更新数据（此时插入的是）
		user.setName("老王王");
		user.setEmail("laowangwang@kkb.com");
		userMapper.updateById(user);
	}
}
