package br.com.agenda.controller;

import java.util.List;

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
	
	public List<TipoContato> getListaTiposContato() {
		return tipoContatoService.listarTodos();
	}
	
	public void salvar() {
		
		tipoContato = new TipoContato();
		
		tipoContato.setTipo(nomeTipoContato);
		
		tipoContatoService.salvar(tipoContato);
		
		System.out.println("Sucesso");
		
	}

	public String getNomeTipoContato() {
		return nomeTipoContato;
	}

	public void setNomeTipoContato(String nomeTipoContato) {
		this.nomeTipoContato = nomeTipoContato;
	}
	
	

}
