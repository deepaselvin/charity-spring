package com.revature.charity.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import com.revature.charity.dao.UserDAO;
import com.revature.charity.dao.UserImpl;
import com.revature.charity.exception.DBException;
import com.revature.charity.exception.ValidatorException;
import com.revature.charity.model.Donor;
import com.revature.charity.model.User;
import com.revature.charity.service.DonorService;
import com.revature.charity.util.Logger;
import com.revature.charity.util.MessageConstant;

@Validated
public class DonorValidator {
	
	@Autowired
	DonorService donorService;
	
	private  DonorValidator()
	{}
	
	static  DonorValidator donorValidator = null;
	
	/**
	 * Get instance of donorValidator class 
	**/
	public static  DonorValidator getInstance()
	{
		if(donorValidator == null)
		{
			donorValidator = new  DonorValidator();
		}
		return donorValidator;
	}
	
	/** 
	 * Login validator 
	 **/

	public void loginValidator(Donor donor) throws ValidatorException
	{
	String name=donor.getName();
		String email = donor.getEmail();
		
		if(StringUtils.containsWhitespace(email))
		{
			throw new ValidatorException(MessageConstant.INVALID_EMAIL);
		}
		if(StringUtils.hasText(name))
		{
			throw new ValidatorException(MessageConstant.INVALID_NAME);
		}
		
	}
	/** 
	 * Register validator
	 *  **/
	public void registerValidator(Donor donor) throws ValidatorException
	{

		String email = donor.getEmail();
		String name=donor.getName();
		long phone=donor.getPhone();
		
		UserDAO userDao = new UserImpl();
		User userobject = new User();
		try {
			userobject = userDao.isEmailExist(email);
			if(userobject != null)
			{
				throw new ValidatorException(MessageConstant.EMAIL_EXIST);
			}
		} catch (DBException e) {
			Logger.error(e);
		}
		
		if(StringUtils.containsWhitespace(email))
		{
			throw new ValidatorException(MessageConstant.INVALID_EMAIL);
		}
		if(StringUtils.hasText(name))
		{
			throw new ValidatorException(MessageConstant.INVALID_NAME);
		}
		
		
	}


}
