package com.tth.demo.housing.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.tth.demo.housing.domain.dto.form.OwnerForm;
import com.tth.demo.housing.domain.dto.vo.OwnerListVO;
import com.tth.demo.housing.domain.dto.vo.OwnerSummaryVO;
import com.tth.demo.housing.domain.entity.Owner;
import com.tth.demo.housing.domain.repo.AccountRepo;
import com.tth.demo.housing.domain.repo.OwnerRepo;

@Service
@Transactional(readOnly = true)
public class OwnerService {
	
	@Autowired
	private OwnerRepo repo;
	
	@Autowired
	private AccountRepo accountRepo;

	public OwnerForm findFormById(Integer id) {
		return repo.findById(id).map(OwnerForm::from).orElseThrow();
	}

	@Transactional
	public int save(OwnerForm form) {
		if(form.getId() == 0) {
			var entity = form.entity();
			var username = SecurityContextHolder.getContext().getAuthentication().getName();
			var loginUser = accountRepo.findById(username).orElseThrow();
			entity.setMem(loginUser);
			entity = repo.save(entity);
			return entity.getId();
		}
		repo.findById(form.getId()).ifPresent(owner -> {
			owner.setName(form.getOwnername());
		});
		return form.getId();
	}

	public List<OwnerSummaryVO> findOneOwners() {
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		return repo.findByMemberEmail(username)
				.map(OwnerSummaryVO::from).collect(Collectors.toList());
	}

	public OwnerListVO findInformation(int id) {
		return repo.findById(id)
				.map(OwnerListVO::from).orElseThrow();
	}

	public List<OwnerListVO> search(Optional<String> keyword) {
		return repo.findAll(whichKeyword(keyword)).stream()
				.map(OwnerListVO::from).collect(Collectors.toList());
	}
	
	private Specification<Owner> whichKeyword(Optional<String> keyword) {	
		if(keyword.filter(StringUtils::hasLength).isEmpty()) {
			return Specification.where(null);
		}	
		return (root, query, cb) -> cb.like(cb.lower(root.get("name")),
				keyword.get().toLowerCase().concat("%"));
	}
}