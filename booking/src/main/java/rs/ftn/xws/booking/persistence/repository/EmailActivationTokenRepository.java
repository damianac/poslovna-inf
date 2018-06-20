package rs.ftn.xws.booking.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ftn.xws.booking.persistence.domain.EmailActivationToken;


@Repository
public interface EmailActivationTokenRepository extends JpaRepository<EmailActivationToken, Long> {

	EmailActivationToken findByToken(String token);
	
}
