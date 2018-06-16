package agentapp.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Term {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Column
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@Column
	private float price;

	@JoinColumn(name = "accomodation_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private Accomodation accomodation;
	
	@Column
	private Long databaseId;
	
	@Column(nullable = false)
	private boolean reserved;
	
	@Column
	private String userId;
	
	@Column(nullable = false)
	private boolean visited;

	public Term() {
	}

	public Term(Date startDate, Date endDate, float price) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
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

	public Accomodation getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(Accomodation accomodation) {
		this.accomodation = accomodation;
	}

	public Long getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(Long databaseId) {
		this.databaseId = databaseId;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

}
