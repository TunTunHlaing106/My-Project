package com.tth.demo.housing.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tth.demo.housing.domain.entity.Category;
import com.tth.demo.housing.domain.repo.CategoryRepo;
import com.tth.demo.housing.domain.service.HouseService;

import lombok.Data;

//import com.tth.demo.housing.domain.entity.Category;
//import com.tth.demo.housing.domain.repo.CategoryRepo;
//import com.tth.demo.housing.domain.service.HouseService;

import lombok.RequiredArgsConstructor;

@Data
@Controller
@RequestMapping("public/home")
@RequiredArgsConstructor
public class HomeController {
	
	private final CategoryRepo repo;
	private final HouseService service;

	@GetMapping
	public String index (
			@RequestParam Optional<Integer> category,
			@RequestParam Optional<String> keyword, 
			ModelMap model
			) {
		model.put("list", service.search(category, keyword));
		return "public/home";
	}
	
	@ModelAttribute("categories")
	public List<Category> categories() {
		return repo.findAll();
	}
}