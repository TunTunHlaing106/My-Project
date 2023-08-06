package com.tth.demo.housing.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Embeddable
public class AuditInfo {

	@Column(columnDefinition = "boolean default false")
	private boolean deleted;
	
	@CreatedDate
	@Column(name = "createdDate")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createAt;

	@CreatedBy
	@Column(name = "create_by")
	private String createBy;

	@LastModifiedDate
	@Column(name = "updatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updateAt;

	@LastModifiedBy
	@Column(name = "update_by")
	private String updateBy;
}