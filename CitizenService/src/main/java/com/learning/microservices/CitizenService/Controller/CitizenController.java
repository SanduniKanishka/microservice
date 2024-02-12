package com.learning.microservices.CitizenService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.microservices.CitizenService.Entity.Citizen;
import com.learning.microservices.CitizenService.Repository.CitizenRepo;

import java.util.List;

@RestController
@RequestMapping("/citizen")
public class CitizenController {

	@Autowired
	private CitizenRepo citizenRepo;
	
	@GetMapping(path = "/test")
	public ResponseEntity<String> test() {
		return new ResponseEntity<String>("helloo", HttpStatus.OK);
	}
	
	@GetMapping(path = "/id/{id}")
	public ResponseEntity<List<Citizen>> getById(@PathVariable Integer id) {
		List<Citizen> citizens = citizenRepo.findByVaccinationCenterId(id);
		return new ResponseEntity<>(citizens, HttpStatus.OK);
	}
	
	@PostMapping(path = "/add")
	public ResponseEntity<String> addCitizen(@RequestBody Citizen citizen) {
		System.out.println("name: "+"A");
		System.out.println(citizen);
		citizenRepo.save(citizen);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
}
