package com.learning.microservices.vaccinationcenter.Controller;

import com.learning.microservices.vaccinationcenter.Entity.VaccinationCenter;
import com.learning.microservices.vaccinationcenter.Model.Citizen;
import com.learning.microservices.vaccinationcenter.Model.RequiredResponse;
import com.learning.microservices.vaccinationcenter.Repository.VaccinationRepo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/vaccinationcenter")
public class VaccinationCenterController {

    @Autowired
    private VaccinationRepo vaccinationRepo;

    @Autowired
    private RestTemplate restTemplate;
    @PostMapping(path = "/add")
    public ResponseEntity<VaccinationCenter> addVaccinationCenter(@RequestBody VaccinationCenter vaccinationCenter) {
        VaccinationCenter vaccinationCenter1 = vaccinationRepo.save(vaccinationCenter);
        return new ResponseEntity<>(vaccinationCenter1, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @HystrixCommand(fallbackMethod = "handleCitizenDownTime")
    public ResponseEntity<RequiredResponse> getallDataBasedOnCenterID(@PathVariable Integer id){
        RequiredResponse response = new RequiredResponse();
        VaccinationCenter vaccinationCenter = vaccinationRepo.findById(id).get();
        response.setVaccinationCenter(vaccinationCenter);

        List<Citizen> citizens = restTemplate.getForObject("http://CITIZEN-SERVICE/citizen/id/"+id,List.class);
        response.setCitizens(citizens);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<RequiredResponse> handleCitizenDownTime(@PathVariable Integer id){
        RequiredResponse response = new RequiredResponse();
        VaccinationCenter vaccinationCenter = vaccinationRepo.findById(id).get();
        response.setVaccinationCenter(vaccinationCenter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
