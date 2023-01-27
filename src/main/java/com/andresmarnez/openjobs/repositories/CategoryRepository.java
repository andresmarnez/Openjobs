package com.andresmarnez.openjobs.repositories;

import com.andresmarnez.openjobs.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}