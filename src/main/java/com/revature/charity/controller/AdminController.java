package com.revature.charity.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.charity.dto.AdminDto;
import com.revature.charity.dto.Message;
import com.revature.charity.exception.ServiceException;
import com.revature.charity.model.Admin;
import com.revature.charity.model.AdminActivity;
import com.revature.charity.model.Donor;
import com.revature.charity.model.DonorActivity;
import com.revature.charity.service.AdminActivityService;
import com.revature.charity.service.AdminService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	@Autowired
	AdminActivityService adminActivityService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	   
	
	@PostMapping("/login")
	@ApiOperation(value = "Adminlogin API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Admin.class),
	@ApiResponse(code = 400, message = "Invalid login Credentials", response = Message.class) })


		public ResponseEntity<?> login(@RequestBody AdminDto admin) {
			
			try 
			{
				Admin adminObj = adminService.adminLogin(admin);
				return new ResponseEntity<>(adminObj,HttpStatus.OK);		
			}
			catch (Exception e) 
			{
				LOGGER.error("Exception:", e);
		    Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		     }
	}

	@PostMapping("/register")
	@ApiOperation(value = "Adminregister API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Admin.class),
	@ApiResponse(code = 400, message = "Invalid Email/Phone Number Credentials", response = Message.class) })
	//TODO: Convert Request Param to Request Body
	public ResponseEntity<?> register(@RequestParam("name") String adminName, @RequestParam("email") String email,
			@RequestParam("phone") long phone) throws ServiceException {

		Admin admin = new Admin();

		admin.setName(adminName);
		admin.setEmail(email);
		admin.setPhone(phone);

		try {
			adminService.adminRegister(admin);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			LOGGER.error("Exception:", e);
			 Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/requestupdate")
	@ApiOperation(value = "Adminrequestupdate API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = AdminActivity.class),
			@ApiResponse(code = 400, message = "Unbale to Update the Request", response = Message.class) })

	public ResponseEntity<?> addrequest(@RequestParam("request") String request, @RequestParam("fund") double fund) {
		
		AdminActivity adminActivity = new AdminActivity();
		adminActivity.setRequest(request);
		adminActivity.setFund(fund);
		try {
			adminActivityService.adminreqUpdate(adminActivity);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			LOGGER.error("Exception:", e);
			 Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/donorlist")
	@ApiOperation(value = "Adminregsiterlist API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Donor.class),
	@ApiResponse(code = 400, message = "List Cannot be displayed", response = Message.class) })

	public ResponseEntity<?> donorList()  {
		
		try {
			List<Donor> list = adminActivityService.donorList();
			return new ResponseEntity<>(list,HttpStatus.OK);
			
		} catch (Exception e) {
			LOGGER.error("Exception:", e); 
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
			
			
		}

	}

	@GetMapping("/donationlist")
	@ApiOperation(value = "Admindonationlist API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = DonorActivity.class),
			@ApiResponse(code = 400, message = "List Cannot be displayed", response = Message.class) })

	public ResponseEntity<?> donationList()  {

		try {
			List<DonorActivity> list =  adminActivityService.donationList();
			return new ResponseEntity<>(list,HttpStatus.OK);
			
		} 
		catch (Exception e)
		{
			LOGGER.error("Exception:", e);
			Message	message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}

	}

	
	@GetMapping("/contribute")
	@ApiOperation(value = "Admincontributereq API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = AdminActivity.class),
			@ApiResponse(code = 400, message = "Invalid Contribute Details", response = Message.class) })

	public ResponseEntity<?> contributereq(@RequestParam("fundamt") double fund, @RequestParam("id") int id) {

		
		AdminActivity adminActivity = null;
		adminActivity = new AdminActivity();
		adminActivity.setFund(fund);
		adminActivity.setId(id);
		try {
			adminActivityService.adminFundUpdate(adminActivity);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Exception:", e);
			Message	message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}

	}

}
