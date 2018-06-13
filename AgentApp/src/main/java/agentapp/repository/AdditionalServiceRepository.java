package agentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agentapp.domain.AdditionalService;


@Repository
public interface AdditionalServiceRepository extends JpaRepository<AdditionalService,Long>{

}
