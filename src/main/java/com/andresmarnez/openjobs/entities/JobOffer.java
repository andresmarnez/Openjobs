package com.andresmarnez.openjobs.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job_offers")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id"
)
public class JobOffer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@Column(nullable = false)
	private Boolean isActive = true;

	@Column(nullable = false)
	private String jobTitle;

	@Column(nullable = false)
	private String jobDescription;

	@JsonFormat(pattern="E, dd MMM YY")
	private LocalDate publishedTime = LocalDate.now();

	@Column(nullable = false)
	private String location;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	@JoinTable(name = "tutorial_tags",
			joinColumns = { @JoinColumn(name = "job_offers_id") },
			inverseJoinColumns = { @JoinColumn(name = "category_category") })
	private List<Category> tags = new ArrayList<>();

	@Column(nullable = false)
	private Integer vacancies;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id")
	private Company company;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	@JoinTable(name = "job_offer_candidates",
			joinColumns = { @JoinColumn(name = "job_offers_id") },
			inverseJoinColumns = { @JoinColumn(name = "candidates_id") })
	private List<Candidate> candidates = new ArrayList<>();
}
