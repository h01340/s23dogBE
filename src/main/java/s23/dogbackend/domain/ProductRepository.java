package s23.dogbackend.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Long> {

	List<Product> findByName(String name);

	List<Product> findBySize(String size);

	List<Product> findByManufacturerId(Long id);

	@Query("SELECT p FROM Product p WHERE p.name= :searchString OR p.color= :searchString")
	List<Product> findByFilter(@Param("searchString") String searchString);

}
