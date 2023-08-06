package com.tth.demo.housing.controller.member;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tth.demo.housing.domain.dto.form.OwnerForm;
import com.tth.demo.housing.domain.service.OwnerService;

@Controller
@RequestMapping("member/owner")
public class MemberOwnerController {
	
	@Autowired
	private OwnerService service;

	@GetMapping("edit")
	public String edit() {
		return "member/owner/edit";
	}
	
	@PostMapping("edit")
	public String save(@Validated @ModelAttribute("form") OwnerForm form, BindingResult result) {
		if(result.hasErrors()) {
			return "member/owner/edit";
		}
		int id = service.save(form);
		return String.format("redirect:/public/owner/%d", id);
	}
	
	@ModelAttribute("form")
	public OwnerForm form(@RequestParam Optional<Integer> id) {
		return id.filter(a -> a > 0)
				.map(a -> service.findFormById(a))
				.orElse(new OwnerForm());
	}
}