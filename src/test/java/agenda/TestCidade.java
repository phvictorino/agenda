package agenda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.agenda.entidade.Cidade;
import br.com.agenda.entidade.Estado;
import br.com.agenda.service.CidadeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/springbeans.xml")
@TransactionConfiguration( transactionManager="transactionManager", defaultRollback=true)
public class TestCidade {
	
	@Autowired
	CidadeService service;
	
	@Test
	@Transactional
	public void salvar() {
		Cidade c = new Cidade();
		Estado e = new Estado();
		
		e.setNome("MATO GROSSO DO SUL");
		e.setUf("MS");
		
		c.setNome("Campo Grande");
		c.setEstado(e);
	}

}
