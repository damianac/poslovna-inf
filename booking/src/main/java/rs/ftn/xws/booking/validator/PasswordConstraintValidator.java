package rs.ftn.xws.booking.validator;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.DictionaryRule;
import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import org.passay.WhitespaceRule;
import org.passay.dictionary.WordListDictionary;
import org.passay.dictionary.WordLists;
import org.passay.dictionary.sort.ArraysSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

	private PasswordValidator validator;
	private static final Logger logger = LoggerFactory.getLogger(PasswordConstraintValidator.class);

	public PasswordConstraintValidator() {
		List<Rule> rules = getRules();

		try {
            rules.add(getDictionaryRule());
        } catch (IOException e) {
            logger.error("Couldn't load forbidden password list {}", e.getMessage());
        }
		
		validator = new PasswordValidator(rules);
	}

	private List<Rule> getRules() {
		return new ArrayList<>(Arrays.asList(
			new LengthRule(6, 30),
			new UppercaseCharacterRule(1), 
			new DigitCharacterRule(1), 
			new SpecialCharacterRule(1),
			new WhitespaceRule()
		)); 
	}
	
	private DictionaryRule getDictionaryRule() throws IOException {
		File file = new ClassPathResource("forbidden-password-list.txt").getFile();
		
		return new DictionaryRule(new WordListDictionary(
			WordLists.createFromReader(
	    		new FileReader[] { new FileReader(file) },
	            false,
	            new ArraysSort()
			)));
	}
	
	@Override
	public void initialize(ValidPassword arg0) {
		
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		RuleResult result = validator.validate(new PasswordData(password));

		if (result.isValid()) {
			return true;
		}
		
		context.buildConstraintViolationWithTemplate(String.join(" ", validator.getMessages(result)))
			.addConstraintViolation()
			.disableDefaultConstraintViolation();
		
		return false;
	}
}
