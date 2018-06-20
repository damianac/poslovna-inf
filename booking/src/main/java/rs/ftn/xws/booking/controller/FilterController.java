package rs.ftn.xws.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ftn.xws.booking.dto.FilterDto;
import rs.ftn.xws.booking.persistence.domain.AccomodationType;
import rs.ftn.xws.booking.persistence.domain.AdditionalService;
import rs.ftn.xws.booking.persistence.domain.Category;
import rs.ftn.xws.booking.service.SearchFilterService;

@RestController
@RequestMapping("api/filter")
public class FilterController {

	@Autowired
	private SearchFilterService searchFilterService;
	
	@GetMapping
	public FilterDto get() {
		return searchFilterService.getSearchFilter();
	}
	
	@PostMapping("types")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public AccomodationType postType(String type) {
		return searchFilterService.addType(type);
	}
	
	@PostMapping("categories")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Category postCategory(String category) {
		return searchFilterService.addCategory(category);
	}
	
	@PostMapping("services")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public AdditionalService postService(String name) {
		return searchFilterService.addAdditionalService(name);
	}
	
}
