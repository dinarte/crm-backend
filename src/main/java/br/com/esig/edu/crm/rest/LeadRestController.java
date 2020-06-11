package br.com.esig.edu.crm.rest;

import java.util.List;
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
import br.com.esig.edu.crm.dominio.Lead;
import br.com.esig.edu.crm.dominio.dto.LeadDTO;
import br.com.esig.edu.crm.service.LeadService;
import br.com.esig.validacao.ValidationException;
import io.swagger.annotations.Api;


@Api(value = "Leads - Este recurso prover os endpoints responsáveis por "
		+ "fazer a manutenção dos registros de leads de marketing")
@RestController
@RequestScope
@RequestMapping("/api/lead")
public class LeadRestController {
	
	@Autowired
	LeadService leadService;
	
	@Autowired
	AutenticacaoService authService;
	
	/**
	 * LISTAGEM: Retorna todas as leads da instituição do usuário
	 * @param paginacao
	 * @return
	 * @throws AuthenticationException 
	 */
	@SecuredApi(userAuth = true)
	@RequestMapping(method = RequestMethod.GET, value = {"","/"})
	public PaginatedResponse<LeadDTO> list(Optional<Paginacao> paginacao, String search) throws AuthenticationException {
		return leadService.getAll(authService.getDados().getInstituicaoId(), search, paginacao.orElse(new Paginacao()));
	}
	
	
	/***
	 * LISTAGEM POR FILTROS: Retorna todas as leads de acordo com os filtros informados na queryString da requisição.
	 * @param etapaId
	 * @param tipoEtapaId
	 * @param timeId
	 * @param campanhaId
	 * @param produtoId
	 * @return
	 * @throws AuthenticationException
	 */
	@SecuredApi(userAuth = true)
	@RequestMapping(method = RequestMethod.GET, value = {"/filters"})
	public List<LeadDTO> listByFilters(  
			Optional<Integer> etapaId, 
			Optional<Integer> tipoEtapaId, 
			Optional<Integer> timeId, 
			Optional<Integer> campanhaId, 
			Optional<Integer> produtoId
			) throws AuthenticationException {
		
		return leadService.getAllByFiltersNoPaginated(
					authService.getDados().getInstituicaoId(), 
					etapaId.orElse(0), 
					tipoEtapaId.orElse(0), 
					timeId.orElse(0), 
					campanhaId.orElse(0), 
					produtoId.orElse(0));
	}
	
		
	/**
	 * GET: Retorna a lead referente ao id informado
	 * @param leadId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{leadId}")
	public LeadDTO get(@PathVariable("leadId") int leadId) {
		return leadService.getOne(leadId);
	}
	
	/**
	 * ADICIONAR: Adiciona uma nova lead
	 * @param lead
	 * @return
	 * @throws AuthenticationException 
	 */
	@RequestMapping(method = RequestMethod.POST, value = {"","/"})
	public ResponseEntity<LeadDTO> add(@RequestBody @Valid Lead lead) throws AuthenticationException {
		lead.setInstituicao(new Instituicao(authService.getDados().getInstituicaoId()));
		return new ResponseEntity<>(leadService.create(lead), HttpStatus.CREATED);
	}

	/**
	 * ALTERAR: Altera uma lead préexistente
	 * @param lead
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = {"","/"})
	public ResponseEntity<LeadDTO> update(@RequestBody @Valid Lead lead, BindingResult result) {
		return new ResponseEntity<>(leadService.update(lead), HttpStatus.ACCEPTED);
	}
	
	

	@RequestMapping(method = RequestMethod.PUT, value = "/mover/{leadId}/{etapaOrigemId}/{etapaDestinoId}")
	public ResponseEntity<LeadDTO> mover(@PathVariable("leadId") Integer leadId, 
			@PathVariable("etapaOrigemId") Integer etapaOrigemId, @PathVariable("etapaDestinoId") Integer etapaDestinoId) {
		
		if (leadId == null || etapaOrigemId == null || etapaDestinoId == null) {
			throw new ValidationException("os paramtros leadId, etapaOrigemId e etapaDestinoId são aguardados da URI fingormada");
		}
		leadService.mover(leadId, etapaOrigemId, etapaDestinoId);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	
	
	/**
	 * DELETAR: Remove uma lead
	 * @param leadId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{leadId}")
	public ResponseEntity<Object> del(@PathVariable("leadId") int leadId) {
		leadService.delete(leadId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
}
