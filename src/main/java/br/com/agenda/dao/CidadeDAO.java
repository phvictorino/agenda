package br.com.agenda.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.agenda.entidade.Cidade;

@Repository
public class CidadeDAO extends GenericDAO<Cidade>{

	@PersistenceContext
	EntityManager em;

	public CidadeDAO() {
		
	}

}
