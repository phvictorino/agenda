package br.com.agenda.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.agenda.entidade.TipoContato;
import br.com.agenda.service.TipoContatoService;

@Controller
public class TipoContatoController {
	
	TipoContato tipoContato;
	
	private String nomeTipoContato;
	
	@Autowired
	TipoContatoService tipoContatoService;
	
	public void salvar() {
		
		tipoContato = new TipoContato();
		
		tipoContato.setTipo(nomeTipoContato);
		
		tipoContatoService.salvar(tipoContato);
		
	}

	public String getNomeTipoContato() {
		return nomeTipoContato;
	}

	public void setNomeTipoContato(String nomeTipoContato) {
		this.nomeTipoContato = nomeTipoContato;
	}
	
	

}
