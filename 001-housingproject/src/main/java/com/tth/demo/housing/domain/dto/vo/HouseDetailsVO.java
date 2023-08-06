package com.tth.demo.housing.domain.dto.vo;

import com.tth.demo.housing.domain.entity.House;

import lombok.Data;

@Data
public class HouseDetailsVO {

	private int id;
	private String name;
	
	private int categoryId;
	private String categoryName;
	
	private int ownerId;
	private String ownerName;
	
	private int floors;
	private int masterRoom;
	private int singleRoom;
	
	private int addressId;
	private String address;

	private int amount;
	
	private String ownerMember;
	
	
	public static HouseDetailsVO from(House entity) {
		var vo = new HouseDetailsVO();
		vo.id = entity.getId();
		vo.name = entity.getName();
		vo.categoryId = entity.getCategory().getId();
		vo.categoryName = entity.getCategory().getName();
		vo.floors = entity.getFloors();
		vo.masterRoom = entity.getMasterRoom();
		vo.singleRoom = entity.getSingleRoom();
		vo.address = entity.getAddress().getBuilding();
		vo.address = entity.getAddress().getStreet();
		vo.address = entity.getAddress().getTownship();
		vo.address = entity.getAddress().getState();
		vo.amount = entity.getAmount();
		return vo;
	}
}