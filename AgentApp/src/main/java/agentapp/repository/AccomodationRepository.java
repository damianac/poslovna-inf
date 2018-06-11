package agentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agentapp.domain.Accomodation;


@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation,Long>{

}
