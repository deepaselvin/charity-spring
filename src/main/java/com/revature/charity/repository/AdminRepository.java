package com.revature.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.charity.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	@Query(" from Admin where  email = :email ")
	Admin login( @Param("email") String email);

	@Query(" from Admin where email =  ?1")
    Admin findByEmail(String email);

}
