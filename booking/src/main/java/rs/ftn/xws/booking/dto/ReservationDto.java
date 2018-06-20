package rs.ftn.xws.booking.dto;

import java.util.Date;

import rs.ftn.xws.booking.persistence.domain.Term;

public class ReservationDto {

	private Long id;
	private Date from;
	private Date to;
	private Float price;
	private String accomodationName;

	public ReservationDto() {

	}

	public ReservationDto(Term term) {
		this.id = term.getId();
		this.from = term.getStartDate();
		this.to = term.getEndDate();
		this.price = term.getPrice();
		this.accomodationName = term.getAccomodation().getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getAccomodationName() {
		return accomodationName;
	}

	public void setAccomodationName(String accomodationName) {
		this.accomodationName = accomodationName;
	}

}
