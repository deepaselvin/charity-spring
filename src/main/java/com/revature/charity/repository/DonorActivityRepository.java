package com.revature.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.charity.model.DonorActivity;
@Repository
public interface DonorActivityRepository  extends JpaRepository<DonorActivity, Integer> {

		
		
}