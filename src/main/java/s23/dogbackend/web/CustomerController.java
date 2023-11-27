package s23.dogbackend.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import s23.dogbackend.domain.Customer;
import s23.dogbackend.domain.CustomerRepository;

@Controller
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("/customerlist")
	public String customerlist(Model model) {
		List<Customer> customers = (List<Customer>) customerRepository.findAll();
		model.addAttribute("customers", customers);
		return "customerlist";
	}


}
