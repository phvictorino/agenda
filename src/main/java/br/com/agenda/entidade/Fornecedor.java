package br.com.agenda.entidade;

import javax.persistence.Entity;

@Entity
public class Fornecedor extends Usuario {

	private static final long serialVersionUID = 1L;
	
	private String nomeEmpresa;
	private String cnpj;
	
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
