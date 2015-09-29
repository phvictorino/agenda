package br.com.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.agenda.entidade.Estado;
import br.com.agenda.service.EstadoService;

@Controller
public class EstadoController {

	private Estado estado = new Estado();
	
	@Autowired
	EstadoService estadoService;
	
	public void salvar() {
		estadoService.salvar(estado);
		
		System.out.println("Sucesso!");
	}
	
	public List<Estado> getListaEstados() {
		return estadoService.listarTodos();
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
	
}
