package com.tth.demo.housing.domain.entity;

import java.io.Serializable;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "ACCOUNT")
@EntityListeners(value = AuditingEntityListener.class)
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@NonNull
	private String email;

	@Column(nullable = false, name = "ownerUserName")
	private String username;

	@Column(nullable = false)
	private Role role;

	@Column(nullable = false)
	@NonNull
	private String password;

	private AuditInfo audit = new AuditInfo();

	public enum Role {
		Admin,
		Member
	}

	public Account(@NonNull String email, String username, @NonNull String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
	}
}