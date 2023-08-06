package com.tth.demo.housing.domain.dto.vo;

import com.tth.demo.housing.domain.entity.Owner;

import lombok.Data;

@Data
public class OwnerListVO {

	private int id;
	private String name;
	private String message;
	private String memberEmail;
	
	public static OwnerListVO from(Owner entity) {
		var vo = new OwnerListVO();
		vo.id = entity.getId();
		vo.name = entity.getName();
		vo.message = entity.getMessage();
		vo.memberEmail = entity.getMemberEmail();
		return vo;
	}
}