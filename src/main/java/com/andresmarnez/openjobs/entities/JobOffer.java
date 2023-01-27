package com.andresmarnez.openjobs.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job_offer")
public class JobOffer {

	@Id
	private Long id;

	@Column(nullable = false)
	private Boolean isActive;

	@Column(nullable = false)
	private String jobTitle;

	@Column(nullable = false)
	private String jobDescription;

	@Column(nullable = false)
	private LocalDateTime published_time;

	@Column(nullable = false)
	private Integer location;

	@ManyToMany
	private List<Category> tags = new ArrayList<>();

	@Column(nullable = false)
	private Integer vacancies;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	@ManyToMany
	private List<Candidate> candidates = new ArrayList<>();
}
