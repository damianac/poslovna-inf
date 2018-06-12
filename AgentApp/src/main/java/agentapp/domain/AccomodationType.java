package agentapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;

@Entity
public class AccomodationType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NaturalId
	private String type;
	
	@Column
	private Long databaseId;

	public AccomodationType() {
	}

	public AccomodationType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(Long databaseId) {
		this.databaseId = databaseId;
	}
	
}
