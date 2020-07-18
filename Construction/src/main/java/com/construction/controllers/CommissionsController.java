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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.construction.models.Bookings;
import com.construction.models.Commissions;
import com.construction.repository.CommissionsRepository;
import com.construction.responses.GlobalResponseData;
import com.construction.responses.GlobalResponseListData;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/commissions")
public class CommissionsController {

	@Autowired
	CommissionsRepository commissionRepository;
	
	GlobalResponseData globalResponseData;
	
	GlobalResponseListData globalResponseListData;

	
	//1. GET ALL COMMISSION
	
	@GetMapping("/getallcommissions")
	public ResponseEntity<List<Commissions>> getAllcommissions() {
		try {
			List<Commissions> commissions = commissionRepository.findAll();
			
			if (commissions.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(commissions, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//2. GET COMMISSIONS BY ID
//	@GetMapping("/commissions/{id}")
//	public ResponseEntity<Commissions> getcommissionsById(@PathVariable("id") Integer id) {
//		Optional<Commissions> commissions = commissionsService.getCommissionsById(id);
//
//		if (commissions.isPresent()) {
//			return new ResponseEntity<>(commissions.get(), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//	
//	
	//3. ADD NEW COMMISSIONS
	@PostMapping("/addcommissions")
	public ResponseEntity<Commissions> addCommission(@RequestBody Commissions add) {
		try {
			Commissions newCommissions = commissionRepository.save(new Commissions(
					add.getBookingId(),
					add.getTotalCommissionAmount(),
					add.getDueCommissionAmount(),
					add.getCommissionStatus()
					)); 
			return new ResponseEntity<>(newCommissions, HttpStatus.CREATED);
		} catch (Exception e) {
			globalResponseData= new GlobalResponseData("false", 417, "Failure:Data Expectation Failed");
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
			
	}
	
	
	
	
	@GetMapping("/commissions/employee/username")
	public ResponseEntity<GlobalResponseListData> getbookingsByEmployeeId() {
		
		String username = "84102308531";
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    username= currentUserName;

		}
		try {
			List<Commissions> commissions = commissionRepository.findUsername(username);
			
			if (commissions.isEmpty()) {
				globalResponseListData = new GlobalResponseListData("false", 404, "Failure:Result Not Found");
				return new ResponseEntity<>(globalResponseListData,HttpStatus.NOT_FOUND);
			}

			globalResponseListData =new GlobalResponseListData("true", 200, "success",commissions);
			return new ResponseEntity<>(globalResponseListData, HttpStatus.OK);
			
		} 
		catch (Exception e) {
			globalResponseListData= new GlobalResponseListData("false", 500, "Failure:Internal Server Error");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//	
//
//	//4. UPDATE COMMISSIONS BY ID
//	
//	@PutMapping("/updatecommissions/{id}")
//	public ResponseEntity<Commissions> updateCommissions(@PathVariable("id") Integer id, @RequestBody Commissions newUpdateCommissions) {
//		
//		return commissionsService.updateCommissions(id, newUpdateCommissions);
//		
//	}
//	
//
//	
//	//5. DELETE COMMISSIONS BY ID
//	
//	@DeleteMapping("/deletecommissions/{id}")
//	private ResponseEntity<HttpStatus> deleteCommissions(@PathVariable("id") int id) {
//		try {
//			commissionsService.deleteCommissions(id);
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//		}
//	}
}
