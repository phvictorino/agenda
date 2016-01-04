package br.com.agenda.entidade;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Fornecedor extends Pessoa {

	private static final long serialVersionUID = 1L;

}
