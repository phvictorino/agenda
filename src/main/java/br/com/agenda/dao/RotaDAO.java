package br.com.agenda.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.agenda.entidade.Rota;

@Repository
public class RotaDAO extends GenericDAO<Rota> {

	@PersistenceContext
	EntityManager em;

	public RotaDAO() {

	}

	@SuppressWarnings("unchecked")
	public List<Rota> listarTodosOrdenadoPorOrdem() {

		try {
			Query query = em.createQuery("SELECT r FROM Rota r ORDER BY r.ordem");
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}

	}

}
