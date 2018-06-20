package rs.ftn.xws.booking.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ftn.xws.booking.persistence.domain.Accomodation;

@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation, Long> {

	List<Accomodation> findByCapacityGreaterThanEqualAndCityAndCountryAllIgnoringCase(int capacity, String city, String country);
}
