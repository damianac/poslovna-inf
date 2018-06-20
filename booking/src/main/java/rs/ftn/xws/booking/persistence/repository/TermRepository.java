package rs.ftn.xws.booking.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rs.ftn.xws.booking.persistence.domain.Term;

@Repository
public interface TermRepository extends JpaRepository<Term,Long>{

	Optional<Term> findByIdAndReservedFalse(Long id);
	
	@Query("select t from Term t where t.user.id = ?#{principal.id}")
	List<Term> findCurrentUserReservations();
	
	@Query("select t from Term t where t.id = :id and t.user.id = ?#{principal.id}")
	Term findByIdCurrentUser(@Param("id") Long id);
	
}
