package br.com.agenda.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.agenda.entidade.Rota;

@Repository
public class RotaDAO extends GenericDAO<Rota> {
	
	@PersistenceContext
	EntityManager em;
	
	public RotaDAO() {

	}

}
