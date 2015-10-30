package br.com.agenda.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mysql.jdbc.Util;

import br.com.agenda.entidade.Rota;
import br.com.agenda.service.RotaService;
import br.com.agenda.utils.UtilsGeral;

@Controller
@ManagedBean
public class RotaController {

	Rota rota = new Rota();
	List<Rota> rotas;

	@Autowired
	RotaService rotaService;

	public RotaController() {

	}

	@PostConstruct
	public void init() {
		rotas = rotaService.listarTodos();
	}
	
	public void listar() {
		UtilsGeral.redirecionar("/rota/listar.xhtml");
	}

	public void novo() {
		this.rota = new Rota();
		UtilsGeral.redirecionar("/rota/form.xhtml");
	}

	public void salvar() {
		rotaService.salvar(rota);

		this.rotas.add(rota);

		UtilsGeral.adicionarMsgInfo("Rota salva com sucesso.");

		UtilsGeral.redirecionar("/rota/listar.xhtml");
	}

	public List<Rota> getListaTodos() {
		return rotaService.listarTodos();
	}

	public void deletar() {

		try {
			rotaService.deletar(this.rota.getId());
			UtilsGeral.adicionarMsgInfo("Rota excluída com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			UtilsGeral.adicionarMsgInfo("Há cidades relacionadas nessa rota. Impossível excluir.");
		}


		UtilsGeral.redirecionar("/rota/listar.xhtml");
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public List<Rota> getRotas() {
		return rotas;
	}

	public void setRotas(List<Rota> rotas) {
		this.rotas = rotas;
	}

}
