
package com.revature.charity.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.charity.exception.ServiceException;
import com.revature.charity.model.AdminActivity;
import com.revature.charity.model.Donor;
import com.revature.charity.model.DonorActivity;
import com.revature.charity.repository.AdminActivityRepository;
import com.revature.charity.repository.DonorActivityRepository;
import com.revature.charity.repository.DonorRepository;
import com.revature.charity.util.MessageConstant;

@Service
public class AdminActivityService {
	
	@Autowired
	AdminActivityRepository adminActivityRepository;

	@Autowired
    DonorRepository donorRepo;
	
	@Autowired
	DonorActivityRepository donorActivityRepository;

	
	@Transactional
	public void adminreqUpdate(AdminActivity adminActivity) throws ServiceException {
		try {
			//TODO:need to add Validation
			//validate Request
			rejectIfRequestExists(adminActivity.getRequest());
			adminActivityRepository.save(adminActivity);
		}
		 catch (Exception e) {
			throw new ServiceException(MessageConstant.UNABLE_TO_ADD_REQUEST);

			//throw new ServiceException(e.getMessage());	}
	}
	}
	
	@Transactional
	public List<Donor> donorList() throws ServiceException {
		
		try {
			List<Donor> donorObj = donorRepo.findAll();
			return donorObj;
		} catch (Exception e) {
			throw new ServiceException(MessageConstant.UNABLE_TO_LIST);
			}
	}
	

	@Transactional
	public List<DonorActivity> donationList() throws ServiceException {
		
		try {
			List<DonorActivity> donorAct = donorActivityRepository.findAll();
			return donorAct;
		} catch (Exception e) {
			throw new ServiceException(MessageConstant.UNABLE_TO_LIST);
		}
	
	}


	@Transactional
	public void adminFundUpdate(AdminActivity adminActivity) throws ServiceException {

		try {
			adminActivityRepository.updateAdd(adminActivity.getFund(),adminActivity.getId());
		} catch (Exception e) {
			 //errorMessage = e.getMessage();
				throw new ServiceException(MessageConstant.UNABLE_TO_ADD_REQUEST);		
			 }
		}

	   public void rejectIfRequestExists(String request) throws ServiceException {
		   AdminActivity adminActivity = adminActivityRepository.findByRequest(request);
	        if ( adminActivity != null) {
	            throw new ServiceException(MessageConstant.UNABLE_TO_ADD_REQUEST);
	        }	
	}
}

