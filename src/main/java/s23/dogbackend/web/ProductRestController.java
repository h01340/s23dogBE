package s23.dogbackend.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import s23.dogbackend.domain.Product;
import s23.dogbackend.domain.ProductRepository;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "https://h01340.github.io/s23dogapp/")
@RestController
public class ProductRestController {
	@Autowired
	private ProductRepository productRepository;
//	@Autowired
//	private ManufacturerRepository manufacturerRepository;
//	@Autowired
//	private TypeRepository typeRepository;

	// Return products depending on URL parameters
	@GetMapping("/rest/products")
	public List<Product> getProducts() {
		// Return all products if no URL parameters specified
		return (List<Product>) productRepository.findAll();
	}

}
