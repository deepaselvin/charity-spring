package com.revature.charity.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.revature.charity.model.Donor;
import com.revature.charity.repository.DonorRepository;
@RunWith(SpringRunner.class)
@SpringBootTest

public class DonorRepoTest {

	@Before
	public void setUp() throws Exception {
	}
	@Autowired
    private DonorRepository donorRepository;
  
	@Test
	public void donorLoginValidRepoTest() {
		
		 String name = "deepa";
		String email = "deepa@gmaii.com";
	       
	       
	        Donor donor = donorRepository.login(name,email);
	        System.out.println(donor);
	        assertNotNull(donorRepository);
	   
	}
	@Test
	public void donorLoginInvalidRepoTest() {
		
		 String name = "dep";
		String email = "dee@gmaii.com";
	       
	       
	        Donor donor = donorRepository.login(name,email);
	        System.out.println(donor);
	        assertNotNull(donorRepository);
	   
	}

}
