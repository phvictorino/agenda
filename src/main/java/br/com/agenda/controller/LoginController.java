package br.com.agenda.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import br.com.agenda.dao.UsuarioDAO;
import br.com.agenda.entidade.Usuario;

@ManagedBean
@Controller
public class LoginController {

	@Autowired
	UsuarioDAO usuarioDAO;

	private Usuario usuarioLogado;
	private String login;

	public boolean isErro() {
		String valor = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("erro");
		if (valor != null)
			return (valor.contains("true"));
		else
			return false;

	}

	public void carregaUsuarioLogado() {
		this.login = SecurityContextHolder.getContext().getAuthentication().getName();

		if (usuarioLogado == null) {
			this.usuarioLogado = usuarioDAO.buscarPorLogin(login);
		}
	}

	public boolean estaUsuarioLogado() {
		carregaUsuarioLogado();
		return (usuarioLogado == null ? false : true);
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
