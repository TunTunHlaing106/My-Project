package com.tth.demo.housing.domain.dto.vo;

import com.tth.demo.housing.domain.entity.Owner;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OwnerSummaryVO {

	private int id;
	private String name;
	
	public static OwnerSummaryVO from(Owner owner) {
		return new OwnerSummaryVO(owner.getId(), owner.getName());
	}
}