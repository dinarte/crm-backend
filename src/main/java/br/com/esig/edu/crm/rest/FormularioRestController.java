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
import br.com.esig.edu.crm.dominio.Formulario;
import br.com.esig.edu.crm.dominio.dto.FormularioDTO;
import br.com.esig.edu.crm.service.FormularioService;
import io.swagger.annotations.Api;


@Api(value = "Formularios - Este recurso prover os endpoints responsáveis por "
		+ "fazer a manutenção dos registros de formularios de marketing")
@RestController
@RequestScope
@RequestMapping("/api/formulario")
public class FormularioRestController {
	
	@Autowired
	FormularioService formularioService;
	
	@Autowired
	AutenticacaoService authService;
	
	/**
	 * LISTAGEM: Retorna todas as formularios da instituição do usuário
	 * @param paginacao
	 * @return
	 * @throws AuthenticationException 
	 */
	@SecuredApi(userAuth = true)
	@RequestMapping(method = RequestMethod.GET, value = {"","/"})
	public PaginatedResponse<FormularioDTO> list(Optional<Paginacao> paginacao, String search) throws AuthenticationException {
		return formularioService.getAll(authService.getDados().getInstituicaoId(), search, paginacao.orElse(new Paginacao()));
	}
	
	/**
	 * GET: Retorna a formulario referente ao id informado
	 * @param formularioId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{formularioId}")
	public FormularioDTO get(@PathVariable("formularioId") int formularioId) {
		return formularioService.getOne(formularioId);
	}
	
	/**
	 * ADICIONAR: Adiciona uma nova formulario
	 * @param formulario
	 * @return
	 * @throws AuthenticationException 
	 */
	@RequestMapping(method = RequestMethod.POST, value = {"","/"})
	public ResponseEntity<FormularioDTO> add(@RequestBody @Valid Formulario formulario) throws AuthenticationException {
		formulario.setInstituicao(new Instituicao(authService.getDados().getInstituicaoId()));
		return new ResponseEntity<>(formularioService.create(formulario), HttpStatus.CREATED);
	}

	/**
	 * ALTERAR: Altera uma formulario préexistente
	 * @param formulario
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = {"","/"})
	public ResponseEntity<FormularioDTO> update(@RequestBody @Valid Formulario formulario, BindingResult result) {
		return new ResponseEntity<>(formularioService.update(formulario), HttpStatus.ACCEPTED);
	}
	
	/**
	 * DELETAR: Remove uma formulario
	 * @param formularioId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{formularioId}")
	public ResponseEntity<Object> del(@PathVariable("formularioId") int formularioId) {
		formularioService.delete(formularioId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
}
