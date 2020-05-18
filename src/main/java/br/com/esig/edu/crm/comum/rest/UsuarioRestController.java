package br.com.esig.edu.crm.comum.rest;

import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import br.com.esig.api.auth.SecuredApi;
import br.com.esig.edu.crm.comum.service.UsuarioService;
import br.com.esig.mcore.auth.dto.UsuarioDTO;

@RestController()
@RequestScope
@RequestMapping("/api/comum")
public class UsuarioRestController {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/usuario")
	@SecuredApi(userAuth = true)
	public List<UsuarioDTO> listUfs() throws AuthenticationException{
		return usuarioService.getUsuariosByInstituicao();
	}
	
}
