package com.learning.microservices.vaccinationcenter.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Citizen {

	private int id;

	private String name;

	private int vaccinationCenterId;

}
