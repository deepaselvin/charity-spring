package com.revature.charity.service;


import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.charity.controller.AdminController;
import com.revature.charity.dto.AdminDto;
import com.revature.charity.exception.ServiceException;
import com.revature.charity.model.Admin;
import com.revature.charity.repository.AdminRepository;
import com.revature.charity.util.MessageConstant;

@Service
public class AdminService {
 

	@Autowired
	AdminRepository adminRepo;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	
	@Transactional
	public Admin adminLogin( final AdminDto  admin) throws ServiceException {	
		
		Admin donorObj=null;		
			try {
			Admin adminObj=new Admin();
			adminObj.setEmail(admin.getEmail());
			
			donorObj = adminRepo.login(admin.getEmail());
		} 
			catch (Exception e)
			{
			 LOGGER.error("Exception:", e);
			throw new ServiceException(MessageConstant.UNABLE_TO_LOGIN);
			
		}
		return donorObj;
	
}
	
	@Transactional
	public void adminRegister(Admin admin) throws ServiceException {
		
		
        
		try {
			//TODO:need to add Validation
			 //validation
	        rejectIfEmailExists(admin.getEmail());
			adminRepo.save(admin);
		} catch (Exception e) {
			 LOGGER.error("Exception:", e);	
			 throw new ServiceException(MessageConstant.UNABLE_TO_REGISTER);
	}
	}
	public void rejectIfEmailExists(String email) throws ServiceException {
        Admin admin = adminRepo.findByEmail(email);
        if ( admin != null) {
            throw new ServiceException(MessageConstant.EMAIL_EXIST);
        }
}
}