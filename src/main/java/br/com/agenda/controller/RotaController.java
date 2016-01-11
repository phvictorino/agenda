package br.com.agenda.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.ReorderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.agenda.entidade.Cliente;
import br.com.agenda.entidade.Rota;
import br.com.agenda.service.RotaService;
import br.com.agenda.utils.UtilsGeral;

@Controller
@ManagedBean
public class RotaController {

	Rota rota = new Rota();
	List<Rota> rotas;
	List<Cliente> clientes;	

	@Autowired
	RotaService rotaService;

	public RotaController() {

	}

	@PostConstruct
	public void init() {
		rotas = rotaService.listarTodos();
	}

	public void listar() {
		UtilsGeral.redirecionar("/rota/listar.xhtml");
	}

	public void novo() {
		this.rota = new Rota();
		UtilsGeral.redirecionar("/rota/form.xhtml");
	}

	public void mudarOrdemLista(ReorderEvent e) {
//		String tabela = ((DataTable) e.getSource()).getClientId();

		DataTable myDatatable = (DataTable) e.getSource();

		Rota rotaMovida = (Rota) myDatatable.getRowData();
		// List<Rota> listaAposMover = (Rota) myDatatable

//		if (rotaMovida != null) {
//
//			Integer ordemNova = e.getToIndex() + 1;
//			Integer ordemAntiga = rotaMovida.getOrdem();
//
//			if (ordemNova > ordemAntiga) {
//				for (Rota r : rotas) {
//					if (r != null && !r.equals(rotaMovida)) {
//						if (r.getOrdem() <= ordemNova && r.getOrdem() >= ordemAntiga) {
//							r.setOrdem(r.getOrdem().intValue() - 1);
//							rotaService.salvar(r);
//						}
//					}
//				}
//			} else {
//				for (Rota r : rotas) {
//					if (r != null && !r.equals(rotaMovida)) {
//						if (r.getOrdem() >= ordemNova && r.getOrdem() <= ordemAntiga) {
//							r.setOrdem(r.getOrdem().intValue() + 1);
//							rotaService.salvar(r);
//						}
//					}
//				}
//			}
//
//			rotaMovida.setOrdem(ordemNova);
//
//			rotaService.salvar(rotaMovida);
//		}

	}

	public void salvar() {
		rotaService.salvar(rota);

		this.rotas.add(rota);

		UtilsGeral.adicionarMsgInfo("Rota salva com sucesso.");

		UtilsGeral.redirecionar("/rota/listar.xhtml");
	}

	public List<Rota> getListaTodos() {
		return rotaService.listarTodos();
	}

	public void deletar() {

		try {
			rotaService.deletar(this.rota.getId());
			UtilsGeral.adicionarMsgInfo("Rota excluída com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			UtilsGeral.adicionarMsgErro("Há clientes relacionados nessa rota. Impossível excluir.");
		}

		UtilsGeral.redirecionar("/rota/listar.xhtml");
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public List<Rota> getRotas() {
		return rotas;
	}

	public void setRotas(List<Rota> rotas) {
		this.rotas = rotas;
	}

}
