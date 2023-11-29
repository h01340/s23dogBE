package s23.dogbackend.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import s23.dogbackend.domain.CustomerRepository;
import s23.dogbackend.domain.Manufacturer;
import s23.dogbackend.domain.ManufacturerRepository;
import s23.dogbackend.domain.Product;
import s23.dogbackend.domain.ProductRepository;
import s23.dogbackend.domain.Type;
import s23.dogbackend.domain.TypeRepository;

@Controller
public class AppController {
	private static final Logger log = LoggerFactory.getLogger(AppController.class);

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private TypeRepository typeRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping(value = { "*", "/", "main", "index" })
	public String showMainPage() {
		return "main";

	}

	@GetMapping(value = { "resetDB" })
	public String resetDB() {
		log.info("remove first possible old data");

		productRepository.deleteAll();
		manufacturerRepository.deleteAll();
		typeRepository.deleteAll();


		log.info("insert manufacturers to db");
		 
		Manufacturer manu1 = new Manufacturer("Aku Ankka");
		Manufacturer manu2 = new Manufacturer("Iines Ankka"); 
		Manufacturer manu3 = new Manufacturer("Hannu Hanhi"); 
		manufacturerRepository.save(manu1);
		manufacturerRepository.save(manu3);
		manufacturerRepository.save(manu2);

		log.info("...inserting product types ...."); 
		Type type1 = new Type("toy");
		Type type2 = new Type("food"); 
		Type type3 = new Type("clothing");
		typeRepository.save(type1); 
		typeRepository.save(type2); 
		typeRepository.save(type3);

		log.info("...inserting products ....");

		productRepository.save(new Product("Winter Coat", type3, "Black", "-", 10, 55, manu3));
		productRepository.save(new Product("Scarf", type3, "Red", "-", 11, 15, manu3));
		productRepository.save(new Product("ball", type1, "Black", "-", 10, 55, manu1));
		productRepository.save(new Product("bone", type2, "Brown", "XL", 221, 2, manu2));

		log.info("TODO: inserting customers");

		return "main";

	}

}
