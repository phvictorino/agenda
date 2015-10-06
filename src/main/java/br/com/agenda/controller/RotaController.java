package br.com.agenda.controller;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.agenda.entidade.Rota;
import br.com.agenda.service.RotaService;

@Controller
@ManagedBean
public class RotaController {
	
	Rota rota = new Rota();
	
	@Autowired
	RotaService rotaService;
	
	

}
