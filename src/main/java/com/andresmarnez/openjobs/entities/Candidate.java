package com.andresmarnez.openjobs.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "candidates")
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Boolean isOpenToWork;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(unique = true, nullable = false)
	private String email;

	private Character gender;

	private String street;

	private String location;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	@JoinTable(name = "candidate_category",
			joinColumns = { @JoinColumn(name = "candidates_id") },
			inverseJoinColumns = { @JoinColumn(name = "category_category") })
	private List<Category> categories = new ArrayList<>();

	@ManyToMany
	private List<JobOffer> applied_offers = new ArrayList<>();
}
