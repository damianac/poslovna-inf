package rs.ftn.xws.booking.events;

import org.springframework.context.ApplicationEvent;

public class AccountCreatedEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2484279985163103221L;
	
	private String email;
	private String token;
	
	public AccountCreatedEvent(String email, String token) {
		super(email);
		this.email = email;
		this.token = token;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getToken() {
		return token;
	}
	
}
