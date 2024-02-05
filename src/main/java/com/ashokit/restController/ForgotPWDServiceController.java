package com.ashokit.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.service.ForgotService;

@RestController
public class ForgotPWDServiceController {
	
	@Autowired
	private ForgotService forgotService;
	
	
   @PostMapping("/forgetPWD")
	public String forgotPass(@RequestParam String userEmail) {
		return forgotService.getEmail(userEmail);
	}
	
}
