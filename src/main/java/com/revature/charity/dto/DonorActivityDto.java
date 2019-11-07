package com.revature.charity.dto;

import lombok.Data;

@Data
public class DonorActivityDto {
	private Integer id;

	private Integer userId;

	private Integer requestId;

	private double amountDonated;


}
