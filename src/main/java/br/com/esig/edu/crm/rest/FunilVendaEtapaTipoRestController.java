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
import br.com.esig.edu.crm.dominio.FunilVendaEtapaTipo;
import br.com.esig.edu.crm.dominio.dto.FunilVendaEtapaTipoDTO;
import br.com.esig.edu.crm.service.FunilVendaEtapaTipoService;
import io.swagger.annotations.Api;


@Api(value = "Tipo de Etapa do Funil de Vendas - Este recurso prover os endpoints responsáveis por "
		+ "fazer a manutenção dos registros.")
@RestController
@RequestScope
@RequestMapping("/api/funil-venda-etapa-tipo")
public class FunilVendaEtapaTipoRestController {
	
	@Autowired
	FunilVendaEtapaTipoService funilVendaEtapaTipoService;
	
	@Autowired
	AutenticacaoService authService;
	
	/**
	 * LISTAGEM: Retorna todas as funilVendaEtapaTipos da instituição do usuário
	 * @param paginacao
	 * @return
	 * @throws AuthenticationException 
	 */
	@SecuredApi(userAuth = true)
	@RequestMapping(method = RequestMethod.GET, value = {"","/"})
	public PaginatedResponse<FunilVendaEtapaTipoDTO> list(Optional<Paginacao> paginacao, String search) throws AuthenticationException {
		return funilVendaEtapaTipoService.getAll(authService.getDados().getInstituicaoId(), search, paginacao.orElse(new Paginacao()));
	}
	
	/**
	 * GET: Retorna a funilVendaEtapaTipo referente ao id informado
	 * @param funilVendaEtapaTipoId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{funilVendaEtapaTipoId}")
	public FunilVendaEtapaTipoDTO get(@PathVariable("funilVendaEtapaTipoId") int funilVendaEtapaTipoId) {
		return funilVendaEtapaTipoService.getOne(funilVendaEtapaTipoId);
	}
	
	/**
	 * ADICIONAR: Adiciona uma nova funilVendaEtapaTipo
	 * @param funilVendaEtapaTipo
	 * @return
	 * @throws AuthenticationException 
	 */
	@RequestMapping(method = RequestMethod.POST, value = {"","/"})
	public ResponseEntity<FunilVendaEtapaTipoDTO> add(@RequestBody @Valid FunilVendaEtapaTipo funilVendaEtapaTipo) throws AuthenticationException {
		funilVendaEtapaTipo.setInstituicao(new Instituicao(authService.getDados().getInstituicaoId()));
		return new ResponseEntity<>(funilVendaEtapaTipoService.create(funilVendaEtapaTipo), HttpStatus.CREATED);
	}

	/**
	 * ALTERAR: Altera uma funilVendaEtapaTipo préexistente
	 * @param funilVendaEtapaTipo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = {"","/"})
	public ResponseEntity<FunilVendaEtapaTipoDTO> update(@RequestBody @Valid FunilVendaEtapaTipo funilVendaEtapaTipo, BindingResult result) {
		return new ResponseEntity<>(funilVendaEtapaTipoService.update(funilVendaEtapaTipo), HttpStatus.ACCEPTED);
	}
	
	/**
	 * DELETAR: Remove uma funilVendaEtapaTipo
	 * @param funilVendaEtapaTipoId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{funilVendaEtapaTipoId}")
	public ResponseEntity<Object> del(@PathVariable("funilVendaEtapaTipoId") int funilVendaEtapaTipoId) {
		funilVendaEtapaTipoService.delete(funilVendaEtapaTipoId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	/**
	 * UP: Sobe um nível na ordenação
	 * @param funilVendaEtapaTipo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = {"/up"})
	public ResponseEntity<FunilVendaEtapaTipoDTO> up(@RequestBody @Valid FunilVendaEtapaTipo funilVendaEtapaTipo, BindingResult result) {
		return new ResponseEntity<>(funilVendaEtapaTipoService.up(funilVendaEtapaTipo), HttpStatus.ACCEPTED);
	}
	
	/**
	 * DOWN: Sobe um nível na ordenação
	 * @param funilVendaEtapaTipo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = {"/down"})
	public ResponseEntity<FunilVendaEtapaTipoDTO> down(@RequestBody @Valid FunilVendaEtapaTipo funilVendaEtapaTipo, BindingResult result) {
		return new ResponseEntity<>(funilVendaEtapaTipoService.down(funilVendaEtapaTipo), HttpStatus.ACCEPTED);
	}
		
}
