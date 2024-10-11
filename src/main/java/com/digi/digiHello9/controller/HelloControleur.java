package com.digi.digiHello9.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digi.digiHello9.services.HelloService;

@RestController
@RequestMapping("/hello")
public class HelloControleur {
	@Autowired
	private HelloService helloService;
	
	@GetMapping
	 public String direHello(){
	 return  helloService.Salutations();
	 }

}
