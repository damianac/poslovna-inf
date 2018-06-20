package rs.ftn.xws.booking.dto;

import java.util.List;

public class ProfileDto {

	private String username;
	
	private List<String> authorities;
	
	public ProfileDto(String username, List<String> authorities) {
		this.username = username;
		this.authorities = authorities;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public List<String> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	
}
