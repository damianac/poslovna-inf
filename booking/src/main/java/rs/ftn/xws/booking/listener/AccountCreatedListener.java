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

import rs.ftn.xws.booking.events.AccountCreatedEvent;

@Component
public class AccountCreatedListener implements ApplicationListener<AccountCreatedEvent> {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine templateEngine;

	@Value("${app.emailActivationUrl}")
	private String emailActivationUrl;

	private static final Logger logger = LoggerFactory.getLogger(AccountCreatedListener.class);

	@Override
	@Async
	public void onApplicationEvent(AccountCreatedEvent event) {
		logger.info("Sending activation email to {}.", event.getEmail());

		String email = event.getEmail();
		String token = event.getToken();

		Context context = new Context();
		context.setVariable("url", emailActivationUrl + "?token=" + token);
		String htmlContent = templateEngine.process("emailConfirmation", context);

		mailSender.send(prepareEmail(email, htmlContent));
	}

	private MimeMessagePreparator prepareEmail(String recipient, String htmlContent) {
		return mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("booking.xws@gmail.com");
			messageHelper.setTo(recipient);
			messageHelper.setSubject("Activation email");
			messageHelper.setText(htmlContent, true);
		};
	}

}
