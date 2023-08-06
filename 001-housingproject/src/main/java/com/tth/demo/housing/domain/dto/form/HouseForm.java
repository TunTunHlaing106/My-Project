package com.tth.demo.housing.domain.dto.form;

import com.tth.demo.housing.domain.entity.Address;
import com.tth.demo.housing.domain.entity.House;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HouseForm {

	private int id;
	
	@NotBlank(message = "Please enter housing name.")
	private String name;
	@NotBlank(message = "Please enter category name.")
	private String category;
	@NotBlank(message = "Please enter number of Floors.")
	private int floors;
	@NotBlank(message = "Please enter number of Master Room.")
	private int masterRoom;
	@NotBlank(message = "Please enter numbare of Single Room.")
	private int singleRoom;
	private int amount;
	@NotBlank(message = "Please enter address.")
	private Address address;
	
	public static HouseForm from(House entity) {
		var form = new HouseForm();
		form.id = entity.getId();
		form.name = entity.getName();
		form.category = entity.getCategory().getName();
		form.floors = entity.getFloors();
		form.masterRoom = entity.getMasterRoom();
		form.singleRoom = entity.getSingleRoom();
		form.amount = entity.getAmount();
		form.address = entity.getAddress();
		return form;
	}
}