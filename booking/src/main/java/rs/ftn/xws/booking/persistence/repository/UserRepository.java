package rs.ftn.xws.booking.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rs.ftn.xws.booking.persistence.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@EntityGraph(value = "User.Roles.Permissions")
	Optional<User> findByEmail(String email);

	boolean existsByEmail(String username);

	@Query("select u from User u where u.id = ?#{principal.id}")
	User findCurrentUser();

}
