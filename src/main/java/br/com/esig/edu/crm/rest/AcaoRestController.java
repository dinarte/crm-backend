package br.com.esig.edu.crm.rest;

import java.util.Date;
import java.util.Optional;

import javax.security.sasl.AuthenticationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import br.com.esig.api.auth.SecuredApi;
import br.com.esig.edu.crm.Paginacao;
import br.com.esig.edu.crm.PaginatedResponse;
import br.com.esig.edu.crm.auth.AutenticacaoService;
import br.com.esig.edu.crm.dominio.Acao;
import br.com.esig.edu.crm.dominio.dto.AcaoDTO;
import br.com.esig.edu.crm.service.AcaoService;
import io.swagger.annotations.Api;


@Api(value = "Ações - Este recurso prover os endpoints responsáveis por "
		+ "fazer a manutenção dos registros de Ações")
@RestController
@RequestScope
@RequestMapping("/api/acao")
public class AcaoRestController {
	
	@Autowired
	AcaoService acaoService;
	
	@Autowired
	AutenticacaoService authService;
	
	/**
	 * LISTAGEM: Retorna todas as acaos da instituição do usuário
	 * @param paginacao
	 * @return
	 * @throws AuthenticationException 
	 */
	@SecuredApi(userAuth = true)
	@RequestMapping(method = RequestMethod.GET, value = {"","/"})
	public PaginatedResponse<AcaoDTO> list(Optional<Paginacao> paginacao, String search) throws AuthenticationException {
		return acaoService.getAll(search, paginacao.orElse(new Paginacao()));
	}
	
	/**
	 * GET: Retorna a acao referente ao id informado
	 * @param acaoId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{acaoId}")
	public AcaoDTO get(@PathVariable("acaoId") int acaoId) {
		return acaoService.getOne(acaoId);
	}
	
	/**
	 * ADICIONAR: Adiciona uma nova acao
	 * @param acao
	 * @return
	 * @throws AuthenticationException 
	 */
	@RequestMapping(method = RequestMethod.POST, value = {"","/"})
	public ResponseEntity<AcaoDTO> add(@RequestBody @Valid Acao acao) throws AuthenticationException {
		acao.setUsuarioOperacao(authService.getUsuarioLogado());
		acao.setDataOperacao(new Date());
		return new ResponseEntity<>(acaoService.create(acao), HttpStatus.CREATED);
	}

	/**
	 * ALTERAR: Altera uma acao préexistente
	 * @param acao
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = {"","/"})
	public ResponseEntity<AcaoDTO> update(@RequestBody @Valid Acao acao, BindingResult result) {
		return new ResponseEntity<>(acaoService.update(acao), HttpStatus.ACCEPTED);
	}
	
	/**
	 * DELETAR: Remove uma acao
	 * @param acaoId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{acaoId}")
	public ResponseEntity<Object> del(@PathVariable("acaoId") int acaoId) {
		acaoService.delete(acaoId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
}
