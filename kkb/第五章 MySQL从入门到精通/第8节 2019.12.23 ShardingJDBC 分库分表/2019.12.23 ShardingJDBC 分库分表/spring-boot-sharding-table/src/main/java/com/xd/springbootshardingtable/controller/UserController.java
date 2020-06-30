package com.xd.springbootshardingtable.controller;

import com.xd.springbootshardingtable.entity.Address;
import com.xd.springbootshardingtable.entity.Code;
import com.xd.springbootshardingtable.entity.Store;
import com.xd.springbootshardingtable.entity.User;
import com.xd.springbootshardingtable.service.AddressService;
import com.xd.springbootshardingtable.service.CodeService;
import com.xd.springbootshardingtable.service.StoreService;
import com.xd.springbootshardingtable.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.List;

/**
 * @Classname UserController
 * @Description 用户测试控制类
 * @Date 2019-05-26 17:36
 * @Version 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CodeService cs;
    @Autowired
    private StoreService ss;
    @Autowired
    private AddressService as;
   

    @GetMapping("/select-user")
    public List<User> select() {
        return userService.getUserList();
    }
    
    @GetMapping("/insert-store")
    public boolean insertStore(Store store) {
    	store.setId(10);
    	store.setName("aa");
        return ss.save(store);
    }
    
    public int getRandom() {
    	return  (int)(Math.random()*100);
    }
    

    @GetMapping("/insert-user")
    public Boolean insert(User user) {
    	
    	for(int i=1;i<=10;i++) {
    		user.setUid(i);
        	user.setName("aaa"+i);
        	user.setAge(getRandom());
        	
            userService.save(user);
            
            Address address=new Address();
            address.setAid(i);
            //用户id
            address.setUid(i);
            address.setName("ddd"+i);
            
            as.save(address);
            
    	}
    	return true;
    	
    }
    
    
    
    @GetMapping("/insert-code")
    public boolean insertCode(Code code) {
    	for (int i=1; i<=10;i++) {
    		code.setId(null);
        	code.setName(String.valueOf(i));
        	 cs.save(code);
    	}
    	return true;
    	
    }
    
    

}
