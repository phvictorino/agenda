package br.com.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agenda.dao.CidadeDAO;
import br.com.agenda.entidade.Cidade;

@Service
@Transactional
public class CidadeService {
	
	@Autowired
	CidadeDAO dao;
	
	public Cidade salvar(Cidade cidade){
		return dao.salvar(cidade);
	}
	
	public void remover(Long id) {
		dao.deletar(id);
	}
	
	public Cidade buscarPorId(Long id){
		return dao.buscarPorId(id);
	}
	
	public List<Cidade> listarTodos() {
		return dao.listarTodos();
	}
	

}
