package s23.dogbackend.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import s23.dogbackend.domain.Manufacturer;
import s23.dogbackend.domain.ManufacturerRepository;
import s23.dogbackend.domain.Product;
import s23.dogbackend.domain.ProductRepository;

@Controller
public class ManufacturerController {
	private static final Logger log = LoggerFactory.getLogger(ManufacturerController.class);

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	@Autowired
	private ProductRepository productRepository;

	@GetMapping(value = "/manufacturerlist")
	public String manufacturerList(Model model) {
		model.addAttribute("manufacturers", manufacturerRepository.findAll());
		return "manufacturerlist";
	}

	@GetMapping(value = "/manufacturerProducts/{id}")
	public String manufacturerProducts(@PathVariable("id") Long id, Model model) {
		log.info("manus tuotteet: " + productRepository.findByManufacturerId(id));
		model.addAttribute("products", productRepository.findByManufacturerId(id));
		return "productlist";
	}

	@GetMapping(value = "/addmanufacturer")
	public String addManufacturer(Model model) {
		model.addAttribute("manufacturer", new Manufacturer());
		return "addManufacturer";
	}

	@PostMapping(value = "/savemanufacturer")
	public String saveManufacturer(@Valid @ModelAttribute("manufacturer") Manufacturer manufacturer,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("Validation error has happened, please recheck the name.");
			return "addManufacturer";
		}
		manufacturerRepository.save(manufacturer);
		return "redirect:manufacturerlist";
	}

	@GetMapping("/deletemanufacturer/{id}")
	public String deleteProduct(@PathVariable("id") Long manufacturerId) {
		log.info("delete manufacturer " + manufacturerId);
		// Cancel if manufacturer has products
		for (Product product : productRepository.findAll()) {
			if (product.getManufacturer().getId().equals(manufacturerId)) {
				return "redirect:/manufacturerlist?cannotdelete";
			}
		}

		manufacturerRepository.deleteById(manufacturerId);
		return "redirect:/manufacturerlist";
	}

}