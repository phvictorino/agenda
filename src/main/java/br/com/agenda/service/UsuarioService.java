package br.com.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agenda.dao.UsuarioDAO;
import br.com.agenda.entidade.Usuario;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	UsuarioDAO dao;

	public Usuario salvar(Usuario usuario) {
		return dao.salvar(usuario);
	}

	public void remover(Long id) {
		dao.deletar(id);
	}

	public Usuario buscarPorId(Long id) {
		return dao.buscarPorId(id);
	}

	public List<Usuario> listarTodos() {

		return dao.listarTodos();
	}

}
