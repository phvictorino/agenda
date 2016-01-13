package br.com.agenda.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.agenda.entidade.Perfil;
import br.com.agenda.entidade.Vendedor;
import br.com.agenda.service.PerfilService;
import br.com.agenda.service.UsuarioService;
import br.com.agenda.service.VendedorService;
import br.com.agenda.utils.UtilsGeral;

@Controller
@ManagedBean
public class VendedorController {

	private Vendedor vendedor = new Vendedor();
	private List<Vendedor> vendedores;
	private String senhaAntiga;

	@Autowired
	private VendedorService vendedorService;

	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private UsuarioService usuarioService;

	@PostConstruct
	public void init() {
		this.vendedor = new Vendedor();
	}

	public void novo() {
		this.vendedor = new Vendedor();
		UtilsGeral.redirecionar("/vendedor/form.xhtml");
	}

	public void listar() {
		this.vendedores = vendedorService.listarTodos();
		UtilsGeral.redirecionar("/vendedor/listar.xhtml");
	}

	public void excluir() {
		if (this.vendedor != null) {
			
			this.usuarioService.remover(this.vendedor.getId());

			UtilsGeral.adicionarMsgInfo("Excluido com sucesso.");
			this.listar();
		}
	}

	public void editar() {

		this.senhaAntiga = this.vendedor.getSenha();

		UtilsGeral.redirecionar("/vendedor/form.xhtml");

	}

	public void salvar() {
		if (validaForm()) {

			Perfil perfil = perfilService.buscarPorNome("ROLE_VENDEDOR");

			if (perfil != null) {
				this.vendedor.setPerfil(perfil);
			}

			Vendedor vendedorSalvo = vendedorService.salvar(vendedor);

			if (vendedorSalvo != null) {
				UtilsGeral.adicionarMsgInfo("Vendedor salvo com sucesso.");
				this.listar();
			} else {
				UtilsGeral.adicionarMsgErro("Erro ao salvar. Tente novamente.");
			}

		}
	}

	private boolean validaForm() {

		if (this.vendedor == null) {
			this.vendedor = new Vendedor();
		}

		if (this.vendedor.getNome() == null || this.vendedor.getNome().isEmpty()) {
			UtilsGeral.adicionarMsgErro("Nome não preenchido.");
			return false;
		}

		if (this.vendedor.getSenha() == null || this.vendedor.getSenha().isEmpty()) {
			this.vendedor.setSenha(this.senhaAntiga);
		}

		return true;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public List<Vendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}

}
