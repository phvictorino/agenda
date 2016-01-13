package br.com.agenda.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.agenda.entidade.Fornecedor;

@Repository
public class FornecedorDAO extends GenericDAO<Fornecedor> {
	
	@PersistenceContext
	private EntityManager entityManager;

}
