package br.com.esig.edu.crm.comum.dominio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Usuario findByLoginAndPassword(String login, String senha);

	Usuario findByLogin(String login);
	
}
