package com.tth.demo.housing.domain.repo;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.tth.demo.housing.domain.entity.Owner;

@EnableJpaRepositories
public interface OwnerRepo extends JpaRepositoryImplementation<Owner, Integer> {
	
	Stream<Owner> findByMemberEmail(String memberEmail);
}