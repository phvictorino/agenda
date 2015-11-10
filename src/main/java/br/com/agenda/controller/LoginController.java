package br.com.agenda.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Controller;

@ManagedBean
@Controller
public class LoginController {

	public boolean isErro() {
		String valor = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("erro");
		if (valor != null)
			return (valor.contains("true"));
		else
			return false;

	}

}
