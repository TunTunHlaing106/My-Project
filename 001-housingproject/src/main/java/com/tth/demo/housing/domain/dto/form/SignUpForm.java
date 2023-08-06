package com.tth.demo.housing.domain.dto.form;

import java.util.function.Function;

import com.tth.demo.housing.domain.entity.Account;
import com.tth.demo.housing.domain.entity.Account.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpForm {

	@NotBlank(message = "Please enter  username.")
	private String username;
	
	@Email(message = "Please enter valid email.")
	@NotBlank(message = "Please enter email.")
	private String email;
	
	@NotBlank(message = "Please enter password.")
	private String password;
	
	public Account entity(Function<String, String> passwordEncoder) {
		var entity = new Account(username, email, passwordEncoder.apply(password));
		entity.setRole(Role.Member);
		return entity;
	}
}