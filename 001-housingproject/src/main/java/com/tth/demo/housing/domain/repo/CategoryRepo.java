package com.tth.demo.housing.domain.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.tth.demo.housing.domain.entity.Category;

public interface CategoryRepo extends JpaRepositoryImplementation<Category, Integer> {

	Optional<Category> findOneByName(String name);
}