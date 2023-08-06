package com.tth.demo.housing.domain.repo;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.tth.demo.housing.domain.entity.House;

@EnableJpaRepositories
public interface HouseRepo extends JpaRepositoryImplementation<House, Integer> {

	Stream<House> findByOwnerId(int id);
}