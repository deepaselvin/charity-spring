package com.revature.charity.service;


import static org.mockito.ArgumentMatchers.any;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.charity.exception.ServiceException;
import com.revature.charity.model.Donor;
import com.revature.charity.repository.DonorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest// package name is same it takes above configuration

public class DonorRegServiceTest {

	@InjectMocks
	private DonorService donorService;

	@Mock
	private DonorRepository donorRepoMock;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
			
	@Test
	
	public void testValidRegister() throws ServiceException {
		Donor donor = new Donor();
		donor.setName("deepaselvin");
		donor.setEmail("deepaselvin@gmaii.com");
		donor.setPhone(8760051846l);
	
		Mockito.when(donorRepoMock.save((Donor) any(Donor.class))).thenReturn(donor);
		//Mockito.when(donorRepoMock.save(anyString(), anyString())).thenReturn(null);
		
		donorService.donorRegister(donor);

}
//	@Test(expected = ServiceException.class)
//	public void testInvalidRegister() throws ServiceException {
//		Donor donorObject = new Donor();
//		donorObject.setName("565327");
//		donorObject.setEmail("872819");
//		donorObject.setPhone(0);
//		Mockito.when(donorRepoMock.save((Donor) any(Donor.class))).thenThrow(Exception.class);
////		Mockito.when(donorRepoMock.save((Donor) any(Donor.class))).thenReturn(null);
//
//		donorService.donorRegister(donorObject);
//		//Mockito.when(donorRepoMock.login(donor.getName(), donor.getEmail())).thenReturn(donor);
//		//Mockito.when(donorRepoMock.save(anyString(), anyString())).thenReturn(null);
//	}


}