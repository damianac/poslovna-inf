package rs.ftn.xws.booking.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ftn.xws.booking.persistence.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{

	boolean existsByCategory(String category);
	
}
