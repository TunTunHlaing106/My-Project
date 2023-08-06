package com.tth.demo.housing.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tth.demo.housing.domain.service.HouseService;
import com.tth.demo.housing.domain.service.OwnerService;

@Controller
@RequestMapping("public/owner")
public class PublicOwnerController {
	
	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private HouseService houseService;

	@GetMapping
	public String index(@RequestParam Optional<String> keyword, ModelMap model) {
		model.put("list", ownerService.search(keyword));
		return "public/owners";
	}
	
	@GetMapping("{id}")
	public String showDetails(@PathVariable int id, ModelMap model) {
		var ownerInfo = ownerService.findInformation(id);
		model.put("ownerInfo", ownerInfo);
		model.put("houses", houseService.findByOwner(id));
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.put("owner", ownerInfo.getMemberEmail().equals(username));
		return "public/owners-details";
	}
}