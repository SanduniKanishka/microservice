package com.learning.microservices.CitizenService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.microservices.CitizenService.Entity.Citizen;

import java.util.List;

public interface CitizenRepo extends JpaRepository<Citizen, Integer>{

	public List<Citizen> findByVaccinationCenterId(Integer id);
}
