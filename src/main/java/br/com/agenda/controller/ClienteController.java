package br.com.agenda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.agenda.entidade.Cidade;
import br.com.agenda.entidade.Cliente;
import br.com.agenda.entidade.Contato;
import br.com.agenda.entidade.TipoContato;
import br.com.agenda.service.CidadeService;
import br.com.agenda.service.ClienteService;
import br.com.agenda.service.ContatoService;
import br.com.agenda.service.TipoContatoService;
import br.com.agenda.utils.UtilsGeral;

@Controller
@ManagedBean
public class ClienteController {

	private Cliente cliente = new Cliente();
	private List<TipoContato> listaTiposContato;
	private List<Contato> listaContatos;
	private Contato contatoSelecionado;
	private Cliente clienteSelecionado;
	private Long idCidadeSelecionada;
	private Long idTipoContatoSelecionado;
	private String nomeContato;

	@Autowired
	ClienteService clienteService;

	@Autowired
	ContatoService contatoService;

	@Autowired
	TipoContatoService tipoContatoService;

	@Autowired
	CidadeService cidadeService;
	
	public ClienteController() {
		
	}

	@PostConstruct
	public void init() {
		this.listaTiposContato = tipoContatoService.listarTodos();

		listaContatos = new ArrayList<Contato>();
	}

	public void salvar() {

		Cidade cidadeSelecionada = cidadeService.buscarPorId(idCidadeSelecionada);
		cliente.setContatos(listaContatos);
		cliente.setCidade(cidadeSelecionada);
		clienteService.salvar(cliente);

	}

	public List<Cliente> getListaClientes() {
		return clienteService.listarTodos();
	}

	public List<Cidade> getListaCidades() {
		return cidadeService.listarTodos();
	}

	public void adicionarContato() {

		contatoSelecionado = new Contato();
		contatoSelecionado.setContato(nomeContato);
		TipoContato tp = tipoContatoService.buscarPorId(idTipoContatoSelecionado);
		contatoSelecionado.setTipo(tp);
		listaContatos.add(contatoSelecionado);

	}

	public void removerContato() {

		listaContatos.remove(contatoSelecionado);

	}

	public void verContatosCliente() {

		this.listaContatos = this.clienteSelecionado.getContatos();

	}

	public Long getIdCidadeSelecionada() {
		return idCidadeSelecionada;
	}

	public void setIdCidadeSelecionada(Long idCidadeSelecionada) {
		this.idCidadeSelecionada = idCidadeSelecionada;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Contato> getListaContatos() {
		return listaContatos;
	}

	public void setListaContatos(List<Contato> listaContatos) {
		this.listaContatos = listaContatos;
	}

	public Contato getContatoSelecionado() {
		return contatoSelecionado;
	}

	public void setContatoSelecionado(Contato contatoSelecionado) {
		this.contatoSelecionado = contatoSelecionado;
	}

	public List<TipoContato> getListaTiposContato() {
		return listaTiposContato;
	}

	public void setListaTiposContato(List<TipoContato> listaTiposContato) {
		this.listaTiposContato = listaTiposContato;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public Long getIdTipoContatoSelecionado() {
		return idTipoContatoSelecionado;
	}

	public void setIdTipoContatoSelecionado(Long idTipoContatoSelecionado) {
		this.idTipoContatoSelecionado = idTipoContatoSelecionado;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

}
