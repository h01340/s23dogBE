package s23.dogbackend.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.validation.Valid;
import s23.dogbackend.domain.ManufacturerRepository;
import s23.dogbackend.domain.Product;
import s23.dogbackend.domain.ProductRepository;
import s23.dogbackend.domain.TypeRepository;

@Controller
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	
	@Autowired
	private TypeRepository typerepository;
	// login page
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@GetMapping("/productlist")
	public String productlist(Model model) {
		List<Product> products = (List<Product>) productRepository.findAll();
		model.addAttribute("products", products);
		return "productlist";
	}

	@GetMapping("/deleteproduct/{id}")
	public String deleteProduct(@PathVariable("id") Long productId) {
		productRepository.deleteById(productId);

		return "redirect:/productlist";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editProduct(@PathVariable("id") Long productId, Model model) {
		model.addAttribute("product", productRepository.findById(productId));
		model.addAttribute("manufacturers", manufacturerRepository.findAll());
		model.addAttribute("types", typerepository.findAll());

		return "editproduct";
	}

	@RequestMapping(value = "/add")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("manufacturers", manufacturerRepository.findAll());
		model.addAttribute("types", typerepository.findAll());

		return "addProduct";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("There has been an error, please recheck the error message.");
			model.addAttribute("manufacturers", manufacturerRepository.findAll());
			model.addAttribute("types", typerepository.findAll());

			return "addProduct";
		}
		productRepository.save(product);
		return "redirect:productlist";
	}

	// TODO: test this method
	@GetMapping("/search/{searchString}")
	public String productByFilter(@PathVariable String searchString, Model model) {
		log.info("lets search some products " + searchString);
		List<Product> products = (List<Product>) productRepository.findByFilter(searchString);
		log.info("products now " + products);
		model.addAttribute("products", products);
		return "productlist";
	}

}
