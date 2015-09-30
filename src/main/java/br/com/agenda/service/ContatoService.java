package br.com.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.entidade.Contato;

@Repository
@Transactional
public class ContatoService {

	@Autowired
	ContatoDAO dao;

	public Contato salvar(Contato contato) {
		return dao.salvar(contato);
	}

	public void deletar(Long id) {
		dao.deletar(id);
	}

	public Contato buscarPorId(long id) {
		return dao.buscarPorId(id);
	}
	
	public List<Contato> listarTodos(){
		return dao.listarTodos();
	}

}
