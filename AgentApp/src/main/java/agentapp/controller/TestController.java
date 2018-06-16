package agentapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ftn.xws.booking.test.TestServiceSoap;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestServiceSoap testService;

	@GetMapping
	public String test() throws Exception {
		return testService.testMethod();
	}

}