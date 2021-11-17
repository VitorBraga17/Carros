package com.example.carros.api.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
	
	@GetMapping
	public String hello() {
		return "API dos Carros";
	}

	@GetMapping("/userInfo")
	public UserDetails userInfo(@AuthenticationPrincipal UserDetails user){
		return user;
	}

}


/*
@GetMapping("/login")
public String login(@RequestParam("login") String login,@RequestParam("senha")  String senha) {
	return "Login " + login + ", senha: " + senha;
}*/

/*
@GetMapping("login/{login}/senha/{senha}")
public String login(@PathVariable("login") String login,@PathVariable("senha")  String senha) {
	return ""
			+ ""
			+ "Login " + login + ", senha: " + senha;
}

@GetMapping("/carros/{id}")
public String getCarroById(@PathVariable("id") Long id) {
	return "Carro by id " + id;
}

@GetMapping("/carros/tipo/{tipo}")
public String getCarroById(@PathVariable("tipo") String tipo) {
	return "Lista de Carros " + tipo;
}*/

/*
@PostMapping("/login")
public String login(@RequestParam("login") String login,@RequestParam("senha")  String senha) {
	return "Login " + login + ", senha: " + senha;
}*/