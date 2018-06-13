package agentapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agentapp.domain.AdditionalService;
import agentapp.service.AdditionalServiceService;

@RestController
@RequestMapping("/additionalServices")
public class AdditionalServiceController {
	
	@Autowired
	private AdditionalServiceService addService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public List<AdditionalService> getAll() throws Exception {
		return addService.getAllServices();
	}
	
}
