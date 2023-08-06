package com.tth.demo.housing.domain.dto.form;

import java.io.Serializable;

import com.tth.demo.housing.domain.entity.Address;
import com.tth.demo.housing.domain.entity.House;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressForm implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Please enter receiver name.")
	private House name;
	
	@NotBlank(message = "Please building address.")
	private String building;
	@NotBlank(message = "Please street address.")
	private String street;
	@NotBlank(message = "Please township.")
	private String township;
	@NotBlank(message = "Please state.")
	private String state;
	
	public static AddressForm from(Address entity) {
		var form = new AddressForm();
		form.building = entity.getBuilding();
		form.street = entity.getStreet();
		form.township = entity.getTownship();
		form.state = entity.getState();
		return form;
	}
}
