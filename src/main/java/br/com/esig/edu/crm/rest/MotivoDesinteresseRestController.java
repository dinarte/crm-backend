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
import br.com.esig.edu.crm.dominio.MotivoDesinteresse;
import br.com.esig.edu.crm.dominio.dto.MotivoDesinteresseDTO;
import br.com.esig.edu.crm.service.MotivoDesinteresseService;
import io.swagger.annotations.Api;


@Api(value = "Motido De Dedinteresse - Este recurso prover os endpoints responsáveis por "
		+ "fazer a manutenção dos registro.")
@RestController
@RequestScope
@RequestMapping("/api/motivo-desinteresse")
public class MotivoDesinteresseRestController {
	
	@Autowired
	MotivoDesinteresseService motivoDesinteresseService;
	
	@Autowired
	AutenticacaoService authService;
	
	/**
	 * LISTAGEM: Retorna todas as motivoDesinteresses da instituição do usuário
	 * @param paginacao
	 * @return
	 * @throws AuthenticationException 
	 */
	@SecuredApi(userAuth = true)
	@RequestMapping(method = RequestMethod.GET, value = {"","/"})
	public PaginatedResponse<MotivoDesinteresseDTO> list(Optional<Paginacao> paginacao, String search) throws AuthenticationException {
		return motivoDesinteresseService.getAll(authService.getDados().getInstituicaoId(), search, paginacao.orElse(new Paginacao()));
	}
	
	/**
	 * GET: Retorna a motivoDesinteresse referente ao id informado
	 * @param motivoDesinteresseId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{motivoDesinteresseId}")
	public MotivoDesinteresseDTO get(@PathVariable("motivoDesinteresseId") int motivoDesinteresseId) {
		return motivoDesinteresseService.getOne(motivoDesinteresseId);
	}
	
	/**
	 * ADICIONAR: Adiciona uma nova motivoDesinteresse
	 * @param motivoDesinteresse
	 * @return
	 * @throws AuthenticationException 
	 */
	@RequestMapping(method = RequestMethod.POST, value = {"","/"})
	public ResponseEntity<MotivoDesinteresseDTO> add(@RequestBody @Valid MotivoDesinteresse motivoDesinteresse) throws AuthenticationException {
		motivoDesinteresse.setInstituicao(new Instituicao(authService.getDados().getInstituicaoId()));
		return new ResponseEntity<>(motivoDesinteresseService.create(motivoDesinteresse), HttpStatus.CREATED);
	}

	/**
	 * ALTERAR: Altera uma motivoDesinteresse préexistente
	 * @param motivoDesinteresse
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = {"","/"})
	public ResponseEntity<MotivoDesinteresseDTO> update(@RequestBody @Valid MotivoDesinteresse motivoDesinteresse, BindingResult result) {
		return new ResponseEntity<>(motivoDesinteresseService.update(motivoDesinteresse), HttpStatus.ACCEPTED);
	}
	
	/**
	 * DELETAR: Remove uma motivoDesinteresse
	 * @param motivoDesinteresseId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{motivoDesinteresseId}")
	public ResponseEntity<Object> del(@PathVariable("motivoDesinteresseId") int motivoDesinteresseId) {
		motivoDesinteresseService.delete(motivoDesinteresseId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
}
