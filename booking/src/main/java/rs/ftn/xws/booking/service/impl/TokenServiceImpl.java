package rs.ftn.xws.booking.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import rs.ftn.xws.booking.exception.InvalidToken;
import rs.ftn.xws.booking.persistence.domain.EmailActivationToken;
import rs.ftn.xws.booking.persistence.domain.PasswordResetToken;
import rs.ftn.xws.booking.persistence.domain.User;
import rs.ftn.xws.booking.persistence.repository.EmailActivationTokenRepository;
import rs.ftn.xws.booking.persistence.repository.PasswordResetTokenRepository;
import rs.ftn.xws.booking.service.TokenService;



@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private PasswordResetTokenRepository passwordTokenRepository;

	@Autowired
	private EmailActivationTokenRepository activationTokenRepository;
	
	@Value("${app.resetPasswordTokenExpiration}")
	private int passwordExpirationMinutes;

	@Value("${app.activationTokenExpiration}")
	private int emailActivationMinutes;

	@Override
	public PasswordResetToken generatePasswordResetToken(User user) {
		PasswordResetToken token = new PasswordResetToken();
		token.setToken(UUID.randomUUID().toString());
		token.setUser(user);
		token.setExpiryDate(getExpiryDate(passwordExpirationMinutes));
		passwordTokenRepository.save(token);
		return token;
	}

	@Override
	public User validatePasswordResetToken(String token) {
		PasswordResetToken resetToken = passwordTokenRepository.findByToken(token);

		if (resetToken == null) {
			throw new InvalidToken("ResetToken not found.");
		}

		Date expiryDate = resetToken.getExpiryDate();
		boolean isExpired = new Date().after(expiryDate);

		if (isExpired) {
			throw new InvalidToken("ResetToken expired");
		}

		passwordTokenRepository.delete(resetToken);
		return resetToken.getUser();
	}

	@Override
	public String generateEmailActivationToken(User user) {
		EmailActivationToken activationToken = new EmailActivationToken();
		activationToken.setExpiryDate(getExpiryDate(emailActivationMinutes));
		activationToken.setToken(UUID.randomUUID().toString());
		activationToken.setUser(user);
		activationTokenRepository.save(activationToken);
		return activationToken.getToken();
	}

	private Date getExpiryDate(int minutes) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, minutes);
		return now.getTime();
	}

	@Override
	public User validateEmailConfirmationToken(String token) throws InvalidToken {
		EmailActivationToken activationToken = activationTokenRepository.findByToken(token);

		if (activationToken == null) {
			throw new InvalidToken("ResetToken not found.");
		}

		Date expiryDate = activationToken.getExpiryDate();
		boolean isExpired = new Date().after(expiryDate);

		if (isExpired) {
			throw new InvalidToken("ResetToken expired");
		}

		activationTokenRepository.delete(activationToken);
		return activationToken.getUser();
	}

}
