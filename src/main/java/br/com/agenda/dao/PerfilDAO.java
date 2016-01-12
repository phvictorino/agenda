package br.com.agenda.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.agenda.entidade.Perfil;

@Repository
public class PerfilDAO extends GenericDAO<Perfil> {

	@PersistenceContext
	private EntityManager entityManager;

	public Perfil buscarPorNome(String nome) {
		
		if (nome != null) {
			
			 Query query = entityManager.createQuery("SELECT p FROM Perfil WHERE p.descricao LIKE '%" + nome + "%'");
			 
			 try {
				 return (Perfil) query.getSingleResult();
			 } catch (Exception e) {
				 e.printStackTrace();
				 return null;
			 }
			 
		}
		
		return null;
	}

}
