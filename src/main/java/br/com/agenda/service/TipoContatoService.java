package br.com.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agenda.dao.TipoContatoDAO;
import br.com.agenda.entidade.TipoContato;

@Service
@Transactional
public class TipoContatoService {

	@Autowired
	TipoContatoDAO dao;

	public TipoContato salvar(TipoContato tp) {
		return dao.salvar(tp);
	}
	
	public void deletar(TipoContato tp) {
		dao.deletar(tp);
	}
	
	public TipoContato buscarPorId(long id) {
		return dao.buscarPorId(id);
	}
	 
	public List<TipoContato> listarTodos() {
		return dao.listarTodos();
	}

}
