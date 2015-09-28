package br.com.agenda.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.agenda.service.TipoContatoService;

@Controller
public class TipoContatoController {
	
	@Autowired
	TipoContatoService TipoContatoService;
	
	@PostConstruct
	public void init() {
		
	}
	
	

}
