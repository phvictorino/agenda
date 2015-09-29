package br.com.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.agenda.entidade.Cidade;
import br.com.agenda.entidade.Estado;
import br.com.agenda.service.CidadeService;
import br.com.agenda.service.EstadoService;

@Controller(value = "cidadeController")
public class CidadeController {

	private Cidade cidade;

	private String nomeCidade;
	private Long idEstadoSelecionado;

	@Autowired
	CidadeService cidadeService;

	@Autowired
	EstadoService estadoService;
	
	public List<Cidade> getListaCidades() {
		return cidadeService.listarTodos();
	}
	

	public List<Estado> getListaEstados() {

		return estadoService.listarTodos();
	}

	public void salvar() {

		cidade = new Cidade();
		
		Estado estadoSelecionado = estadoService.buscarPorId(idEstadoSelecionado);
		
		cidade.setEstado(estadoSelecionado);
		cidade.setNome(nomeCidade);

		cidadeService.salvar(cidade);

		System.out.println("Sucesso!");
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public Long getIdEstadoSelecionado() {
		return idEstadoSelecionado;
	}

	public void setIdEstadoSelecionado(Long idEstadoSelecionado) {
		this.idEstadoSelecionado = idEstadoSelecionado;
	}


}
