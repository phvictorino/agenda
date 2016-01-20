package br.com.agenda.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.agenda.entidade.Perfil;
import br.com.agenda.service.PerfilService;
import br.com.agenda.utils.UtilsGeral;

@Controller
@ManagedBean
public class PerfilController {

	private List<Perfil> perfis;
	private Perfil perfil;

	@Autowired
	PerfilService perfilService;

	public void listar() {
		perfis = perfilService.listarTodos();
		UtilsGeral.redirecionar("/admin/perfil/listar.xhtml");
	}

	public void novo() {
		perfil = new Perfil();
		UtilsGeral.redirecionar("/admin/perfil/form.xhtml");
	}

	public void editar(Perfil perfilSelecionado) {
		this.perfil = perfilSelecionado;
		UtilsGeral.redirecionar("/admin/perfil/form.xhtml");
	}

	public void exlcuir(Perfil perfilSelecionado) {

		Long idPerfil = perfilSelecionado.getId();

		perfilService.remover(idPerfil);

		if (perfilService.buscarPorId(idPerfil) == null) {
			UtilsGeral.adicionarMsgInfo("Perfil removido com sucesso.");
		} else {
			UtilsGeral.adicionarMsgErro("Erro ao remover perfil.");
		}
		
		UtilsGeral.redirecionar("/admin/perfil/listar.xhtml");
	}
	
	public void salvar() {
		
		if (perfilService.salvar(perfil) != null) 
			UtilsGeral.adicionarMsgInfo("Perfil adicionado com sucesso.");
		else
			UtilsGeral.adicionarMsgErro("Erro ao adicionar perfil. Tente novamente.");
		
		UtilsGeral.redirecionar("/admin/perfil/listar.xhtml");
		
	}
	
	// ---getters and setters---

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}
