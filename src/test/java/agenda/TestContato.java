package agenda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.agenda.entidade.Contato;
import br.com.agenda.entidade.TipoContato;
import br.com.agenda.service.ContatoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/springbeans.xml")
@TransactionConfiguration( transactionManager="transactionManager", defaultRollback=true)
public class TestContato {
	
	@Autowired
	ContatoService service;
	
	@Test
	@Transactional
	public void salvar() {
		TipoContato tp = new TipoContato();
		Contato c = new Contato();
		
		tp.setTipo("Email");
		
		c.setContato("pedro@pedro.com");
		c.setTipo(tp);
		
		
		
		
		
	}
	
	

}
