package agentapp.dto;

import java.util.ArrayList;
import java.util.List;

import agentapp.domain.Term;

public class AccomodationInfo {

	private String name;
	private String country;
	private String city;
	private String address;
	private Long accomodationType;
	private Long category;
	private String description;
	private int capacity;
	private ArrayList<Long> additionalServices;
	private List<TermInfo> terms;

	public AccomodationInfo() {
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

	public Long getAccomodationType() {
		return accomodationType;
	}

	public void setAccomodationType(Long accomodationType) {
		this.accomodationType = accomodationType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public ArrayList<Long> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(ArrayList<Long> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public List<TermInfo> getTerms() {
		return terms;
	}

	public void setTerms(List<TermInfo> terms) {
		this.terms = terms;
	}

	@Override
	public String toString() {
		return "AccomodationInfo [name=" + name + ", country=" + country + ", city=" + city + ", address=" + address
				+ ", accomodationType=" + accomodationType + ", description=" + description + ", capacity=" + capacity
				+ ", additionalServices=" + additionalServices + ", terms=" + terms + "]";
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

}
