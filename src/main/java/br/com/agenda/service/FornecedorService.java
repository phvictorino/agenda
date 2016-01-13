package br.com.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agenda.dao.FornecedorDAO;
import br.com.agenda.entidade.Fornecedor;

@Service
@Transactional
public class FornecedorService {
	
	@Autowired
	FornecedorDAO fornecedorDAO;
	
	public Fornecedor salvar(Fornecedor fornecedor) {
		return this.fornecedorDAO.salvar(fornecedor);
	}
	
	public Fornecedor buscarPorId(Long id) {
		return this.fornecedorDAO.buscarPorId(id);
	}
	
	public void excluir(Long id) {
		this.fornecedorDAO.deletar(id);
	}

	public List<Fornecedor> listarTodos() {
		return this.fornecedorDAO.listarTodos();
	}

}
