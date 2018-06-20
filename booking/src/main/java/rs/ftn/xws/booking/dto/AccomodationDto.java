package rs.ftn.xws.booking.dto;

import java.util.List;

public class AccomodationDto {

	private Long id;
	private String name;
	private String country;
	private String city;
	private String address;
	private String description;
	private String category;
	private String accomodationType;
	private List<String> additionalService;
	private List<String> images;

	public Long getId() {
		return id;
	}

	public AccomodationDto() {

	}

	public AccomodationDto(Long id, String name, String country, String city, String address, String description,
			String category, String accomodationType, List<String> additionalService, List<String> images) {
		this.id = id;
		this.name = name;
		this.country = country;
		this.city = city;
		this.address = address;
		this.description = description;
		this.category = category;
		this.accomodationType = accomodationType;
		this.additionalService = additionalService;
		this.images = images;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAccomodationType() {
		return accomodationType;
	}

	public void setAccomodationType(String accomodationType) {
		this.accomodationType = accomodationType;
	}

	public List<String> getAdditionalService() {
		return additionalService;
	}

	public void setAdditionalService(List<String> additionalService) {
		this.additionalService = additionalService;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

}
