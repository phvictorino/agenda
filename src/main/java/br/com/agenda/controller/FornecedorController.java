package br.com.agenda.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.agenda.entidade.Fornecedor;
import br.com.agenda.entidade.Perfil;
import br.com.agenda.service.FornecedorService;
import br.com.agenda.service.PerfilService;
import br.com.agenda.service.UsuarioService;
import br.com.agenda.utils.UtilsGeral;

@Controller
@ManagedBean
public class FornecedorController {

	private Fornecedor fornecedor;
	private List<Fornecedor> fornecedores;
	private String senhaAntiga;

	@Autowired
	private FornecedorService fornecedorService;

	@Autowired
	private PerfilService perfilService;

	@Autowired
	private UsuarioService usuarioService;

	public void init() {
		this.fornecedor = new Fornecedor();
	}

	public void novo() {
		this.fornecedor = new Fornecedor();
		UtilsGeral.redirecionar("/fornecedor/form.xhtml");
	}

	public void editar() {
		this.senhaAntiga = this.fornecedor.getSenha();
		UtilsGeral.redirecionar("/fornecedor/form.xhtml");
	}

	public void excluir() {
		if (this.fornecedor != null) {

			usuarioService.remover(this.fornecedor.getId());

//			fornecedorService.excluir(this.fornecedor.getId());

			UtilsGeral.adicionarMsgInfo("Excluído com sucesso");
			this.listar();
		}
	}

	public void listar() {
		this.fornecedores = fornecedorService.listarTodos();
		UtilsGeral.redirecionar("/fornecedor/listar.xhtml");
	}

	public void salvar() {
		if (validaForm()) {

			Perfil perfil = perfilService.buscarPorNome("ROLE_FORNECEDOR");

			if (perfil != null) {
				this.fornecedor.setPerfil(perfil);
			}

			Fornecedor fornecedorSalvo = this.fornecedorService.salvar(this.fornecedor);

			if (fornecedorSalvo != null) {
				UtilsGeral.adicionarMsgInfo("Fornecedor salvo com sucesso.");
				this.listar();
			} else {
				UtilsGeral.adicionarMsgErro("Erro ao salvar. Tente novamente.");
			}

		}
	}

	private boolean validaForm() {

		if (this.fornecedor == null) {
			this.fornecedor = new Fornecedor();
		}

		if (this.fornecedor.getNome() == null || this.fornecedor.getNome().isEmpty()) {
			UtilsGeral.adicionarMsgErro("Nome não preenchido.");
			return false;
		}

		if (this.fornecedor.getSenha() == null || this.fornecedor.getSenha().isEmpty()) {
			this.fornecedor.setSenha(this.senhaAntiga);
		}

		return true;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

}
