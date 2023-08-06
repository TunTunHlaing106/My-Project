package com.tth.demo.housing.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Embeddable
@Table(name = "address")
public class Address {

	@Column(nullable = false)
	private String building;
	@Column(nullable = false)
	private String street;
	@Column(nullable = false)
	private String township;
	@Column(nullable = false)
	private String state;
}