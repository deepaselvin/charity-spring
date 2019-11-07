package com.revature.charity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.charity.service.EmployeeServiceImpl;
import com.revature.charity.service.UserServiceImpl;

@Configuration
public class BeanConfig {
	@Bean
	public UserServiceImpl userviceImpl()
	{
		return new UserServiceImpl();
	}
	
	@Bean
	public EmployeeServiceImpl  employeeServiceImpl()
	{
		return new EmployeeServiceImpl();
		
	}
}
