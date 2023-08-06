package com.tth.demo.housing.domain.entity;

import java.io.Serializable;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "house")
@EntityListeners(value = AuditingEntityListener.class)
public class House implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private int id;

	@Column(nullable = false, name = "housingName")
	private String name;
	
	@Column(nullable = false)
	private Address address;
	
	@Column(nullable = false, name = "numberOfFloors")
	private int floors;
	
	@Column(nullable = false, name = "numberOfMasterRoom")
	private int masterRoom;
	
	@Column(nullable = false, name = "numberOfSingleRoom")
	private int singleRoom;
	
	@ManyToOne(optional = false)
	private Category category;
	
	@ManyToOne(optional = false)
	private Owner owner;
	
	private int amount;
	
	private AuditInfo audit = new AuditInfo();
}