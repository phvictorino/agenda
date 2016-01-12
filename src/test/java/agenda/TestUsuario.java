package agenda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import br.com.agenda.entidade.Perfil;
import br.com.agenda.entidade.Usuario;
import br.com.agenda.service.PerfilService;
import br.com.agenda.service.UsuarioService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/springbeans.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class TestUsuario {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	PerfilService perfilService;

	@Test
	public void salvar() {
		
		//CADASTRO PARA INICIAR SISTEMA

		Perfil perfil = new Perfil();
		perfil.setDescricao("ROLE_ADMIN");
		perfilService.salvar(perfil);

		Usuario u = new Usuario();
		u.setAtivo(true);
		u.setNome("Administrador do sistema");
		u.setEmail("pedro.victorino@gmail.com");
		u.setLogin("admin");
		u.setPerfil(perfil);
		u.setSenha("123");
		usuarioService.salvar(u);

	}

}
