package com.revature.charity.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.anyString;
import com.revature.charity.dto.DonorDto;
import com.revature.charity.exception.ServiceException;
import com.revature.charity.model.Donor;
import com.revature.charity.repository.DonorRepository;
@RunWith(SpringRunner.class)
@SpringBootTest// package name is same it takes above configuration

public class DonorLoginServiceTest {
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
		donor.setName("deepa");
		donor.setEmail("deepa@gmaii.com");
		//Mockito.when(donorRepoMock.login(donor.getName(), donor.getEmail())).thenReturn(donor);
		Mockito.when(donorRepoMock.login(anyString(), anyString())).thenReturn(donor);


		
		DonorDto donorObj = new DonorDto();
		donorObj.setName("deepa");
		donorObj.setEmail("deepa@gmaii.com");
		Donor donorOb = donorService.donorLogin(donorObj);
		assertNotNull(donorOb);

	}
	
	@Test(expected = ServiceException.class)
	public void testInvalidLogin() throws ServiceException {
		Donor donor = new Donor();
		donor.setName("deepas");
		donor.setEmail("deepas@gmaii.com");
		//Mockito.when(donorRepoMock.login(donor.getName(), donor.getEmail())).thenReturn(donor);
		Mockito.when(donorRepoMock.login(anyString(), anyString())).thenReturn(null);


		
		DonorDto donorObj = new DonorDto();
		donorObj.setName("deepa");
		donorObj.setEmail("deepa@gmaii.com");
		Donor donorOb = donorService.donorLogin(donorObj);
		assertNotNull(donorOb);

	}
}
