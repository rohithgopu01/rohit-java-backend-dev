package com.amazon.backend.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rootcontroller {

	@GetMapping("/")
	public String getMethodName() {
		return "Welcome to backend amazon Apis project ";
	}
	@GetMapping("login")
	public String login() {
		return "Login api";
	}
}
