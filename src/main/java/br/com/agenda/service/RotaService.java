package br.com.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.agenda.dao.RotaDAO;
import br.com.agenda.entidade.Rota;

public class RotaService {

	@Autowired
	RotaDAO dao;

	public Rota salvar(Rota rota) {
		return dao.salvar(rota);
	}

	public void deletar(Long id) {
		dao.deletar(id);
	}

	public Rota buscarPorId(long id) {
		return dao.buscarPorId(id);
	}

	public List<Rota> listarTodos() {
		return dao.listarTodos();
	}

}
