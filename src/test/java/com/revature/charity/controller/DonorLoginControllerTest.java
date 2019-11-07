package com.revature.charity.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.charity.dto.DonorDto;
import com.revature.charity.model.Donor;
import com.revature.charity.service.DonorService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class DonorLoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DonorService donorServiceMock;

	@InjectMocks
	DonorController donorController;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testLogin() throws Exception {
		Donor donor = new Donor();
		donor.setEmail("deepa@gmaii.com");
		donor.setName("deepa");
		when(donorServiceMock.donorLogin(any(DonorDto.class))).thenReturn(donor);


		String userJson = new ObjectMapper().writeValueAsString(donor);
		mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(userJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$.email").value("deepa@gmaii.com"));

	}

	/*
	@Test
	public void testInvalidLogin() throws Exception {
		Donor donor = new Donor();
		donor.setEmail("deepa@gmaii.com");
		donor.setName("deepa");
		
		when(donorServiceMock.login(any(Donor.class))).thenThrow(new InvalidLoginException("Invalid login credentials"));

		String userJson = new ObjectMapper().writeValueAsString(donor);

		mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(userJson))
				.andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.errorMessage").value("Invalid login credentials"));

	}*/

}
