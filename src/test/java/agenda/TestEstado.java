package agenda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.agenda.entidade.Estado;
import br.com.agenda.service.EstadoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/springbeans.xml")
@TransactionConfiguration( transactionManager="transactionManager", defaultRollback=true)
public class TestEstado {
	
	@Autowired
	EstadoService service;
	
	@Test
	@Transactional
	public void salvar() {
		Estado e = new Estado();
		
		e.setNome("Mato Grosso do Sul");
		e.setUf("MS");
		
		service.salvar(e);
	}
	

}
