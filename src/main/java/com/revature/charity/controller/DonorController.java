package com.revature.charity.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.charity.dto.DonorDto;
import com.revature.charity.dto.Message;
import com.revature.charity.exception.ServiceException;
import com.revature.charity.model.AdminActivity;
import com.revature.charity.model.Donor;
import com.revature.charity.model.DonorActivity;
import com.revature.charity.service.DonorActivityService;
import com.revature.charity.service.DonorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/donor")
public class DonorController {
	
	@Autowired
	DonorService donorService;
	
	@Autowired
	DonorActivityService donorActivityService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DonorController.class);
	   
	/*
	 * Admin Registeration
	 * input:name,email,phone*/

	@PostMapping("/register")
	@ApiOperation(value = "Donorregister API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Donor.class),
			@ApiResponse(code = 400, message = "Invalid Email/Phone Number Credentials", response = Message.class) })
	//TODO: Convert Request Param to Request Body
	public ResponseEntity<?> register(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("phone") long phone)  {

		
		Donor donor = null;
		donor = new Donor();
		donor.setName(name);
		donor.setEmail(email);
		donor.setPhone(phone);
		
		try {
			donorService.donorRegister(donor);
			return new ResponseEntity<>(donor, HttpStatus.OK);
			
		} catch (ServiceException e) {
			LOGGER.error("Exception:", e);
			Message	message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	/*
	 * Admin Login
	 * input:email*/
	@PostMapping("/login")
	@ApiOperation(value = "Donorlogin API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = DonorDto.class),
			@ApiResponse(code = 400, message = "Invalid name/email", response = Message.class) })
	//TODO: Convert Request Param to Request Body
	public ResponseEntity<?> login(@RequestParam("name") String name, @RequestParam("email") String email) {

		Donor donorObj = null;
		DonorDto donor = new DonorDto();
		donor.setName(name);
		donor.setEmail(email);

		try {
			donorObj = donorService.donorLogin(donor);
			System.out.println("list"+donorObj);
			return new ResponseEntity<>(donorObj, HttpStatus.OK);
		} catch (ServiceException e) {
			LOGGER.error("Exception:", e);
			Message	message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}

		
	}

	/*
	 * Donor Contribution
	 * input:userid,requestid,amount*/
	@PostMapping("/contribute")
	@ApiOperation(value = "Donorcontribute API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = DonorActivity.class),
			@ApiResponse(code = 400, message = "Cannot able to Contribute ", response = Message.class) })
	//TODO: Convert Request Param to Request Body
	public ResponseEntity<?> contribute(@RequestParam("userId") int userid, @RequestParam("requestId") int requestId,
			@RequestParam("amountDonated") double amountdonated)  {

	
		DonorActivity donorAct=null;
		DonorActivity donorActivity = new DonorActivity();

		donorActivity.setUserId(userid);
		donorActivity.setRequestId(requestId);
		donorActivity.setAmountDonated(amountdonated);

		try {
			donorActivityService.donorContribute(donorActivity);
			return new ResponseEntity<>(donorAct, HttpStatus.OK);
		}
		catch (ServiceException e)
		{
			LOGGER.error("Exception:", e);
			Message	message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
	/*
	 * Donor -->Request List*/

	@GetMapping("/requestList")
	@ApiOperation(value = "Donorcontribute API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = AdminActivity.class),
	@ApiResponse(code = 400, message = "List Cannot be displayed", response = Message.class) })

	public ResponseEntity<?> requestList()  {


		try {

			List<AdminActivity> list= donorActivityService.requestList();
			return new ResponseEntity<>(list, HttpStatus.OK);
			
		} 
		catch (ServiceException e) 
		{
			LOGGER.error("Exception:", e);
			Message	message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
			}

	}
	/*
	 * Donor -->Donor List*/

	@GetMapping("/donorList")
	@ApiOperation(value = "Donorcontribute API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Donor.class),
			@ApiResponse(code = 400, message = "List Cannot be displayed", response = Message.class) })

	public ResponseEntity<?> donorList() {

		
		try {
			List<Donor> list = donorService.donorList();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} 
		catch (ServiceException e)
		{
			LOGGER.error("Exception:", e);
			Message	message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
			}			


	}


}
