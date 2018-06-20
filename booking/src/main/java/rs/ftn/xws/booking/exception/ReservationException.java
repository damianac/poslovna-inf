package rs.ftn.xws.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReservationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3743862672667625637L;

	public ReservationException(String message) {
		super(message);
	}

}
