package br.com.agenda.entidade;

import javax.persistence.Entity;

@Entity
public class Vendedor extends Usuario {

	private static final long serialVersionUID = 1L;
	
	private String cpf;

}
