package br.com.esig.edu.crm.rest;

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
import br.com.esig.edu.crm.comum.dominio.Instituicao;
import br.com.esig.edu.crm.dominio.AcaoCanal;
import br.com.esig.edu.crm.dominio.dto.AcaoCanalDTO;
import br.com.esig.edu.crm.service.AcaoCanalService;
import io.swagger.annotations.Api;


@Api(value = "AcaoCanals - Este recurso prover os endpoints responsáveis por "
		+ "fazer a manutenção dos registros de acaoCanals de marketing")
@RestController
@RequestScope
@RequestMapping("/api/acao-canal")
public class AcaoCanalRestController {
	
	@Autowired
	AcaoCanalService acaoCanalService;
	
	@Autowired
	AutenticacaoService authService;
	
	/**
	 * LISTAGEM: Retorna todas as acaoCanals da instituição do usuário
	 * @param paginacao
	 * @return
	 * @throws AuthenticationException 
	 */
	@SecuredApi(userAuth = true)
	@RequestMapping(method = RequestMethod.GET, value = {"","/"})
	public PaginatedResponse<AcaoCanalDTO> list(Optional<Paginacao> paginacao, String search) throws AuthenticationException {
		return acaoCanalService.getAll(authService.getDados().getInstituicaoId(), search, paginacao.orElse(new Paginacao()));
	}
	
	/**
	 * GET: Retorna a acaoCanal referente ao id informado
	 * @param acaoCanalId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{acaoCanalId}")
	public AcaoCanalDTO get(@PathVariable("acaoCanalId") int acaoCanalId) {
		return acaoCanalService.getOne(acaoCanalId);
	}
	
	/**
	 * ADICIONAR: Adiciona uma nova acaoCanal
	 * @param acaoCanal
	 * @return
	 * @throws AuthenticationException 
	 */
	@RequestMapping(method = RequestMethod.POST, value = {"","/"})
	public ResponseEntity<AcaoCanalDTO> add(@RequestBody @Valid AcaoCanal acaoCanal) throws AuthenticationException {
		return new ResponseEntity<>(acaoCanalService.create(acaoCanal), HttpStatus.CREATED);
	}

	/**
	 * ALTERAR: Altera uma acaoCanal préexistente
	 * @param acaoCanal
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = {"","/"})
	public ResponseEntity<AcaoCanalDTO> update(@RequestBody @Valid AcaoCanal acaoCanal, BindingResult result) {
		return new ResponseEntity<>(acaoCanalService.update(acaoCanal), HttpStatus.ACCEPTED);
	}
	
	/**
	 * DELETAR: Remove uma acaoCanal
	 * @param acaoCanalId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{acaoCanalId}")
	public ResponseEntity<Object> del(@PathVariable("acaoCanalId") int acaoCanalId) {
		acaoCanalService.delete(acaoCanalId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
}
