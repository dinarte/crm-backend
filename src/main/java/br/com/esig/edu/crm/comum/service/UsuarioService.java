package br.com.esig.edu.crm.comum.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.security.sasl.AuthenticationException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import br.com.esig.edu.crm.auth.AutenticacaoService;
import br.com.esig.edu.crm.comum.dominio.Usuario;
import br.com.esig.edu.crm.comum.dominio.UsuarioRepository;
import br.com.esig.mcore.auth.dto.UsuarioDTO;

@Service
@RequestScope
public class UsuarioService {

	@Autowired
	AutenticacaoService auth;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	
	public List<UsuarioDTO> getUsuariosByInstituicao() throws AuthenticationException{
		return usuarioRepo.findAllAdmByInstituicaoId(auth.getDados().getInstituicaoId())
				.stream()
				.map(u -> mapper.map(u, UsuarioDTO.class))
				.collect(Collectors.toList());
	}


	public Usuario getUsuarioByLogin(String login) {
		return usuarioRepo.findByLogin(login);
	}

	
}
