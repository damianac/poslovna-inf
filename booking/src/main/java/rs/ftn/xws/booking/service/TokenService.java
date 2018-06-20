package rs.ftn.xws.booking.service;

import rs.ftn.xws.booking.exception.InvalidToken;
import rs.ftn.xws.booking.persistence.domain.PasswordResetToken;
import rs.ftn.xws.booking.persistence.domain.User;

public interface TokenService {

	PasswordResetToken generatePasswordResetToken(User user);

	String generateEmailActivationToken(User user);

	User validatePasswordResetToken(String token) throws InvalidToken;

	User validateEmailConfirmationToken(String token) throws InvalidToken;

}
