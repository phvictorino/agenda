package br.com.agenda.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.agenda.entidade.Perfil;
import br.com.agenda.entidade.Usuario;
import br.com.agenda.entidade.Vendedor;
import br.com.agenda.service.PerfilService;
import br.com.agenda.service.UsuarioService;
import br.com.agenda.service.VendedorService;
import br.com.agenda.utils.UtilsGeral;

@Controller
@ManagedBean
public class UsuarioController {

	private Usuario usuario;
	private List<Perfil> perfis;
	private Long idPerfilSelecionado;
	private Perfil perfilSelecionado;
	private List<Usuario> usuarios;

	private Long idVendedorSelecionado;
	private Vendedor vendedorSelecinado;
	private List<Vendedor> vendedores;
	private String senhaAntiga;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PerfilService perfilService;

	@Autowired
	private LoginController loginController;

	@Autowired
	private VendedorService vendedorService;

	@PostConstruct
	public void init() {
		this.perfis = perfilService.listarTodos();
	}

	public void novo() {
		this.usuario = new Usuario();
		this.perfis = perfilService.listarTodos();
		UtilsGeral.redirecionar("/admin/usuario/form.xhtml");
	}

	public void listar() {
		this.usuarios = usuarioService.listarTodos();
		UtilsGeral.redirecionar("/admin/usuario/listar.xhtml");
	}

	public void salvar() {

		if (validaFormulario()) {

			this.usuario.setAtivo(true);

			Usuario usuarioSalvo = usuarioService.salvar(usuario);

			if (usuarioSalvo == null) {
				UtilsGeral.adicionarMsgErro("Erro ao salvar usuário. Tente novamente.");
			} else {
				UtilsGeral.adicionarMsgInfo("Usuário salvo com sucesso.");
				this.listar();
			}
		}

	}

	private boolean validaFormulario() {

		if (this.usuario.getEmail() == null || this.usuario.getEmail().isEmpty()) {
			UtilsGeral.adicionarMsgErro("Email não preenchido.");
			return false;
		}

		if (this.usuario.getLogin() == null || this.usuario.getLogin().isEmpty()) {
			UtilsGeral.adicionarMsgErro("Login não preenchido.");
			return false;
		}

		if (this.usuario.getNome() == null || this.usuario.getNome().isEmpty()) {
			UtilsGeral.adicionarMsgErro("Nome não preenchido.");
			return false;
		}

		if (this.usuario.getSenha() == null || this.usuario.getSenha().isEmpty()) {
			this.usuario.setSenha(senhaAntiga);
		}

		if (idPerfilSelecionado != null) {
			this.perfilSelecionado = perfilService.buscarPorId(idPerfilSelecionado);
			this.usuario.setPerfil(perfilSelecionado);
		} else {
			UtilsGeral.adicionarMsgErro("Nenhum perfil selecionado.");
			return false;
		}

		if (idVendedorSelecionado != null) {
			this.vendedorSelecinado = vendedorService.buscarPorId(idVendedorSelecionado);
			this.usuario.setVendedor(vendedorSelecinado);
		}

		return true;
	}

	public void editar() {

		senhaAntiga = usuario.getSenha();

		if (this.usuario.getVendedor() != null) {
			this.idVendedorSelecionado = this.usuario.getVendedor().getId();
		}

		this.idPerfilSelecionado = this.usuario.getPerfil().getId();

		UtilsGeral.redirecionar("/admin/usuario/form.xhtml");
	}

	public void excluir() {

		if (this.usuario != null) {
			if (validaExcluir()) {
				usuarioService.remover(this.usuario.getId());
				UtilsGeral.adicionarMsgInfo("Usuário removido com sucesso.");
				this.listar();
			}
		}

	}

	private boolean validaExcluir() {

		if (loginController.getUsuarioLogado().equals(usuario)) {
			UtilsGeral.adicionarMsgErro("Você não pode apagar o usuário que está usando no momento.");
			return false;
		}

		return true;
	}

	// ---- getters and setters

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Long getIdPerfilSelecionado() {
		return idPerfilSelecionado;
	}

	public void setIdPerfilSelecionado(Long idPerfilSelecionado) {
		this.idPerfilSelecionado = idPerfilSelecionado;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Long getIdVendedorSelecionado() {
		return idVendedorSelecionado;
	}

	public void setIdVendedorSelecionado(Long idVendedorSelecionado) {
		this.idVendedorSelecionado = idVendedorSelecionado;
	}

	public List<Vendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}

}
