package agentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agentapp.domain.AccomodationType;


@Repository
public interface AccomodationTypeRepository extends JpaRepository<AccomodationType,Long>{

}
