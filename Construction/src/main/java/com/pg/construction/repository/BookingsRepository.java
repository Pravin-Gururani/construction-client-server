package com.pg.construction.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pg.construction.models.Bookings;




@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Integer> 
{

}