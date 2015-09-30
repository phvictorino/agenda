package br.com.agenda.controller;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
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
//@Scope("view")
public class ClienteController {

	private Cliente cliente = new Cliente();
	private List<TipoContato> listaTiposContato;
	private List<Contato> listaContatos;
	private Contato contatoSelecionado;
	private Cliente clienteSelecionado;
	private Long idCidadeSelecionada;
	private Long idTipoContatoSelecionado;
	private String nomeContato;

	private String nomeCliente;

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
		this.contatoSelecionado = new Contato();
		listaContatos = new ArrayList<Contato>();
	}

	public void novo() {
		idCidadeSelecionada = null;
		cliente = new Cliente();
		listaContatos = new ArrayList<Contato>();
		nomeCliente = "";
		idTipoContatoSelecionado = null;
		UtilsGeral.redirecionar(UtilsGeral.obterUrl() + "/cliente/form.xhtml");
	}

	public void salvar() {

		Cidade cidadeSelecionada = cidadeService.buscarPorId(idCidadeSelecionada);

		cliente.setNome(nomeCliente);
		cliente.setContatos(listaContatos);
		cliente.setCidade(cidadeSelecionada);
		clienteService.salvar(cliente);

		UtilsGeral.adicionarMsgInfo("Usuário salvo com sucesso!");

		UtilsGeral.redirecionar("listar.xhtml");
	}

	public List<Cliente> getListaClientes() {
		return clienteService.listarTodos();
	}

	public List<Cidade> getListaCidades() {
		return cidadeService.listarTodos();
	}

	public void adicionarContato() {

		contatoSelecionado.setContato(nomeContato);
		contatoSelecionado.setCliente(cliente);
		TipoContato tp = tipoContatoService.buscarPorId(idTipoContatoSelecionado);
		contatoSelecionado.setTipo(tp);

		if (contatoSelecionado != null) {
			listaContatos.add(contatoSelecionado);
		}

		nomeContato = "";
		idTipoContatoSelecionado = null;
		contatoSelecionado = new Contato();

	}

	public void removerContato() {

		if (contatoSelecionado.getId() != null) {
			contatoService.deletar(contatoSelecionado.getId());
		}

		listaContatos.remove(contatoSelecionado);

	}

	public void verContatosCliente() {

		this.listaContatos = this.clienteSelecionado.getContatos();

	}

	public void editarCliente() {

		cliente = clienteSelecionado;

		listaContatos = clienteSelecionado.getContatos();

		idCidadeSelecionada = clienteSelecionado.getCidade().getId();

		nomeCliente = clienteSelecionado.getNome();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("form.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

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

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

}
