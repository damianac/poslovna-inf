package rs.ftn.xws.booking.dto;

import java.util.List;

import rs.ftn.xws.booking.persistence.domain.AccomodationType;
import rs.ftn.xws.booking.persistence.domain.AdditionalService;
import rs.ftn.xws.booking.persistence.domain.Category;

public class FilterDto {

	private List<AccomodationType> types;
	private List<Category> categories;
	private List<AdditionalService> services;

	public FilterDto() {

	}

	public FilterDto(List<AccomodationType> types, List<Category> categories, List<AdditionalService> services) {
		this.types = types;
		this.categories = categories;
		this.services = services;
	}

	public List<AccomodationType> getTypes() {
		return types;
	}

	public void setTypes(List<AccomodationType> types) {
		this.types = types;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<AdditionalService> getServices() {
		return services;
	}

	public void setServices(List<AdditionalService> services) {
		this.services = services;
	}

}
