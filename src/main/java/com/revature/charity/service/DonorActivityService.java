package com.revature.charity.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.charity.exception.ServiceException;
import com.revature.charity.model.AdminActivity;
import com.revature.charity.model.DonorActivity;
import com.revature.charity.repository.AdminActivityRepository;
import com.revature.charity.repository.DonorActivityRepository;
import com.revature.charity.util.MessageConstant;

@Service
public class DonorActivityService {
	@Autowired
	private DonorActivityRepository donorActivityRepository;

	@Autowired
	private  AdminActivityRepository adminActivityRepository;

	@Transactional
	public void donorContribute(DonorActivity donorActivity) throws ServiceException {
		
		 
		try {
			//TODO:need to add Validation
			donorActivityRepository.save(donorActivity);
			adminActivityRepository.update(donorActivity.getAmountDonated(), donorActivity.getRequestId());
			if (donorActivityRepository == null) {
				throw new ServiceException(MessageConstant.UNABLE_TO_TRANSACTION);
			}
		} catch (Exception e) {
			throw new ServiceException(MessageConstant.UNABLE_TO_TRANSACTION);
			
			 }
	}

	@Transactional
	public List<DonorActivity> donatedList() throws ServiceException {
		List<DonorActivity> donorObj = null;
		donorObj = donorActivityRepository.findAll();

		if (donorObj == null) {
			throw new ServiceException(MessageConstant.UNABLE_TO_LIST);
		}
		return donorObj;
	}
	@Transactional
	public List<AdminActivity> requestList() throws ServiceException {
		List<AdminActivity> donorObj = null;
		donorObj = adminActivityRepository.findAll();

		if (donorObj == null) {
			throw new ServiceException(MessageConstant.UNABLE_TO_LIST);
		}
		return donorObj;
	}


	

}
