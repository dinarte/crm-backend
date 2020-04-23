package br.com.esig.edu.crm.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.esig.edu.crm.comum.dominio.Usuario;
import br.com.esig.excecoes.AuthenticationException;
import br.com.esig.solis.client.dto.UsuarioAutenticacao;

/**
 * Responsável pelo login apenas dos usuários responsáveis
 * 
 * @author Gleydson, Dinarte
 *
 */

@RestController
public class LogonCrmRestController {

	@Autowired
	LogonCrmService logonService;

	@PostMapping(value = "/api/auth")
	public AutenticacaoResponse logar(@RequestParam("login") String login, @RequestParam("senha") String senha) {

		UsuarioAutenticacao usuario = logonService.getUsuarioByLoginAndSenha(login, senha);
		if (usuario == null) {
			throw new AuthenticationException("Usuário/Senha não encontrados");
		} else {

			AutenticacaoResponse autResponse = new AutenticacaoResponse((Usuario) usuario.getAutParams().get("usuarioLocal"));
			autResponse.setJwt(logonService.generateJWT(usuario));

			return autResponse;
		}

	}

}
