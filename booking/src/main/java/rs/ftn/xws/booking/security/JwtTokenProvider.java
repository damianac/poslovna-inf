package rs.ftn.xws.booking.security;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import rs.ftn.xws.booking.exception.InvalidJWTokenException;

@Component
public class JwtTokenProvider {

	@Value("${app.jwtSecret}")
	private String jwtSecret;

	@Value("${app.jwtExpirationInMs}")
	private int jwtExpirationInMs;

	public String generateToken(Authentication authentication) {
		UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

		Map<String, Object> additionalInfo = new HashMap<>();
		additionalInfo.put("username", userDetails.getUsername());
		additionalInfo.put("authorities", userDetails.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.toList()));
		
		return Jwts.builder()
			.setClaims(additionalInfo)
			.setSubject(userDetails.getId())
			.setIssuedAt(new Date())
			.setExpiration(expiryDate)
			.setHeaderParam("typ", "JWT")
			.signWith(SignatureAlgorithm.HS512, jwtSecret)
			.compact();
	}

	public UserPrincipal getUserPrincipal(String jwt) throws InvalidJWTokenException {
		try {
			Claims claims = getClaimsFromToken(jwt);
			
			return new UserPrincipal(
				getIdFromClaims(claims), 
				getUsernameFromClaims(claims), 
				true,
				getGrantedAuthoritiesFromClaims(claims)
			);
		} catch (Exception e) {
			throw new InvalidJWTokenException(e);
		}		
	}
	
	private Claims getClaimsFromToken(String authToken) throws Exception {
		return Jwts.parser()
			.setSigningKey(jwtSecret)
			.parseClaimsJws(authToken)
			.getBody();
	}
	
	private String getIdFromClaims(Claims claims) {
		return claims.getSubject();		
	}
	
	private String getUsernameFromClaims(Claims claims) {
		return (String) claims.get("username");
	}
	
	@SuppressWarnings("unchecked")
	private List<GrantedAuthority> getGrantedAuthoritiesFromClaims(Claims claims) {
		List<String> authorities = (List<String>) claims.get("authorities");			
		return authorities.stream()
			.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toList());
	}
}
