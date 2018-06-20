package rs.ftn.xws.booking.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ftn.xws.booking.dto.ApiResponse;
import rs.ftn.xws.booking.dto.JwtAuthenticationResponse;
import rs.ftn.xws.booking.dto.LoginRequest;
import rs.ftn.xws.booking.dto.PasswordChangeRequest;
import rs.ftn.xws.booking.dto.PasswordResetRequest;
import rs.ftn.xws.booking.dto.ProfileDto;
import rs.ftn.xws.booking.dto.SignUpRequest;
import rs.ftn.xws.booking.events.AccountCreatedEvent;
import rs.ftn.xws.booking.events.PasswordResetEvent;
import rs.ftn.xws.booking.exception.InvalidToken;
import rs.ftn.xws.booking.persistence.domain.User;
import rs.ftn.xws.booking.security.JwtTokenProvider;
import rs.ftn.xws.booking.service.DomainUserDetailsService;
import rs.ftn.xws.booking.service.TokenService;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    private DomainUserDetailsService domainUserDetailsService;
    
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    @Autowired
    private TokenService tokenService;
    
    @Value("${app.accountEnabledUrl}")
    private String redirectUrl;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    	try {
    		Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	                loginRequest.getEmail(),
	                loginRequest.getPassword()
	            )
	        );
    		
    		String email = authentication.getName();
    		List<String> authorities = authentication.getAuthorities().stream()
    				.map(GrantedAuthority::getAuthority)
    				.collect(Collectors.toList());
    		
	        return ResponseEntity.ok(new JwtAuthenticationResponse(
	        	new ProfileDto(email, authorities),
	    		tokenProvider.generateToken(authentication)
			));
		} catch (AuthenticationException e) {
			logger.error("Authentication error: {}", e.getMessage());
			
			return ResponseEntity.badRequest()
					.body(new ApiResponse(false, e.getMessage()));
		}
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    	User user = domainUserDetailsService.registerUser(signUpRequest);
    	
    	if (user != null) {
    		String token = tokenService.generateEmailActivationToken(user);  		
    		eventPublisher.publishEvent(new AccountCreatedEvent(user.getEmail(), token));  		
    		return ResponseEntity.ok(new ApiResponse(true, "Account created! Confrimation email sent."));
    	} else {
    		return ResponseEntity.badRequest().body(new ApiResponse(false, "Email already exists!"));
    	}
    }
    
    @GetMapping("/confirm-email")
    public void confirmEmail(@RequestParam(name = "token", required = true) String token, HttpServletResponse response) throws IOException {	
    	try {
    		User user = tokenService.validateEmailConfirmationToken(token);
    		domainUserDetailsService.activateUser(user);
    		logger.info("Activation user account: " + user.getEmail());
    		response.sendRedirect(redirectUrl);
		} catch (InvalidToken e) {
			logger.debug("Invalid activation token: {}", e.getMessage());
			response.sendRedirect("/register.html");
		}
    }
    
    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> forgotPassword(@RequestBody String email) {
    	User user = domainUserDetailsService.findByEmail(email);
    	
    	if (user == null) {
    		return ResponseEntity.badRequest()
    				.body(new ApiResponse(false, "User doesn't exists with the given email!"));
    	}
    	
    	eventPublisher.publishEvent(new PasswordResetEvent(tokenService.generatePasswordResetToken(user)));
    	
    	return ResponseEntity.ok(new ApiResponse(true, "Password reset email has been sent!"));
    }
    
    @PostMapping("/password-reset")
    public ResponseEntity<ApiResponse> resetPassword(@RequestBody @Valid PasswordResetRequest passwordReset) {
    	try { 		
    		User user = tokenService.validatePasswordResetToken(passwordReset.getTokenId());
    		domainUserDetailsService.changePassword(user, passwordReset.getNewPassword());
    		return ResponseEntity.ok(new ApiResponse(true, "Password successfuly reseted!"));
    	} catch (InvalidToken e) {
			logger.error("ResetTokenException {}", e.getMessage());
			return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid request!"));
		}
    }
    
    @PostMapping("/password-change")
    public ResponseEntity<ApiResponse> changePassword(@RequestBody @Valid PasswordChangeRequest passwordChange){
  		
    		User user = domainUserDetailsService.findCurrentUser();
    		if(user != null){
	    		domainUserDetailsService.changePassword(user, passwordChange.getNewPassword());
	    		return ResponseEntity.ok(new ApiResponse(true, "Password successfuly reseted!"));
    		}else{
    			return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid request!"));
    		}
    	
    }
    
}
