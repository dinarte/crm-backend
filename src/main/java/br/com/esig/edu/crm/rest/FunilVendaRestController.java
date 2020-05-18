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
import br.com.esig.edu.crm.dominio.FunilVenda;
import br.com.esig.edu.crm.dominio.dto.FunilVendaDTO;
import br.com.esig.edu.crm.service.FunilVendaService;
import io.swagger.annotations.Api;


@Api(value = "Funil de Vendas - Este recurso prover os endpoints responsáveis por "
		+ "fazer a manutenção dos registros de funilVendas de marketing")
@RestController
@RequestScope
@RequestMapping("/api/funil-venda")
public class FunilVendaRestController {
	
	@Autowired
	FunilVendaService funilVendaService;
	
	@Autowired
	AutenticacaoService authService;
	
	/**
	 * LISTAGEM: Retorna todas as funilVendas da instituição do usuário
	 * @param paginacao
	 * @return
	 * @throws AuthenticationException 
	 */
	@SecuredApi(userAuth = true)
	@RequestMapping(method = RequestMethod.GET, value = {"","/"})
	public PaginatedResponse<FunilVendaDTO> list(Optional<Paginacao> paginacao, String search) throws AuthenticationException {
		return funilVendaService.getAll(authService.getDados().getInstituicaoId(), search, paginacao.orElse(new Paginacao()));
	}
	
	/**
	 * GET: Retorna a funilVenda referente ao id informado
	 * @param funilVendaId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{funilVendaId}")
	public FunilVendaDTO get(@PathVariable("funilVendaId") int funilVendaId) {
		return funilVendaService.getOne(funilVendaId);
	}
	
	/**
	 * ADICIONAR: Adiciona uma nova funilVenda
	 * @param funilVenda
	 * @return
	 * @throws AuthenticationException 
	 */
	@RequestMapping(method = RequestMethod.POST, value = {"","/"})
	public ResponseEntity<FunilVendaDTO> add(@RequestBody @Valid FunilVenda funilVenda) throws AuthenticationException {
		funilVenda.setInstituicao(new Instituicao(authService.getDados().getInstituicaoId()));
		return new ResponseEntity<>(funilVendaService.create(funilVenda), HttpStatus.CREATED);
	}

	/**
	 * ALTERAR: Altera uma funilVenda préexistente
	 * @param funilVenda
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = {"","/"})
	public ResponseEntity<FunilVendaDTO> update(@RequestBody @Valid FunilVenda funilVenda, BindingResult result) {
		return new ResponseEntity<>(funilVendaService.update(funilVenda), HttpStatus.ACCEPTED);
	}
	
	/**
	 * DELETAR: Remove uma funilVenda
	 * @param funilVendaId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{funilVendaId}")
	public ResponseEntity<Object> del(@PathVariable("funilVendaId") int funilVendaId) {
		funilVendaService.delete(funilVendaId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
}
