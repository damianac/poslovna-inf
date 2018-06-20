package rs.ftn.xws.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ftn.xws.booking.dto.FilterDto;
import rs.ftn.xws.booking.persistence.domain.AccomodationType;
import rs.ftn.xws.booking.persistence.domain.AdditionalService;
import rs.ftn.xws.booking.persistence.domain.Category;
import rs.ftn.xws.booking.persistence.repository.AccomodationTypeRepository;
import rs.ftn.xws.booking.persistence.repository.AdditionalServiceRepository;
import rs.ftn.xws.booking.persistence.repository.CategoryRepository;
import rs.ftn.xws.booking.service.SearchFilterService;

@Service
public class SearchFilterServiceImpl implements SearchFilterService {

	@Autowired
	private AccomodationTypeRepository accomodationTypeRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private AdditionalServiceRepository additionalServiceRepository;

	@Override
	public AccomodationType addType(String type) {
		if (!accomodationTypeRepository.existsByType(type)) {
			return accomodationTypeRepository.save(new AccomodationType(type));
		}

		return null;
	}

	@Override
	public Category addCategory(String category) {
		if (!categoryRepository.existsByCategory(category)) {
			return categoryRepository.save(new Category(category));
		}

		return null;
	}

	@Override
	public AdditionalService addAdditionalService(String name) {
		if (!additionalServiceRepository.existsByName(name)) {
			return additionalServiceRepository.save(new AdditionalService(name));
		}

		return null;
	}
	
	@Override
	public FilterDto getSearchFilter() {
		List<AccomodationType> types = accomodationTypeRepository.findAll();
		List<Category> categories = categoryRepository.findAll();
		List<AdditionalService> services = additionalServiceRepository.findAll();
		
		return new FilterDto(types, categories, services);
	}

}
