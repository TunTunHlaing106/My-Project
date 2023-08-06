package com.tth.demo.housing.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tth.demo.housing.domain.dto.form.SignUpForm;
import com.tth.demo.housing.security.SignUpService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class SignUpController {
	
	@Autowired
	private SignUpService service;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private SecurityContextRepository securityContextRepository;
	
	@ModelAttribute("form")
	public SignUpForm form() {
		return new SignUpForm();
	}
	
	@GetMapping("public/signin")
	public String signIn() {
		return "public/sign-in";
	}
	
	@PostMapping("public/signup")
	public String signUp(
			ModelMap model, HttpServletRequest request, HttpServletResponse resp,
		@Validated @ModelAttribute("form") SignUpForm form, BindingResult result) {
		if(result.hasErrors()) {
			model.put("error", 1);
			return "public/sign-in";
		}
		service.signUp(form);
		var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword()));
		var context = SecurityContextHolder.getContext();
		context.setAuthentication(authentication);
		securityContextRepository.saveContext(context, request, resp);
		String redirectUrl = getRedirectUrl(request, resp).orElse("/member/home");
		return String.format("redirect:%s", redirectUrl);
	}
	
	private Optional<String> getRedirectUrl(HttpServletRequest request, HttpServletResponse resp) {
		var requestCache = new HttpSessionRequestCache();
		var savedRequest = requestCache.getRequest(request, resp);
		return Optional.ofNullable(savedRequest)
				.map(a -> a.getRedirectUrl());
	}
}