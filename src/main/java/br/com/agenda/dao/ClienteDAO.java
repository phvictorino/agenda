package br.com.agenda.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.agenda.entidade.Cidade;
import br.com.agenda.entidade.Cliente;
import br.com.agenda.entidade.Estado;

@Repository
public class ClienteDAO extends GenericDAO<Cliente> {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Cliente> buscarPorCidade(Cidade cidade) {
		
		Query query = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.cidade.nome LIKE :cidade");
		query.setParameter("cidade", cidade.getNome());
		return null;
	}

	public List<Cliente> buscarPorEstado(Estado estado) {
		return null;
	}

	public List<Cliente> buscarPorNome(String nome) {
		return null;
	}

}
