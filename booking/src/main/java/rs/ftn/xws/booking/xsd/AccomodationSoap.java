package rs.ftn.xws.booking.xsd;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Accomodation")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccomodationSoap {
	
	@XmlElement
	private Long id;
	
	@XmlElement
	private String name;
	
	@XmlElement(name = "country",required = true)
	private String country;
	
	@XmlElement(name="city",required = true)
	private String city;
	
	@XmlElement(name="address",required = true)
	private String address;
	
	@XmlElement(name = "accomodationType", required = true)
	private Long accomodationType;
	
	@XmlElement(name="category",required = true)
	private Long category;
	
	@XmlElement(name = "description", required = true)
	private String description;
	
	@XmlElement(name = "capacity", required = true)
	private int capacity;
	
	@XmlElementWrapper(name = "additionalServices", required = true)
	@XmlElement(name = "service", required = true)
	private ArrayList<Long> additionalServices;
	
	@XmlElementWrapper(name = "terms", required = true)
	@XmlElement(name = "term", required = true)
	private ArrayList<TermSoap> terms;

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

	public ArrayList<TermSoap> getTerms() {
		return terms;
	}

	public void setTerms(ArrayList<TermSoap> terms) {
		this.terms = terms;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAccomodationType() {
		return accomodationType;
	}

	public void setAccomodationType(Long accomodationType) {
		this.accomodationType = accomodationType;
	}

	public ArrayList<Long> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(ArrayList<Long> additionalServices) {
		this.additionalServices = additionalServices;
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

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
