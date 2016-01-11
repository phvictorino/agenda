package br.com.agenda.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Cliente extends Pessoa implements Serializable {

	private static final long serialVersionUID = -1375244853694540763L;

	public static final Integer CLIENTE_ATIVO = 1;
	public static final Integer CLIENTE_INATIVO = 2;

	private Integer situacao;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
	private List<Contato> contatos;

	@ManyToOne(cascade = CascadeType.ALL)
	private Cidade cidade;

	@ManyToOne(cascade = CascadeType.ALL)
	private Rota rota;

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

}
