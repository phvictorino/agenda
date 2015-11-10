package agenda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import br.com.agenda.entidade.Perfil;
import br.com.agenda.service.PerfilService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/springbeans.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class TestPerfil {

	@Autowired
	PerfilService perfilService;

	@Test
	public void salvar() {

		Perfil perfil = new Perfil();

		perfil.setDescricao("ROLE_USER");

		perfilService.salvar(perfil);

		Perfil perfil2 = new Perfil();

		perfil2.setDescricao("ROLE_ADMIN");

		perfilService.salvar(perfil2);

	}

}
