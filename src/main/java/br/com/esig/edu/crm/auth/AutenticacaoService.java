package br.com.esig.edu.crm.auth;

import java.io.IOException;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.esig.api.auth.jwt.EnvelopeJwtDTO;
import br.com.esig.api.auth.jwt.JWTService;
import br.com.esig.edu.crm.comum.dominio.Usuario;

/**
 * O Serviço de autenticação serve para que o desenvolvedor 
 * possa recuperar os dados de atuetnticação do usuário logado.  
 * @author Dinarte
 *
 */
@Component
@RequestScope
public class AutenticacaoService {

	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private HttpServletRequest request;
	
	private EnvelopeJwtDTO dados;
	

	public EnvelopeJwtDTO getDados() throws AuthenticationException {
		if (dados != null )
			return dados;
		
		String autorizationheader = request.getHeader("Authorization");
		if (autorizationheader == null)
			throw new AuthenticationException("Usuário não autorizado para a operação (Token inexistente)");
		
		try {
			dados = jwtService.decode( request.getHeader("Authorization").replace("Bearer ", "") );
		} catch (IOException e) {
			e.printStackTrace();
			throw new AuthenticationException("Erro interno ao tentar autorizar o usuário: " + e.getMessage());
		}
		return dados;
	}
	
	public Usuario getUsuarioLogado(){
		
		ObjectMapper objectMapper = new ObjectMapper();
        String json;
		try {
			json = objectMapper.writeValueAsString(getDados().getMetaDados().get("usuarioLocal"));
			return objectMapper.readValue(json, Usuario.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
