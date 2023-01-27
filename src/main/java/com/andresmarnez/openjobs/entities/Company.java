package com.andresmarnez.openjobs.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company {

	@Id
	private Long vatNum;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String location;

	private String description;

	@Column(nullable = false)
	private String country;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String phone;

	@OneToMany
	private List<JobOffer> offers =  new ArrayList<>();

}
