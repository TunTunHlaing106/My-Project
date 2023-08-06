package com.tth.demo.housing.domain.entity;

import java.io.Serializable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "owner")
@EntityListeners(value = AuditingEntityListener.class)
public class Owner implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NonNull
	@Column(nullable = false, name = "ownerName")
	private String name;
	
	private String message;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "owerId")
	private Account mem;
	
	private AuditInfo audit = new AuditInfo();

	@NonNull
	@Column(nullable = false)
	private String memberEmail;
}