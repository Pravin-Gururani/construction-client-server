package com.construction.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.construction.models.Bookings;
import com.construction.models.Commissions;



@Repository
public interface CommissionsRepository extends JpaRepository<Commissions, Integer> 
{
	@Query(value="select * from commissions where booking_id in (select booking_id from bookings where employee=(select id from users where username=:username))",nativeQuery = true)
	List<Commissions> findUsername(@Param("username")String username);
}