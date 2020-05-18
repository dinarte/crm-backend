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
import br.com.esig.edu.crm.dominio.FormaCaptacao;
import br.com.esig.edu.crm.dominio.dto.FormaCaptacaoDTO;
import br.com.esig.edu.crm.service.FormaCaptacaoService;
import io.swagger.annotations.Api;


@Api(value = "Forma de Captação - Este recurso prover os endpoints responsáveis por "
		+ "fazer a manutenção dos registros das formas de captação")
@RestController
@RequestScope
@RequestMapping("/api/forma-captacao")
public class FormaCaptacaoRestController {
	
	@Autowired
	FormaCaptacaoService formaCaptacaoService;
	
	@Autowired
	AutenticacaoService authService;
	
	/**
	 * LISTAGEM: Retorna todas as formaCaptacaos da instituição do usuário
	 * @param paginacao
	 * @return
	 * @throws AuthenticationException 
	 */
	@SecuredApi(userAuth = true)
	@RequestMapping(method = RequestMethod.GET, value = {"","/"})
	public PaginatedResponse<FormaCaptacaoDTO> list(Optional<Paginacao> paginacao, String search) throws AuthenticationException {
		return formaCaptacaoService.getAll(authService.getDados().getInstituicaoId(), search, paginacao.orElse(new Paginacao()));
	}
	
	/**
	 * GET: Retorna a formaCaptacao referente ao id informado
	 * @param formaCaptacaoId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{formaCaptacaoId}")
	public FormaCaptacaoDTO get(@PathVariable("formaCaptacaoId") int formaCaptacaoId) {
		return formaCaptacaoService.getOne(formaCaptacaoId);
	}
	
	/**
	 * ADICIONAR: Adiciona uma nova formaCaptacao
	 * @param formaCaptacao
	 * @return
	 * @throws AuthenticationException 
	 */
	@RequestMapping(method = RequestMethod.POST, value = {"","/"})
	public ResponseEntity<FormaCaptacaoDTO> add(@RequestBody @Valid FormaCaptacao formaCaptacao) throws AuthenticationException {
		formaCaptacao.setInstituicao(new Instituicao(authService.getDados().getInstituicaoId()));
		return new ResponseEntity<>(formaCaptacaoService.create(formaCaptacao), HttpStatus.CREATED);
	}

	/**
	 * ALTERAR: Altera uma formaCaptacao préexistente
	 * @param formaCaptacao
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = {"","/"})
	public ResponseEntity<FormaCaptacaoDTO> update(@RequestBody @Valid FormaCaptacao formaCaptacao, BindingResult result) {
		return new ResponseEntity<>(formaCaptacaoService.update(formaCaptacao), HttpStatus.ACCEPTED);
	}
	
	/**
	 * DELETAR: Remove uma formaCaptacao
	 * @param formaCaptacaoId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{formaCaptacaoId}")
	public ResponseEntity<Object> del(@PathVariable("formaCaptacaoId") int formaCaptacaoId) {
		formaCaptacaoService.delete(formaCaptacaoId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
}
