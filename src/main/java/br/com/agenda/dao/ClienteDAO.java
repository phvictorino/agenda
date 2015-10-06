package br.com.agenda.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.agenda.entidade.Cliente;

@Repository
public class ClienteDAO extends GenericDAO<Cliente> {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Cliente> buscarPorFiltros(Long idCidade, Long idEstado, String nome, Integer situacaoCliente) {
		List<Cliente> clientes = null;
		StringBuilder sql = new StringBuilder();

		if (idCidade != null || idEstado != null || nome != null) {

			try {

				sql.append("SELECT c FROM Cliente c WHERE 1=1  ");

				if (nome != null && !nome.isEmpty()) {
					sql.append(" AND UPPER(c.nome) LIKE '%" + nome.toUpperCase() + "%' ");
				}

				if (idEstado != null) {
					sql.append(" AND c.cidade.estado.id = '" + idEstado + "' ");
				}

				if (idCidade != null) {
					sql.append(" AND c.cidade.id = '" + idCidade + "' ");
				}

				if (situacaoCliente != null) {
					sql.append(" AND c.situacao = " + situacaoCliente);
				}

				Query query = entityManager.createQuery(sql.toString());
				clientes = query.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return clientes;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listarTodosAtivos() {

		List<Cliente> clientes = null;
		
		try {
			Query query = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.situacao = :situacao");
			query.setParameter("situacao", Cliente.CLIENTE_ATIVO);
			clientes = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return clientes;
	}

}
