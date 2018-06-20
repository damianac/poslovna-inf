package rs.ftn.xws.booking.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import rs.ftn.xws.booking.validator.FieldMatch;
import rs.ftn.xws.booking.validator.ValidPassword;

@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class SignUpRequest {

	@NotBlank
	@Size(min = 3)
	private String email;

	@NotBlank
	@ValidPassword
	private String password;

	@NotBlank
	private String confirmPassword;

	public String getEmail() {
		return email;
	}

	public void setEmail(String username) {
		this.email = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
