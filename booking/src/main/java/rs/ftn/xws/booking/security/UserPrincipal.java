package rs.ftn.xws.booking.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -901909149073198612L;
	
	private String id;
	private String password;
	private String username;
	private boolean enabled;
	private Collection<? extends GrantedAuthority> autorities;
	
	public UserPrincipal(String id, String password, String username, boolean enabled, Collection<? extends GrantedAuthority> autorities) {
		this.id = id;
		this.password = password;
		this.username = username;
		this.autorities = autorities;
		this.enabled = enabled;
	}
	
	public UserPrincipal(String id, String username, boolean enabled, Collection<? extends GrantedAuthority> autorities) {
		this(id, null, username, enabled, autorities);
	}
	
	public String getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return autorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
