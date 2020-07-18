package com.construction.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.construction.models.Bookings;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Integer> 
{	
	@Query(value="select * from bookings where employee=(select id from users where username=:username)",nativeQuery = true)
	List<Bookings> findBookingsByUsername(@Param("username")String username);
}