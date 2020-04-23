package br.com.esig.edu.crm.auth;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.esig.api.auth.jwt.EnvelopeJwtDTO;
import br.com.esig.api.auth.jwt.JWTService;
import br.com.esig.edu.crm.comum.dominio.Usuario;
import lombok.Data;

@Component
@Scope("request")
@Data
public class AutenticacaoService {

	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private HttpServletRequest request;
	
	private EnvelopeJwtDTO dados;
	
	@PostConstruct
	private void getData() throws JsonParseException, JsonMappingException, IOException {
		dados = jwtService.decode( request.getHeader("Authorization").replace("Bearer ", "") );
	}
	
	public Usuario getUsuarioLogado(){
		
		ObjectMapper objectMapper = new ObjectMapper();
        String json;
		try {
			json = objectMapper.writeValueAsString(dados.getMetaDados().get("usuarioLocal"));
			return objectMapper.readValue(json, Usuario.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
