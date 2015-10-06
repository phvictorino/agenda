package br.com.agenda.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class UtilsGeral {

	public static String converteDataEmString(Date date) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(date);
		
	}
	
	public static Date converteStringEmData(String data){
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    
	    try {
			return (Date)formatter.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}  
		
	}

	public static void redirecionar(String link) {
		try {
			manterMensagens();
			FacesContext.getCurrentInstance().getExternalContext().redirect(obterUrl() + link);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String obterUrl() {
		return obterExternalContext().getRequestContextPath();
	}

	public static void manterMensagens() {
		obterExternalContext().getFlash().setKeepMessages(true);
	}

	public static Map<String, Object> obterSessaoMap() {
		return FacesContext.getCurrentInstance() != null ? FacesContext.getCurrentInstance().getExternalContext().getSessionMap() : null;
	}

	public static ExternalContext obterExternalContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context != null ? context.getExternalContext() : null;
	}

	public static void adicionarMsg(String msgLonga, String msgCurta, FacesMessage.Severity severity) {
		FacesMessage msg = new FacesMessage(msgLonga, msgCurta);
		msg.setSeverity(severity);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public static void adicionarMsgInfo(String msg) {
		FacesMessage facesMsg = new FacesMessage("Informação", msg);
		facesMsg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public static void adicionarMsgErro(String msg) {
		FacesMessage facesMsg = new FacesMessage("Erro", msg);
		facesMsg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public static void adicionarMsgErroFatal(String msg) {
		FacesMessage facesMsg = new FacesMessage("Erro fatal", msg);
		facesMsg.setSeverity(FacesMessage.SEVERITY_FATAL);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public static void adicionarMsgAviso(String msg) {
		FacesMessage facesMsg = new FacesMessage("Aviso", msg);
		facesMsg.setSeverity(FacesMessage.SEVERITY_WARN);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public static void setSessionMapValue(String key, Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
	}

	public static Object getSessionMapValue(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
	}

	public static void removeSessionMapValue(String key, Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
	}

}
