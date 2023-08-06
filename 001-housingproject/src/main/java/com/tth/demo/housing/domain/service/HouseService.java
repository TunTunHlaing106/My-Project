package com.tth.demo.housing.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tth.demo.housing.domain.dto.form.HouseForm;
import com.tth.demo.housing.domain.dto.vo.HouseDetailsVO;
import com.tth.demo.housing.domain.dto.vo.HouseListVO;
import com.tth.demo.housing.domain.entity.Category;
import com.tth.demo.housing.domain.entity.House;
import com.tth.demo.housing.domain.repo.CategoryRepo;
import com.tth.demo.housing.domain.repo.HouseRepo;
import com.tth.demo.housing.domain.repo.OwnerRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HouseService {
	
	private final HouseRepo repo;
	private final CategoryRepo categoryRepo;
	private final OwnerRepo ownerRepo;
	
	public List<HouseListVO> findByOwner(int id) {
		return repo.findByOwnerId(id)
				.map(HouseListVO::from).collect(Collectors.toList());
	}
	
	@Transactional
	public int save(HouseForm form) {
		var category = categoryRepo.findOneByName(form.getCategory())
				.orElseGet(() -> {
					var entity = new Category();
					entity.setName(form.getCategory());
					return categoryRepo.save(entity);
				});
		if(form.getId() == 0) {
			var entity = new House();
			entity.setName(form.getName());
			entity.setCategory(category);
			entity.setAddress(form.getAddress());
			entity.setFloors(form.getFloors());
			entity.setMasterRoom(form.getMasterRoom());
			entity.setSingleRoom(form.getSingleRoom());
			entity.setAmount(form.getAmount());
			var owner = ownerRepo.findById(form.getAmount()).orElseThrow();
			entity.setOwner(owner);
			return repo.save(entity).getId();
		}
		return repo.findById(form.getId())
				.map(entity -> {
					entity.setName(form.getName());
					entity.setCategory(category);
					entity.setAddress(form.getAddress());
					entity.setFloors(form.getFloors());
					entity.setMasterRoom(form.getMasterRoom());
					entity.setSingleRoom(form.getSingleRoom());
					entity.setAmount(form.getAmount());
					return entity.getId();
				}).orElseThrow();
	}

	public Optional<HouseDetailsVO> findDetailsById(int id) {
		return repo.findById(id).map(HouseDetailsVO::from);
	}

	public List<HouseListVO> search(Optional<Integer> category, Optional<String> keyword) {
		return repo.findAll(withCategory(category)
					.and(withKeyword(keyword)))
				.stream().map(HouseListVO::from).collect(Collectors.toList());
	}
	
	private Specification<House> withCategory(Optional<Integer> data) {
		if(data.isPresent()) {
			return (root, query, cb) -> cb.equal(root.get("category").get("id"), data.get());
		}
		return Specification.where(null);
	}

	private Specification<House> withKeyword(Optional<String> data) {
		if(data.isPresent()) {
			return (root, query, cb) -> cb.or(
					cb.like(cb.lower(root.get("name")), data.get().toLowerCase().concat("%")),
					cb.like(cb.lower(root.get("category").get("name")), data.get().toLowerCase().concat("%")),
					cb.like(cb.lower(root.get("floors")), data.get().toLowerCase().concat("%")),
					cb.like(cb.lower(root.get("masterRoom")), data.get().toLowerCase().concat("%")),
					cb.like(cb.lower(root.get("singleRoom")), data.get().toLowerCase().concat("%")),
					cb.like(cb.lower(root.get("creDate")), data.get().toLowerCase().concat("%"))
			);
		}
		return Specification.where(null);
	}

	public HouseForm getFormById(Integer id) {
		return repo.findById(id).map(HouseForm::from).orElseThrow();
	}
}