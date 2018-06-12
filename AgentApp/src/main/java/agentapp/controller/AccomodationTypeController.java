package agentapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agentapp.domain.AccomodationType;
import agentapp.service.AccomodationTypeService;

@RestController
@RequestMapping("/accomodationTypes")
public class AccomodationTypeController {
	
	
	@Autowired
	private AccomodationTypeService accTypeService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public List<AccomodationType> getAll() throws Exception {
		return accTypeService.getAllTypes();
	}

}
