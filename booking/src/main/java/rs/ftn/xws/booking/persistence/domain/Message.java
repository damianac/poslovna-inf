package rs.ftn.xws.booking.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String message;

	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private User user;

	@JoinColumn(name = "term_id")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Term term;

	private String agent;

	public Message() {

	}

	public Message(String message, User user, Term term, String agent) {
		this.message = message;
		this.user = user;
		this.term = term;
		this.agent = agent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

}
