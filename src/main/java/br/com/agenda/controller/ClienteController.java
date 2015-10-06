package br.com.agenda.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.agenda.entidade.Cidade;
import br.com.agenda.entidade.Cliente;
import br.com.agenda.entidade.Contato;
import br.com.agenda.entidade.Estado;
import br.com.agenda.entidade.TipoContato;
import br.com.agenda.service.CidadeService;
import br.com.agenda.service.ClienteService;
import br.com.agenda.service.ContatoService;
import br.com.agenda.service.EstadoService;
import br.com.agenda.service.TipoContatoService;
import br.com.agenda.utils.UtilsGeral;

@Controller
@ManagedBean
//@Scope("view")
public class ClienteController {

	private Cliente cliente = new Cliente();
	private List<TipoContato> listaTiposContato;
	private List<Contato> listaContatos;
	private List<Cliente> listaClientes;
	private List<Cidade> listaCidades;
	private Contato contatoSelecionado;
	private Cliente clienteSelecionado;
	private Long idCidadeSelecionada;
	private Long idTipoContatoSelecionado;
	private String nomeContato;
	private Integer situacaoCliente;
	
	private Integer clienteAtivo = Cliente.CLIENTE_ATIVO;
	private Integer clienteInativo = Cliente.CLIENTE_INATIVO;

	private Long idCidadeFiltro;
	private Long idEstadoFiltro;
	private String nomeClienteFiltro;

	private String nomeCliente;

	@Autowired
	ClienteService clienteService;

	@Autowired
	ContatoService contatoService;

	@Autowired
	TipoContatoService tipoContatoService;

	@Autowired
	CidadeService cidadeService;

	@Autowired
	EstadoService estadoService;

	public ClienteController() {

	}

	@PostConstruct
	public void init() {
		this.listaTiposContato = tipoContatoService.listarTodos();
		this.contatoSelecionado = new Contato();
		listaContatos = new ArrayList<Contato>();
		listaCidades = cidadeService.listarTodos();
		listaClientes = clienteService.listarTodosAtivos();
	}

	public void listar() {
		listaCidades = new ArrayList<Cidade>();
		nomeClienteFiltro = "";
		idCidadeFiltro = null;
		idEstadoFiltro = null;
		situacaoCliente = Cliente.CLIENTE_ATIVO;
		listaClientes = clienteService.listarTodosAtivos();
		UtilsGeral.redirecionar("/cliente/listar.xhtml");
	}

	public void novo() {
		listaCidades = cidadeService.listarTodos();
		this.listaTiposContato = tipoContatoService.listarTodos();
		idCidadeSelecionada = null;
		cliente = new Cliente();
		listaContatos = new ArrayList<Contato>();
		nomeCliente = "";
		idTipoContatoSelecionado = null;
		UtilsGeral.redirecionar("/cliente/form.xhtml");
		
		
	}

	public void salvar() {

		Cidade cidadeSelecionada = cidadeService.buscarPorId(idCidadeSelecionada);
		
		cliente.setSituacao(situacaoCliente);
		cliente.setNome(nomeCliente);
		cliente.setContatos(listaContatos);
		cliente.setCidade(cidadeSelecionada);
		clienteService.salvar(cliente);
		listaClientes = clienteService.listarTodos();

		UtilsGeral.adicionarMsgInfo("Usuário salvo com sucesso!");

		UtilsGeral.redirecionar("/cliente/listar.xhtml");
	}

	//	public List<Cliente> getListaClientes() {
	//		return clienteService.listarTodos();
	//	}

	//	public List<Cidade> getListaCidades() {
	//		return cidadeService.listarTodos();
	//	}

	public void adicionarContato() {

		if (nomeContato != null && !nomeContato.isEmpty()) {
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
		} else {
			UtilsGeral.adicionarMsgErro("Digite o nome do contato para adicionar.");
		}

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
		listaCidades = cidadeService.listarTodos();
		cliente = clienteSelecionado;
		listaContatos = clienteSelecionado.getContatos();
		this.listaTiposContato = tipoContatoService.listarTodos();
		idCidadeSelecionada = clienteSelecionado.getCidade().getId();
		nomeCliente = clienteSelecionado.getNome();

		UtilsGeral.redirecionar("/cliente/form.xhtml");

	}

	public List<Estado> getListaEstados() {
		return estadoService.listarTodos();
	}

	public void aplicarFiltrosClientes() {

		List<Cliente> clientes = clienteService.buscarPorFiltros(idCidadeFiltro, idEstadoFiltro, nomeClienteFiltro, situacaoCliente);

		if (clientes != null) {
			this.listaClientes = clientes;
		} else {
			UtilsGeral.adicionarMsgErro("Nenhum cliente encontrado com o filtro aplicado.");
		}

	}

	public void cidadesDoEstadoSelecionado() {

		Estado estado = estadoService.buscarPorId(idEstadoFiltro);

		if (estado != null) {
			listaCidades = estado.getCidades();
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

	public Long getIdCidadeFiltro() {
		return idCidadeFiltro;
	}

	public void setIdCidadeFiltro(Long idCidadeFiltro) {
		this.idCidadeFiltro = idCidadeFiltro;
	}

	public Long getIdEstadoFiltro() {
		return idEstadoFiltro;
	}

	public void setIdEstadoFiltro(Long idEstadoFiltro) {
		this.idEstadoFiltro = idEstadoFiltro;
	}

	public String getNomeClienteFiltro() {
		return nomeClienteFiltro;
	}

	public void setNomeClienteFiltro(String nomeClienteFiltro) {
		this.nomeClienteFiltro = nomeClienteFiltro;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public void setListaCidades(List<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public List<Cidade> getListaCidades() {
		return listaCidades;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public Integer getSituacaoCliente() {
		return situacaoCliente;
	}

	public void setSituacaoCliente(Integer situacaoCliente) {
		this.situacaoCliente = situacaoCliente;
	}

	public Integer getClienteAtivo() {
		return clienteAtivo;
	}

	public void setClienteAtivo(Integer clienteAtivo) {
		this.clienteAtivo = clienteAtivo;
	}

	public Integer getClienteInativo() {
		return clienteInativo;
	}

	public void setClienteInativo(Integer clienteInativo) {
		this.clienteInativo = clienteInativo;
	}


}
