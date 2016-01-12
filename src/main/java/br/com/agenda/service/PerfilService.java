package br.com.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agenda.dao.PerfilDAO;
import br.com.agenda.entidade.Perfil;

@Service
@Transactional
public class PerfilService {
	
	@Autowired
	PerfilDAO dao;
	
	public Perfil salvar(Perfil perfil) {
		return dao.salvar(perfil);
	}

	public void remover(Long id) {
		dao.deletar(id);
	}

	public Perfil buscarPorId(Long id) {
		return dao.buscarPorId(id);
	}

	public List<Perfil> listarTodos() {

		List<Perfil> perfils = dao.listarTodos();

		if (perfils != null) {
			for (Perfil perfil : perfils) {
				if (perfil.getUsuarios() != null) {
					perfil.getUsuarios().size();
				}
			}
		}

		return perfils;
	}
	
	public Perfil buscarPorNome(String nome) {
		return dao.buscarPorNome(nome);
	}
	

}
