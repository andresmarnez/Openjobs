package com.andresmarnez.openjobs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

	@Id
	private String category;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},
			mappedBy = "categories")
	@JsonIgnore
	private List<Candidate> candidate_tags = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},
			mappedBy = "tags")
	@JsonIgnore
	private List<JobOffer> offer_tags = new ArrayList<>();

	public Category(String category) {
		this.category = category;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Category category1 = (Category) o;
		return category.equals(category1.category);
	}

	@Override
	public int hashCode() {
		return Objects.hash(category);
	}
}
