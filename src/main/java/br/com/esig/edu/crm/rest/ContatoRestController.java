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
import br.com.esig.edu.crm.dominio.Contato;
import br.com.esig.edu.crm.dominio.dto.ContatoDTO;
import br.com.esig.edu.crm.dominio.dto.ContatoListDTO;
import br.com.esig.edu.crm.service.ContatoService;
import io.swagger.annotations.Api;


@Api(value = "Contatos - Este recurso prover os endpoints responsáveis por "
		+ "fazer a manutenção dos registros de contatos de marketing")
@RestController
@RequestScope
@RequestMapping("/api/contato")
public class ContatoRestController {
	
	@Autowired
	ContatoService contatoService;
	
	@Autowired
	AutenticacaoService authService;
	
	/**
	 * LISTAGEM: Retorna todas as contatos da instituição do usuário
	 * @param paginacao
	 * @return
	 * @throws AuthenticationException 
	 */
	@SecuredApi(userAuth = true)
	@RequestMapping(method = RequestMethod.GET, value = {"","/"})
	public PaginatedResponse<ContatoListDTO> list(Optional<Paginacao> paginacao, String search) throws AuthenticationException {
		return contatoService.getAll(authService.getDados().getInstituicaoId(), search, paginacao.orElse(new Paginacao()));
	}
	
	/**
	 * GET: Retorna a contato referente ao id informado
	 * @param contatoId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{contatoId}")
	public ContatoDTO get(@PathVariable("contatoId") int contatoId) {
		return contatoService.getOne(contatoId);
	}
	
	/**
	 * ADICIONAR: Adiciona uma nova contato
	 * @param contato
	 * @return
	 * @throws AuthenticationException 
	 */
	@RequestMapping(method = RequestMethod.POST, value = {"","/"})
	public ResponseEntity<ContatoDTO> add(@RequestBody @Valid Contato contato) throws AuthenticationException {
		contato.setInstituicao(new Instituicao(authService.getDados().getInstituicaoId()));
		return new ResponseEntity<>(contatoService.create(contato), HttpStatus.CREATED);
	}

	/**
	 * ALTERAR: Altera uma contato préexistente
	 * @param contato
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = {"","/"})
	public ResponseEntity<ContatoDTO> update(@RequestBody @Valid Contato contato, BindingResult result) {
		return new ResponseEntity<>(contatoService.update(contato), HttpStatus.ACCEPTED);
	}
	
	/**
	 * DELETAR: Remove uma contato
	 * @param contatoId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{contatoId}")
	public ResponseEntity<Object> del(@PathVariable("contatoId") int contatoId) {
		contatoService.delete(contatoId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
}
