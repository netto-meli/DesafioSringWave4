package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class DesafioSpring1Controller {

	@Autowired
	private DesafioSpring1Controller desafioService;
}
