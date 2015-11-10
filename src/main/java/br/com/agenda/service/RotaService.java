package br.com.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agenda.dao.RotaDAO;
import br.com.agenda.entidade.Rota;

@Service
@Transactional
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

		List<Rota> rotas;

		rotas = dao.listarTodosOrdenadoPorOrdem();

		if (rotas != null) {
			for (Rota rota : rotas) {
				if (rota.getCidades() != null) {
					rota.getCidades().size();
				}
			}
		}

		return rotas;
	}

}
