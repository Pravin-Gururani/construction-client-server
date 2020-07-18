package com.construction.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.construction.models.Bookings;
import com.construction.models.Commissions;
import com.construction.models.User;
import com.construction.repository.BookingsRepository;
import com.construction.repository.CommissionsRepository;
import com.construction.repository.UserRepository;
import com.construction.responses.GlobalResponseData;
import com.construction.responses.GlobalResponseListData;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/booking")
public class BookingsController {

	@Autowired
	BookingsRepository bookingRepository;

	@Autowired
	CommissionsRepository commissionRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ObjectMapper mapper;
	
	GlobalResponseData globalResponseData;
	
	GlobalResponseListData globalResponseListData;

	// 1. GET ALL BOOKING

	@GetMapping("/getallbookings")
	public ResponseEntity<List<Bookings>> getAllbookings() {
		try {
			List<Bookings> bookings = bookingRepository.findAll();

			if (bookings.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(bookings, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 2. GET BOOKINGS BY ID
	@GetMapping("/bookings/{id}")
	public ResponseEntity<GlobalResponseData> getbookingsById(@PathVariable("id") Integer id) {

		try {

			Optional<Bookings> bookings = bookingRepository.findById(id);
			
			if (bookings.isPresent()) {
				System.out.println("if");
				globalResponseData = new GlobalResponseData("true", 200, "success",bookings.get());
				return new ResponseEntity<>(globalResponseData, HttpStatus.OK);
			} else {
				System.out.println("else");
				globalResponseData = new GlobalResponseData("false", 404, "Failure:Result Not Found");
				return new ResponseEntity<>(globalResponseData, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			globalResponseData = new GlobalResponseData("false", 500, "Failure:Internal Server Error");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//
	//}

	// 3. ADD NEW BOOKINGS
	@PostMapping("/addbookings")
	public ResponseEntity<GlobalResponseData> addBooking(@RequestBody Bookings add) {
		try {
			int daysWorked = (int) ((add.getBookingTo().getTime() - add.getBookingFrom().getTime())
					/ (24 * 60 * 60 * 1000));
			String status = "Pending";

			Bookings bookings = bookingRepository.save(new Bookings(add.getBookingFrom(), add.getBookingTo(), status,
					daysWorked, add.getUser(), add.getEmployee()));

			Optional<User> user = userRepository.findById(add.getEmployee().getId());

			int totalCommissionAmount = daysWorked * user.get().getEmployeeData().getCommissionRate();
			CommissionsController commissionConntroller = new CommissionsController();
			Commissions commission = commissionRepository
					.save(new Commissions(bookings, totalCommissionAmount, totalCommissionAmount, "Pending"));
			commissionConntroller.addCommission(commission);
			//globalResponse= new GlobalResponse("true", 200, "success",bookings);
			return new ResponseEntity<>(globalResponseData,HttpStatus.CREATED);
		} catch (Exception e) {
			globalResponseData= new GlobalResponseData("false", 417, "Failure:Data Expectation Failed");
			return new ResponseEntity<>(globalResponseData, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
	@GetMapping("/bookings/employee/username")
	public ResponseEntity<GlobalResponseListData> getbookingsByEmployeeId() {
		String username="";

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			username = currentUserName;
			System.out.println("test-1- UN-Long" + currentUserName);
		}
		
		try {
			List<Bookings> bookings = bookingRepository.findBookingsByUsername(username);

			
			if (bookings.isEmpty()) {
				globalResponseListData = new GlobalResponseListData("false", 404, "Failure:Result Not Found");
				return new ResponseEntity<>(globalResponseListData,HttpStatus.NOT_FOUND);
			}
			else {
				globalResponseListData =new GlobalResponseListData("true", 200, "success",bookings);
				return new ResponseEntity<>(globalResponseListData, HttpStatus.OK);
				
			}
		} catch (Exception e) {
			globalResponseListData= new GlobalResponseListData("false", 500, "Failure:Internal Server Error");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	//4. UPDATE BOOKINGS BY ID
//	
//	@PutMapping("/updatebookings/{id}")
//	public ResponseEntity<Bookings> updateBookings(@PathVariable("id") Integer id, @RequestBody Bookings updateBookings) {
//		
//		return bookingsService.updateBookings(id, updateBookings);
//		
//	}
//	
//
//	
//	//5. DELETE BOOKINGS BY ID
//	
//	@DeleteMapping("/deletebooking/{id}")
//	private ResponseEntity<HttpStatus> deleteBooking(@PathVariable("id") int id) {
//		try {
//			bookingsService.deleteBookings(id);
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//		}
//	}

}
