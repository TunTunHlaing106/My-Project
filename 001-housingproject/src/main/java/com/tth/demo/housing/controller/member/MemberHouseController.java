package com.tth.demo.housing.controller.member;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tth.demo.housing.domain.dto.form.HouseForm;
import com.tth.demo.housing.domain.service.HouseService;
import com.tth.demo.housing.domain.service.OwnerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("member/house")
@RequiredArgsConstructor
public class MemberHouseController {
	
	private final OwnerService ownerService;
	private final HouseService houseService;
	
	@GetMapping("edit")
	public String edit(@RequestParam int owner, @RequestParam("id") Optional<Integer> houseId, ModelMap model) {
		model.put("ownerInfo", ownerService.findInformation(owner));
		return "member/house/edit";
	}
	
	@ModelAttribute("form")
	public HouseForm form(@RequestParam int owner, @RequestParam("id") Optional<Integer> houseId) {
		return houseId.filter(a -> a > 0).map(id -> houseService.getFormById(id)).orElseGet(() -> {
			var form = new HouseForm();
			form.setAmount(owner);
			return form;
		});
	}
}