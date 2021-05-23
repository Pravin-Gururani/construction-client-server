package com.construction.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.construction.models.Bookings;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Integer> 
{	
	@Query(value="select * from bookings where employee=(select id from users where username=:username) or user=(select id from users where username=:username)",nativeQuery = true)
	List<Bookings> findEmployeeBookingsByUsername(@Param("username")String username);
	
	@Query(value="select * from bookings where user=(select id from users where username=:username) or user=(select id from users where username=:username)",nativeQuery = true)
	List<Bookings> findUserBookingsByUsername(@Param("username")String username);
	
	@Query(value="select rating from rating where employee=:employeeId and rating is not null",nativeQuery = true)
//	ArrayList<HashMap<Integer, Integer>> findEmployeeRating(@Param("employeeId") Integer employeeId);
	List<Integer> findEmployeeRating(@Param("employeeId") Long employeeId);
}