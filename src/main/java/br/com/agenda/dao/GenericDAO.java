package br.com.agenda.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class GenericDAO<T> {

	@PersistenceContext
	private EntityManager entityManager;

	public T salvar(T objeto) {
		T objetoResultado = entityManager.merge(objeto);
		return objetoResultado;
	}

	public void persistir(T objeto) {
		entityManager.persist(objeto);

	}

	public void deletar(Long id) {
		Query query = entityManager.createQuery("DELETE FROM "
	+ getTypeClass().getName() + " WHERE id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<T> listarTodos() {
		Query query = entityManager.createQuery("SELECT o FROM " + getTypeClass().getName() + " o");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public T buscarPorId(long id) {
		Query query = entityManager.createQuery("SELECT o FROM " + getTypeClass().getName() + " o WHERE o.id = :id");
		query.setParameter("id", id);
		return (T) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	private Class<T> getTypeClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

}
