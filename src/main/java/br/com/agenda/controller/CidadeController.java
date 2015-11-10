package br.com.agenda.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.agenda.entidade.Cidade;
import br.com.agenda.entidade.Estado;
import br.com.agenda.entidade.Rota;
import br.com.agenda.service.CidadeService;
import br.com.agenda.service.EstadoService;
import br.com.agenda.service.RotaService;
import br.com.agenda.utils.UtilsGeral;

@Controller
@ManagedBean
public class CidadeController {

	private Cidade cidade;

	private String nomeCidade;
	private Long idEstadoSelecionado;
	private Long idRotaSelecionada;
	private List<Rota> rotas;

	@Autowired
	CidadeService cidadeService;

	@Autowired
	EstadoService estadoService;
	
	@Autowired
	RotaService rotaService;

	@PostConstruct
	public void init() {
		cidade = new Cidade();
		this.rotas = rotaService.listarTodos();
	}
	
	public void listar() {
		UtilsGeral.redirecionar("/cidade/listar.xhtml");
	}

	public void novo() {
		this.nomeCidade = "";
		cidade = new Cidade();
		this.rotas = rotaService.listarTodos();
		UtilsGeral.redirecionar("/cidade/form.xhtml");
	}

	public void editar() {
		nomeCidade = cidade.getNome();
		this.rotas = rotaService.listarTodos();
		
		if (cidade.getRota() != null) {
			idRotaSelecionada = cidade.getRota().getId();
		} else {
			idRotaSelecionada = null;
		}
		
		if (cidade.getEstado().getId() != null) {
			idEstadoSelecionado = cidade.getEstado().getId();
		} else {
			idEstadoSelecionado = null;
		}
		
		UtilsGeral.redirecionar("/cidade/form.xhtml");
	}

	public void excluir() {

		try {
			cidadeService.remover(cidade.getId());
			UtilsGeral.adicionarMsgInfo("Cidade removida.");
		} catch (Exception e) {
			UtilsGeral.adicionarMsgErro("Cidade vinculada à cliente. Impossível excluir.");
		}

	}

	public List<Cidade> getListaCidades() {
		return cidadeService.listarTodos();
	}

	public List<Estado> getListaEstados() {

		return estadoService.listarTodos();
	}

	public void salvar() {

		Estado estadoSelecionado;
		Rota rotaSelecionada;
		
		if (idRotaSelecionada != null) {
			rotaSelecionada = rotaService.buscarPorId(idRotaSelecionada);
		} else {
			rotaSelecionada = null;
		}

		if (idEstadoSelecionado != null) {
			estadoSelecionado = estadoService.buscarPorId(idEstadoSelecionado);
		} else {
			estadoSelecionado = null;
		}
		
		cidade.setRota(rotaSelecionada);
		cidade.setEstado(estadoSelecionado);
		cidade.setNome(nomeCidade);

		cidadeService.salvar(cidade);

		UtilsGeral.adicionarMsgInfo("Cidade salva com sucesso!");

		UtilsGeral.redirecionar("/cidade/listar.xhtml");
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

	public List<Rota> getRotas() {
		return rotas;
	}

	public void setRotas(List<Rota> rotas) {
		this.rotas = rotas;
	}

	public Long getIdRotaSelecionada() {
		return idRotaSelecionada;
	}

	public void setIdRotaSelecionada(Long idRotaSelecionada) {
		this.idRotaSelecionada = idRotaSelecionada;
	}

}
