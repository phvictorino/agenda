package agenda;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.agenda.entidade.Cidade;
import br.com.agenda.entidade.Cliente;
import br.com.agenda.entidade.Contato;
import br.com.agenda.entidade.Estado;
import br.com.agenda.entidade.TipoContato;
import br.com.agenda.service.ClienteService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/springbeans.xml")
@TransactionConfiguration( transactionManager="transactionManager", defaultRollback=true)
public class TestCliente {
	
	@Autowired
	ClienteService service;
	
	@Test
	@Transactional
	public void salvar() {
		TipoContato tp = new TipoContato();
		Contato contato = new Contato();
		List<Contato> contatos = new ArrayList<Contato>();
		Cidade cidade = new Cidade();
		Estado estado = new Estado();
		Cliente cliente = new Cliente();
		
		tp.setTipo("Email");
		
		contato.setTipo(tp);
		contatos.add(contato);
		
		estado.setNome("Mato Grosso do Sul");
		estado.setUf("MS");
		
		cidade.setEstado(estado);
		cidade.setNome("Campo Grande");
		
		cliente.setCidade(cidade);
		cliente.setNome("Jão");
		cliente.setContatos(contatos);
		
	}
	
}
