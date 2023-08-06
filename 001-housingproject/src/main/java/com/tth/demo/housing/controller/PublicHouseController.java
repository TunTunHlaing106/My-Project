package com.tth.demo.housing.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tth.demo.housing.domain.service.HouseService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("public/product")
@RequiredArgsConstructor
public class PublicHouseController {
	
	private final HouseService houseService;

	@GetMapping("{id}")
	String showDetails(@PathVariable int id, ModelMap model) {
		var data = houseService.findDetailsById(id).orElseThrow();
		model.put("data", data);
		model.put("owner", data.getOwnerMember().equals(SecurityContextHolder.getContext().getAuthentication().getName()));
		return "member/house/details";
	}
}
