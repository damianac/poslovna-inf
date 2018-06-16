package agentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agentapp.domain.Term;


@Repository
public interface TermRepository extends JpaRepository<Term,Long>{

}
