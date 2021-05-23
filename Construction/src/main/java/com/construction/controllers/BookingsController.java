package com.construction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.construction.models.Bookings;
import com.construction.models.Rating;
import com.construction.repository.CommissionsRepository;
import com.construction.repository.UserRepository;
import com.construction.responses.GlobalResponseData;
import com.construction.responses.GlobalResponseListData;
import com.construction.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/booking")
public class BookingsController {

	@Autowired
	BookingService bookingService;

	@Autowired
	CommissionsRepository commissionRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ObjectMapper mapper;

	GlobalResponseData globalResponseData;

	GlobalResponseListData globalResponseListData;

	// 1. GET ALL BOOKING

	
	@GetMapping("/getall")
	public ResponseEntity<GlobalResponseListData> getAllBookings() {
		return bookingService.getAllBookings();
	}
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<GlobalResponseData> getBookingById(@PathVariable("id") Integer id) {
		return bookingService.getBookingById(id);

	}
	@PostMapping("/add")
	public ResponseEntity<GlobalResponseData> addBooking(@RequestBody Bookings add) 
	{
		return bookingService.addBooking(add);
	}

	@PutMapping("/updatebyid/{id}")
	public ResponseEntity<GlobalResponseData> updateBooking(@PathVariable("id") Integer id, @RequestBody Bookings newBooking) {
		
			return bookingService.updateBooking(id, newBooking);
	}	
	
	@GetMapping("/getbyusername")
	public ResponseEntity<GlobalResponseListData> getBookingsByUsername() {
		return bookingService.getBookingsByUsername();
	}

	@PutMapping("/updateratingbyid/{id}")
	public ResponseEntity<GlobalResponseData> updateRating(@PathVariable("id") Integer id, @RequestBody Bookings bookingRating)
	{
		return bookingService.updateRating(id,bookingRating);
	}
	
}
