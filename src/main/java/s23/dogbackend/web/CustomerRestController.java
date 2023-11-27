package s23.dogbackend.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import s23.dogbackend.domain.Customer;
import s23.dogbackend.domain.CustomerRepository;

@CrossOrigin(origins = "*")
@RestController
public class CustomerRestController {
	private static final Logger log = LoggerFactory.getLogger(CustomerRestController.class);

	@Autowired
	private CustomerRepository customerRepository;

	// Return products depending on URL parameters
	@PostMapping("/rest/customer")
	Customer registerNewCustomer(@RequestBody Customer newCustomer) {
		log.info("save new customer to db " + newCustomer);
		return customerRepository.save(newCustomer);
	}
}
