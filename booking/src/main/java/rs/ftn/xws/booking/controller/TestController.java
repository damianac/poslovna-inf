package rs.ftn.xws.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ftn.xws.booking.persistence.domain.User;
import rs.ftn.xws.booking.persistence.repository.UserRepository;
import rs.ftn.xws.booking.security.UserPrincipal;

@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	private UserRepository repository;

	@GetMapping("/1")
	@PreAuthorize("hasRole('USER')")
	public String getHello() {
		return "Hello World!";
	}

	@GetMapping("/2")
	@PreAuthorize("hasAuthority('CREATE')")
	public String getHasAuthority(Authentication authentication) {
		UserPrincipal details = (UserPrincipal) authentication.getPrincipal();

		return "Username: " + details.getUsername() + " id: " + details.getId() + " authorities: "
				+ details.getAuthorities();
	}

	@GetMapping("/3")
	public String getTest3() {
		User user = repository.findCurrentUser();

		System.out.println(user.getEmail() + " " + user.getPassword());

		return "Success";
	}

	@GetMapping("/4/{test}")
	@PreAuthorize("hasAuthority('FanZoneAdmin ' + #test)")
	public String getTest4(@PathVariable int test) {
		return "Success";
	}

}
