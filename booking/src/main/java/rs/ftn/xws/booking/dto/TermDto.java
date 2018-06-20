package rs.ftn.xws.booking.dto;

import java.util.Date;

public class TermDto {

	private Long id;
	private Date startDate;
	private Date endDate;
	private float price;
	private AccomodationDto accomodation;

	public TermDto() {

	}

	public TermDto(Long id, Date startDate, Date endDate, float price, AccomodationDto accomodation) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.accomodation = accomodation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public AccomodationDto getAccomodation() {
		return accomodation;
	}
	
	public void setAccomodation(AccomodationDto accomodation) {
		this.accomodation = accomodation;
	}

}
