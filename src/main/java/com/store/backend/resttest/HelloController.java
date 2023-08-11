package com.store.backend.resttest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.backend.api.security.UserPrincipal;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HelloController {
	
	@GetMapping("/") 
	public String hello() {
		return "hello world";
	}
	
	@GetMapping("/secured")
	public String secured(@AuthenticationPrincipal UserPrincipal principal) {
		return "if you see this, you're logged in as " + principal.getUsername();
	}

}
