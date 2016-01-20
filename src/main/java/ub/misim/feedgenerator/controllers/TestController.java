package ub.misim.feedgenerator.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping(path = "/ping")
	@ResponseBody
	public String ping() {
		return "All is well!";
	}

}
