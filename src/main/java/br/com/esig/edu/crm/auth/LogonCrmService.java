package br.com.esig.edu.crm.auth;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.esig.api.auth.jwt.EnvelopeJwtDTO;
import br.com.esig.api.auth.jwt.JWTService;
import br.com.esig.autenticacao.service.interfaces.AutenticacaoProdutoService;
import br.com.esig.core.config.ProdutoConfig;
import br.com.esig.core.dominio.ZonaDTO;
import br.com.esig.edu.crm.comum.dominio.Usuario;
import br.com.esig.edu.crm.comum.dominio.UsuarioRepository;
import br.com.esig.mcore.auth.client.UserAutenticacaoClient;
import br.com.esig.mcore.auth.dto.AutenticacaoRequest;
import br.com.esig.solis.client.dto.ProdutoDTO;
import br.com.esig.solis.client.dto.UsuarioAutenticacao;
import br.com.esig.web.util.RequestUtils;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class LogonCrmService {

	@Autowired
	ProdutoConfig produtoConfig;

	@Autowired
	JWTService jwtService;

	@Autowired
	UsuarioRepository userRepository;
	
	@Autowired
	AutenticacaoProdutoService autenticacaoService;
	
	@Autowired
	UserAutenticacaoClient autenticacaoClient;
	
	@Autowired
	Environment env;
	
	@Autowired
	HttpServletRequest req;

	public UsuarioAutenticacao getUsuarioFromSolisByLoginAndSenha(String login, String senha) {
		
		UsuarioAutenticacao autRequest = new UsuarioAutenticacao();
		
		autRequest.setZona(new ZonaDTO(env.getProperty(ZonaDTO.ZONA_PROPERTY_NAME)));
		autRequest.setLogin(login);
		autRequest.setSenha(senha);
		autRequest.setProduto(new ProdutoDTO(produtoConfig.getIdProduto()));
		preencheAutParams(autRequest);
		
		log.info("Iniciando autenticação para o usuário " + login + ", produto " + autRequest.getProduto().getId());

		UsuarioAutenticacao userAutenticado = autenticacaoService.autenticar(autRequest);
		Usuario userLocal = userRepository.findByLogin(login);
		userAutenticado.getAutParams().put("usuarioLocal", userLocal);
		
		return userAutenticado;
	}
	
	public br.com.esig.mcore.auth.dto.AutenticacaoResponse getUsuarioFromMCoreByLoginAndSenha(String login, String senha) {
				
		AutenticacaoRequest mCoreRequest = new AutenticacaoRequest();
		mCoreRequest.setLogin(login);
		mCoreRequest.setPassword(senha);
		mCoreRequest.setApp(produtoConfig.getAppCodigo());

		br.com.esig.mcore.auth.dto.AutenticacaoResponse responseMCore = autenticacaoClient.autenticar(mCoreRequest);

		log.info("Iniciando autenticação para o usuário " + login + ", produto " + responseMCore.getApp());
		
		
		return responseMCore;
	}
	
	
	/**
	 * Informa os parametros de autenticação: request-headers que precisam ser gravados, parmametros gerais de autenticação
	 * que precisarem.
	 * 
	 * @param userAut
	 * @param req
	 */
	private void preencheAutParams(UsuarioAutenticacao userAut) {
		
		final String ipAddress = req.getHeader("X-FORWARDED-FOR") != null ? req.getHeader("X-FORWARDED-FOR") : req.getRemoteAddr();  
		final String userAgent = req.getHeader("User-Agent");
		
		userAut.setUserIpAddress( ipAddress );
		userAut.setUserAgent(userAgent);
		userAut.setUrlForward(RequestUtils.getApplicationURL(req));
		
	}
	

	public String generateJWTSolis(UsuarioAutenticacao user ) {
		
		Usuario uLocal = (Usuario) user.getAutParams().get("usuarioLocal"); 
		
		EnvelopeJwtDTO envelope = new EnvelopeJwtDTO();
		envelope.setCodigoApp(produtoConfig.getAppCodigo());
		envelope.setData(new Date());
		envelope.setEmail(uLocal.getLogin());
		envelope.setLogin(uLocal.getLogin());
		envelope.setIdUsuario(uLocal.getId());
		envelope.setId(uLocal.getId());
		envelope.setInstituicaoId(user.getInstituicao().getId());
		
		envelope.setMetaDados( new HashMap<String,Object>() );
		envelope.getMetaDados().put("usuarioLocal", uLocal);

		return jwtService.generateJWT(envelope);
	}
	
	
	public String generateJWTMCore(br.com.esig.mcore.auth.dto.AutenticacaoResponse autResponse, Usuario uLocal) {
		
		EnvelopeJwtDTO envelope = new EnvelopeJwtDTO();
		envelope.setCodigoApp(produtoConfig.getAppCodigo());
		envelope.setData(new Date());
		envelope.setEmail(uLocal.getLogin());
		envelope.setLogin(uLocal.getLogin());
		envelope.setIdUsuario(uLocal.getId());
		envelope.setId(uLocal.getId());
		envelope.setInstituicaoId(12980);
		
		envelope.setMetaDados( new HashMap<String,Object>() );
		envelope.getMetaDados().put("usuarioLocal", uLocal);
		envelope.getMetaDados().put("mCoreAuth", autResponse);
		return jwtService.generateJWT(envelope);
	}
	

	public String toMD5(String password) {

		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		m.update(password.getBytes(), 0, password.length());
		return new BigInteger(1, m.digest()).toString(16);
	}

	

	

}
