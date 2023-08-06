package com.tth.demo.housing.domain.dto.vo;

import com.tth.demo.housing.domain.entity.House;

import lombok.Data;

@Data
public class HouseListVO {

	private int id;
	private String name;
	private String category;
	private String address;
	private int floors;
	private int masterRoom;
	private int singleRoom;
	private int amount;
	
	public static HouseListVO from(House entity) {
		var vo = new HouseListVO();
		vo.id = entity.getId();
		vo.name = entity.getName();
		vo.category = entity.getCategory().getName();
		vo.address = entity.getAddress().getBuilding();
		vo.address = entity.getAddress().getStreet();
		vo.address = entity.getAddress().getTownship();
		vo.address = entity.getAddress().getState();
		vo.floors = entity.getFloors();
		vo.masterRoom = entity.getMasterRoom();
		vo.singleRoom = entity.getSingleRoom();
		vo.amount = entity.getAmount();
		return vo;
	}
}