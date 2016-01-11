package br.com.agenda.entidade;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Vendedor extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	@OneToOne
	private Usuario usuario;
	

}
