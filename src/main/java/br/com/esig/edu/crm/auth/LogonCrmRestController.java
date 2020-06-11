package br.com.esig.edu.crm.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.esig.edu.crm.comum.dominio.Usuario;
import br.com.esig.edu.crm.comum.service.UsuarioService;
import br.com.esig.excecoes.AuthenticationException;
import br.com.esig.excecoes.InfraException;
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
	
	@Autowired
	UsuarioService userService;

	@PostMapping(value = "/api/auth")
	public br.com.esig.mcore.auth.dto.AutenticacaoResponse logarMCore(@RequestBody Credencials credencials) {

		try {
			br.com.esig.mcore.auth.dto.AutenticacaoResponse autResponse = logonService.getUsuarioFromMCoreByLoginAndSenha(credencials.getLogin(), credencials.getSenha());
			Usuario uLocal  = userService.getUsuarioByLogin(credencials.getLogin());
			autResponse.setJwtToken(logonService.generateJWTMCore(autResponse, uLocal));
			return autResponse;
		} catch(InfraException e) {
			throw new AuthenticationException(e.getMessage());
		}	
		

	}
	
	@PostMapping(value = "/api/auth/solis")
	public AutenticacaoResponse logarSolis(Credencials credencials) {

		UsuarioAutenticacao usuario = logonService.getUsuarioFromSolisByLoginAndSenha(credencials.getLogin(), credencials.getSenha());
		if (usuario == null) {
			throw new AuthenticationException("Usuário/Senha não encontrados");
		} else {

			AutenticacaoResponse autResponse = new AutenticacaoResponse((Usuario) usuario.getAutParams().get("usuarioLocal"));
			autResponse.setJwt(logonService.generateJWTSolis(usuario));

			return autResponse;
		}

	}

}
