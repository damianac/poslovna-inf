package rs.ftn.xws.booking.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import rs.ftn.xws.booking.events.PasswordResetEvent;
import rs.ftn.xws.booking.persistence.domain.PasswordResetToken;
import rs.ftn.xws.booking.persistence.domain.User;

@Component
public class PasswordResetListener implements ApplicationListener<PasswordResetEvent> {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine templateEngine;

	@Value("${app.passwordResetUrl}")
	private String resetPasswordUrl;

	private static final Logger logger = LoggerFactory.getLogger(PasswordResetListener.class);

	@Override
	@Async
	public void onApplicationEvent(PasswordResetEvent event) {
		logger.info("PasswordResetEvent has occured. Sending password reset mail to {}",
				event.getToken().getUser().getEmail());

		PasswordResetToken passwordResetToken = event.getToken();
		User user = passwordResetToken.getUser();

		Context context = new Context();
		context.setVariable("url", resetPasswordUrl + "?token=" + passwordResetToken.getToken());
		String htmlContent = templateEngine.process("passwordRecovery", context);

		mailSender.send(prepareEmail(user.getEmail(), htmlContent));
	}

	private MimeMessagePreparator prepareEmail(String recipient, String htmlContent) {
		return mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("booking.xws@gmail.com");
			messageHelper.setTo(recipient);
			messageHelper.setSubject("Password reset");
			messageHelper.setText(htmlContent, true);
		};
	}

}
