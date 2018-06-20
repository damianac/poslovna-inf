package rs.ftn.xws.booking.dto;

import javax.validation.constraints.NotBlank;

import rs.ftn.xws.booking.validator.FieldMatch;
import rs.ftn.xws.booking.validator.ValidPassword;



@FieldMatch(first = "newPassword", second = "confirmPassword", message = "The password fields must match")
public class PasswordChangeRequest {
	
	@NotBlank
	@ValidPassword
	private String newPassword;
	
	@NotBlank
	private String confirmPassword;
	
	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
