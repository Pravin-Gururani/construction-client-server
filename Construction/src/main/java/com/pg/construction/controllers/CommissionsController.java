package com.pg.construction.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pg.construction.models.Commissions;
import com.pg.construction.repository.CommissionsRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/commissions")
public class CommissionsController {

	@Autowired
	CommissionsRepository commissionRepository;

	
	//1. GET ALL COMMISSION
	
	@GetMapping("/getallcommissions")
	public ResponseEntity<List<Commissions>> getAllcommissions() {
		try {
			List<Commissions> commissions = commissionRepository.findAll();
//				System.out.println(commissions.isEmpty());
			
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
	public ResponseEntity<Commissions> createTutorial(@RequestBody Commissions add) {
		try {
			Commissions newCommissions = commissionRepository.save(new Commissions(
					add.getBookingId(),
					add.getTotalCommissionAmount(),
					add.getDueCommissionAmount(),
					add.getCommissionStatus()
					));
					
			return new ResponseEntity<>(newCommissions, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
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
