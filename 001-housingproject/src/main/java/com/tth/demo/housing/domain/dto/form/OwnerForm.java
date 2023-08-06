package com.tth.demo.housing.domain.dto.form;

import com.tth.demo.housing.domain.entity.Owner;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerForm {

	private int id;
	
	@NotBlank(message = "Please enter full name.")
	private String ownername;
	
	@NotBlank(message = "Please enter full name.")
	private String message;

	public Owner entity() {
		return new Owner(ownername,message);
	}
	
	public static OwnerForm from(Owner entity) {
		return new OwnerForm(entity.getId(), entity.getName(),entity.getMessage());
	}
}