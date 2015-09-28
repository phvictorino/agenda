package br.com.agenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.agenda.entidade.Cidade;
import br.com.agenda.service.CidadeService;

@Controller("cidadeController")
public class CidadeController {

	public Cidade cidade = new Cidade();

	@Autowired
	CidadeService service;

	public void salvar() {

		this.cidade = service.salvar(this.cidade);

		if (this.cidade != null) {
			System.out.println("Sucesso!");
		}

	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
}
