package ca.sheridancollege.smartwaste.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String goHome() {
		return "index.html";
	}

	@GetMapping("/view")
	public String goView() {
		return "index.html";
	}	
}
