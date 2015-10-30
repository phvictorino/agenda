package br.com.agenda.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.agenda.entidade.Estado;
import br.com.agenda.service.EstadoService;
import br.com.agenda.utils.UtilsGeral;

@Controller
@ManagedBean
public class EstadoController {

	private Estado estado = new Estado();

	@Autowired
	EstadoService estadoService;
	
	public void listar() {
		UtilsGeral.redirecionar("/estado/listar.xhtml");
	}

	public void novo() {
		estado = new Estado();
		UtilsGeral.redirecionar("/estado/form.xhtml");
	}

	public void salvar() {
		estadoService.salvar(estado);

		UtilsGeral.adicionarMsgInfo("Estado salvo com sucesso.");

		UtilsGeral.redirecionar("/estado/listar.xhtml");
	}

	public void editar() {
		UtilsGeral.redirecionar("/estado/form.xhtml");
	}

	public void excluir() {
		try {
			estadoService.deletar(estado.getId());
			UtilsGeral.adicionarMsgInfo("Estado removido com sucesso.");
		} catch (Exception e) {
			UtilsGeral.adicionarMsgErro("Estado vincula à cidade. Impossível excluir.");
		}

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
