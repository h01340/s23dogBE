package s23.dogbackend.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	@GetMapping(value = { "*", "/", "main", "index" })
	public String showMainPage() {
		return "main";

	}

}
