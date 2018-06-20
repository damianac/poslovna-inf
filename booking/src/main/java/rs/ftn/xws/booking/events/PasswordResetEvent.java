package rs.ftn.xws.booking.events;

import org.springframework.context.ApplicationEvent;

import rs.ftn.xws.booking.persistence.domain.PasswordResetToken;

public class PasswordResetEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2918129303689436035L;

	private PasswordResetToken token;

	public PasswordResetEvent(PasswordResetToken token) {
		super(token);
		this.token = token;
	}

	public PasswordResetToken getToken() {
		return token;
	}

}
