package br.com.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agenda.dao.ClienteDAO;
import br.com.agenda.entidade.Cliente;

@Service
@Transactional
public class ClienteService {
	
	@Autowired
	ClienteDAO dao;
	
	public Cliente salvar(Cliente cliente) {
		return dao.salvar(cliente);
	}
	
	public void deletar(Cliente cliente) {
		dao.deletar(cliente);
	}
	
	public Cliente buscarPorId(long id) {
		return dao.buscarPorId(id);
	}
	
	public List<Cliente> listarTodos(){
		return dao.listarTodos();
	}

}
