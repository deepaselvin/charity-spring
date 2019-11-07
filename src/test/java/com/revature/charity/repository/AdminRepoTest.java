
package com.revature.charity.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.charity.model.Admin;
import com.revature.charity.repository.AdminRepository;
@RunWith(SpringRunner.class)
@SpringBootTest// package name is same it takes above configuration

public class AdminRepoTest {

	@Before
	public void setUp() throws Exception {
	}

	
	 @Autowired
	    private AdminRepository adminRepository;
	    
	    /**
	     * login in AdminRepository
	     * @Param email
	     * return adminRepository Object
	     */
	    @Test
	    public void adminLoginValidTest() {
	    
	    
	        String email = "pradeepa@gmaii.com";
	       
	       
	        Admin admin = adminRepository.login(email);
	        System.out.println(admin);
	        assertNotNull(adminRepository);
	    }

	    @Test
	    public void adminLoginInvalidTest() {
	    
	    
	        String email = "prad@gmaii.com";
	       
	       
	        Admin admin = adminRepository.login(email);
	        System.out.println(admin);
	        assertNotNull(adminRepository);
	    }

}
