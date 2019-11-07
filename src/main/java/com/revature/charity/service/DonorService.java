package com.revature.charity.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.charity.controller.DonorController;
import com.revature.charity.dto.DonorDto;
import com.revature.charity.exception.ServiceException;
import com.revature.charity.model.Donor;
import com.revature.charity.repository.DonorRepository;
import com.revature.charity.util.MessageConstant;

@Service
public class DonorService {

	@Autowired
    DonorRepository donorRepo;
	private static final Logger LOGGER = LoggerFactory.getLogger(DonorController.class);
	@SuppressWarnings("unused")
	
	@Transactional
	public Donor donorLogin( final DonorDto donorObject) throws ServiceException {
	
		Donor donorObj = null;
		try {
			donorObj = donorRepo.login(donorObject.getName(),donorObject.getEmail());
		if(donorObj ==null) {
			 throw new ServiceException(MessageConstant.UNABLE_TO_LOGIN);			
		}	
		} 
		catch (Exception e) 
		{
				 LOGGER.error("Exception:", e);
				 throw new ServiceException(MessageConstant.UNABLE_TO_LOGIN);
				
				 
		
		}
		return donorObj;
	}

	@Transactional
	public void donorRegister(Donor donor ) throws ServiceException {
	     
        //validation
        rejectIfEmailExists(donor.getEmail());
        
        
	try {
			//TODO:need to add Validation
			donorRepo.save(donor);
		}
	catch (Exception e)
	{
			 LOGGER.error("Exception:", e);		 
			throw new ServiceException(MessageConstant.UNABLE_TO_REGISTER);
			
		}
	}
	@Transactional
	public List<Donor> donorList() throws ServiceException {
		List<Donor> donorObj = null;
		try {		
			donorObj = donorRepo.findAll();
		} 
		catch (Exception e) 
		{
			LOGGER.error("Exception:", e);
			throw new ServiceException(MessageConstant.UNABLE_TO_LIST);
		}
		return donorObj;
	}
	
	
	   public void rejectIfEmailExists(String email) throws ServiceException {
	        Donor donor = donorRepo.findByEmail(email);
	        if ( donor != null) {
	            throw new ServiceException(MessageConstant.EMAIL_EXIST);
	        }	

}
}
