package com.learning.microservices.vaccinationcenter.Repository;

import com.learning.microservices.vaccinationcenter.Entity.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationRepo extends JpaRepository<VaccinationCenter, Integer> {
}
