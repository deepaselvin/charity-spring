package com.revature.charity.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.revature.charity.model.AdminActivity;

@Repository
public interface AdminActivityRepository extends JpaRepository<AdminActivity, Integer>{

	
	@Modifying
	   @Transactional
	   @Query("UPDATE AdminActivity d SET"
	           + " d.fund = d.fund- :fund WHERE  d.id = :id")
	   public  void  update(
		    @Param("fund") double fund, @Param("id") Integer id);
	@Query(" from AdminActivity where request_type =  ?1")
	AdminActivity findByRequest(String email);


	@Modifying
	   @Transactional
	   @Query("UPDATE AdminActivity d SET"
	           + " d.fund = d.fund+ :fund WHERE  d.id = :id")
	   public  void  updateAdd(
		    @Param("fund") double fund, @Param("id") Integer id);


	
	//update category set fund_needed= fund_needed - ? where id=?

//String sql1 = "select name,request_id,amount_donated from users u,user_activity a where u.id=a.user_id";
	//userid localStorage how to set it here
}
