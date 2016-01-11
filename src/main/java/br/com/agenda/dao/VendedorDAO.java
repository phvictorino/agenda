package br.com.agenda.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.agenda.entidade.Vendedor;

@Repository
public class VendedorDAO extends GenericDAO<Vendedor> {

	@PersistenceContext
	private EntityManager entityManager;

}
