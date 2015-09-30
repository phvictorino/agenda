package br.com.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agenda.dao.ClienteDAO;
import br.com.agenda.entidade.Cidade;
import br.com.agenda.entidade.Cliente;
import br.com.agenda.entidade.Estado;

@Service
@Transactional
public class ClienteService {

	@Autowired
	ClienteDAO dao;

	public Cliente salvar(Cliente cliente) {
		return dao.salvar(cliente);
	}

	public void deletar(Long id) {
		dao.deletar(id);
	}

	public Cliente buscarPorId(long id) {

		Cliente cliente = dao.buscarPorId(id);

		if (cliente != null) {
			cliente.getContatos().size();
		}

		return cliente;
	}

	public List<Cliente> listarTodos() {

		List<Cliente> clientes = dao.listarTodos();

		if (clientes != null) {
			for (Cliente cliente : clientes) {
				cliente.getContatos().size();
			}
		}

		return clientes;
	}

	public List<Cliente> buscarPorCidade(Cidade cidade) {
		return dao.buscarPorCidade(cidade);
	}

	public List<Cliente> buscarPorEstado(Estado estado) {
		return dao.buscarPorEstado(estado);
	}

	public List<Cliente> buscarPorNome(String nome) {
		return dao.buscarPorNome(nome);
	}

}
