package com.andresmarnez.openjobs.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
@Table(name = "categories")
public class Category {

	@Id
	private String category;

	@ManyToMany
	private List<Candidate> candidate_tags = new ArrayList<>();

	@ManyToMany
	private List<JobOffer> offer_tags = new ArrayList<>();
}
