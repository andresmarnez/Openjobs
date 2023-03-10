package com.andresmarnez.openjobs.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id"
)
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@Column(nullable = false, unique = true)
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

	@OneToMany(fetch = FetchType.EAGER,
			mappedBy = "company",
			cascade = {
				CascadeType.REMOVE, CascadeType.MERGE
			}
	)
	@Column(unique = true)
	private List<JobOffer> offers =  new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Company company = (Company) o;
		return id.equals(company.id);
	}

}
