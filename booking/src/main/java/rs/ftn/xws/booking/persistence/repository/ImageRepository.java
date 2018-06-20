package rs.ftn.xws.booking.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ftn.xws.booking.persistence.domain.AccomodationImage;

@Repository
public interface ImageRepository extends JpaRepository<AccomodationImage, Long> {

}
