package br.com.agenda.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.agenda.entidade.Usuario;

@Repository
public class UsuarioDAO extends GenericDAO<Usuario> {

	@PersistenceContext
	private EntityManager entityManager;

	public Usuario buscarPorLogin(String login) {

		if (login != null) {

			String hql = "SELECT u FROM Usuario u WHERE u.login = :login";
			Query query = entityManager.createQuery(hql.toString());
			query.setParameter("login", login);

			try {
				return (Usuario) query.getSingleResult();
			} catch (Exception e) {
				return null;
			}

		}

		return null;

	}

}
