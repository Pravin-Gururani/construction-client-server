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

import com.construction.models.Commissions;
import com.construction.responses.GlobalResponseData;
import com.construction.responses.GlobalResponseListData;
import com.construction.service.CommissionService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/commission")
public class CommissionsController {

	@Autowired
	CommissionService commissionService;
	
	GlobalResponseData globalResponseData;
	
	GlobalResponseListData globalResponseListData;
	
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<GlobalResponseData> getCommissionById(@PathVariable("id") Integer id) {
		return commissionService.getCommissionById(id);
	}
	
	@PostMapping("/add")
	public ResponseEntity<GlobalResponseData> addCommission(@RequestBody Commissions add)
	{
		return commissionService.addCommission(add);
	}
	@GetMapping("/getall")
	public ResponseEntity<GlobalResponseListData> getAllCommissions() {
		return commissionService.getAllCommissions();
	}
		
	@GetMapping("/getbyusername")
	public ResponseEntity<GlobalResponseListData> getCommissionsByUsername() {
		
		return commissionService.getCommissionsByUsername();
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
	
	
	//4. UPDATE COMMISSIONS BY ID
	@PutMapping("/updatebyid/{id}")
	public ResponseEntity<GlobalResponseData> updateCommissions(@PathVariable("id") Integer id, @RequestBody Commissions newUpdateCommissions) {
		
		return commissionService.updateCommission(id, newUpdateCommissions);
		
	}	
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
