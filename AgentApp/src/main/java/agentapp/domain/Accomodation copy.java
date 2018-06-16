package agentapp.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Accomodation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotBlank
	private String name;

	@Column
	@NotBlank
	private String country;

	@Column
	@NotBlank
	private String city;

	@Column
	@NotBlank
	private String address;

	@JoinColumn(name = "typeId")
	@ManyToOne(fetch = FetchType.LAZY)
	private AccomodationType accomodationType;
	
	@JoinColumn(name = "categoryId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;

	@Column
	private String description;

	@Column
	private int capacity;
	
	@Column
	private Long databaseId;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "accomodation_services", 
		joinColumns = @JoinColumn(name = "accomodation_id"), 
		inverseJoinColumns = @JoinColumn(name = "service_id"))
	private Set<AdditionalService> additionalServices;

	@OneToMany(mappedBy = "accomodation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Term> terms;

	@OneToMany(mappedBy = "accomodation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<AccomodationImage> images;

	public Accomodation() {
	}

	public Long getId() {
		return id;
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

	public AccomodationType getAccomodationType() {
		return accomodationType;
	}

	public void setAccomodationType(AccomodationType accomodationType) {
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

	public Set<AdditionalService> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<AdditionalService> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public List<Term> getTerms() {
		return terms;
	}

	public void setTerms(List<Term> terms) {
		this.terms = terms;
	}

	public List<AccomodationImage> getImages() {
		return images;
	}

	public void setImages(List<AccomodationImage> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "Accomodation [id=" + id + ", name=" + name + ", country=" + country + ", city=" + city + ", address="
				+ address + ", accomodationType=" + accomodationType + ", category=" + category + ", description="
				+ description + ", capacity=" + capacity + ", databaseId=" + databaseId + ", additionalServices="
				+ additionalServices + ", terms=" + terms + ", images=" + images + "]";
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(Long databaseId) {
		this.databaseId = databaseId;
	}

}
