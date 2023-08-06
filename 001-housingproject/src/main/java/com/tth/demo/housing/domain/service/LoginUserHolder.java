package com.tth.demo.housing.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tth.demo.housing.domain.entity.Account;
import com.tth.demo.housing.domain.repo.AccountRepo;

import jakarta.annotation.PostConstruct;

@Service
@Scope("session")
public class LoginUserHolder {

	private Account acc;
	
	@Autowired
	private AccountRepo repo;
	
	@PostConstruct
	private void postConstruct() {
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		if(null != authentication 
				&& authentication instanceof UsernamePasswordAuthenticationToken 
				&& authentication.isAuthenticated()) {
			var username = authentication.getName();
			repo.findById(username).ifPresent(account -> this.acc = account);
		}
	}
	
	@EventListener
	public void onLoginSuccess(AuthenticationSuccessEvent event) {
		var username = event.getAuthentication().getName();
		repo.findById(username).ifPresent(account -> this.acc = account);
	}
	
	public Account getUser() {
		return acc;
	}
}