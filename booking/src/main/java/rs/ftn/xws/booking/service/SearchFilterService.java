package rs.ftn.xws.booking.service;

import rs.ftn.xws.booking.dto.FilterDto;
import rs.ftn.xws.booking.persistence.domain.AccomodationType;
import rs.ftn.xws.booking.persistence.domain.AdditionalService;
import rs.ftn.xws.booking.persistence.domain.Category;

public interface SearchFilterService {

	AccomodationType addType(String type);

	Category addCategory(String category);

	AdditionalService addAdditionalService(String name);

	FilterDto getSearchFilter();
	
}
