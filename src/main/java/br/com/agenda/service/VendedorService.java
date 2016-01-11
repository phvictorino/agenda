package br.com.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agenda.dao.VendedorDAO;
import br.com.agenda.entidade.Vendedor;

@Service
@Transactional
public class VendedorService {

	@Autowired
	private VendedorDAO vendedorDAO;

	public Vendedor buscarPorId(Long id) {
		return vendedorDAO.buscarPorId(id);
	}

	public List<Vendedor> listarTodos() {
		return vendedorDAO.listarTodos();
	}

}
