package s23.dogbackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import s23.dogbackend.domain.Manufacturer;
import s23.dogbackend.domain.ManufacturerRepository;
import s23.dogbackend.domain.Product;
import s23.dogbackend.domain.ProductRepository;
import s23.dogbackend.domain.Type;
import s23.dogbackend.domain.TypeRepository;


@SpringBootApplication
public class DogbackendApplication {
	private static final Logger log = LoggerFactory.getLogger(SpringBootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DogbackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(ProductRepository pRepository, ManufacturerRepository mRepository,
			TypeRepository tRepository) {
		return (args) -> {

			log.info("Lets create some test data to H2 db");
			
			log.info("...inserting manufacturers...");
			// public Owner(String firstName, String lastName, String city, String ssn, int
			// yob)
			Manufacturer manu1 = new Manufacturer("Aku Ankka");
			Manufacturer manu2 = new Manufacturer("Iines Ankka");
			Manufacturer manu3 = new Manufacturer("Hannu Hanhi");
			mRepository.save(manu1);
			mRepository.save(manu2);
			mRepository.save(manu3);

			log.info("...inserting product types ....");
			Type type1 = new Type("toy");
			Type type2 = new Type("food");
			Type type3 = new Type("clothing");
			tRepository.save(type1);
			tRepository.save(type2);
			tRepository.save(type3);


			log.info("...inserting products ....");

			pRepository.save(new Product("Winter Coat", type3, "Black", "-", 10, 55, manu3));
			pRepository.save(new Product("Scarf", type3, "Red", "-", 11, 15, manu3));
			pRepository.save(new Product("ball", type1, "Black", "-", 10, 55, manu1));
			pRepository.save(new Product("bone", type2, "Brown", "XL", 221, 2, manu2));

			
			log.info("Print products autot");
			for (Product product: pRepository.findAll()) {
				log.info(product.toString());
			}

		};
	}


}
