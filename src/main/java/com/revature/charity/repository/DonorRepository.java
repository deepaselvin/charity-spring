package com.revature.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.charity.model.Donor;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Integer> {

	@Query(" from Donor where name= :name and email = :email ")
	Donor login(@Param("name") String name, @Param("email") String email);
	
	@Query(" from Donor where email =  ?1")
    Donor findByEmail(String email);

	

}
