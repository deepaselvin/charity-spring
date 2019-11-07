package com.revature.charity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_activity")
public class DonorActivity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = " user_id ")
	private Integer userId;

	@Column(name = " request_id")
	private Integer requestId;

	@Column(name = " amount_donated")
	private double amountDonated;


}
