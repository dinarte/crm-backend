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
import br.com.esig.edu.crm.dominio.Acao;
import br.com.esig.edu.crm.dominio.Agendamento;
import br.com.esig.edu.crm.dominio.Lead;
import br.com.esig.edu.crm.dominio.dto.AgendamentoDTO;
import br.com.esig.edu.crm.service.AgendamentoService;
import io.swagger.annotations.Api;


@Api(value = "Ações - Este recurso prover os endpoints responsáveis por "
		+ "fazer a manutenção dos registros de Ações")
@RestController
@RequestScope
@RequestMapping("/api/agendamento")
public class AgendamentoRestController {
	
	@Autowired
	AgendamentoService agendamentoService;
	
	@Autowired
	AutenticacaoService authService;
	
	/**
	 * LISTAGEM: Retorna todas as agendamentos da instituição do usuário
	 * @param paginagendamento
	 * @return
	 * @throws AuthenticationException 
	 */
	@SecuredApi(userAuth = true)
	@RequestMapping(method = RequestMethod.GET, value = {"","/"})
	public PaginatedResponse<AgendamentoDTO> list(Optional<Paginacao> paginagendamento, String search) throws AuthenticationException {
		return agendamentoService.getAll(search, paginagendamento.orElse(new Paginacao()));
	}
	
	
	
	@SecuredApi(userAuth = true)
	@RequestMapping(method = RequestMethod.GET, value = "/acao/{acaoId}")
	public List<AgendamentoDTO> listByAcao(@PathVariable("acaoId") Integer acaoId) throws AuthenticationException {
		return agendamentoService.getAllByAcao(new Acao(acaoId));
	}
	
	@SecuredApi(userAuth = true)
	@RequestMapping(method = RequestMethod.GET, value = "/lead/{leadId}")
	public List<AgendamentoDTO> listByLead(@PathVariable("leadId") Integer leadId) throws AuthenticationException {
		return agendamentoService.getAllByLead(new Lead(leadId));
	}
	
	
	/**
	 * GET: Retorna a agendamento referente ao id informado
	 * @param agendamentoId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{agendamentoId}")
	public AgendamentoDTO get(@PathVariable("agendamentoId") int agendamentoId) {
		return agendamentoService.getOne(agendamentoId);
	}
	
	/**
	 * ADICIONAR: Adiciona uma nova agendamento
	 * @param agendamento
	 * @return
	 * @throws AuthenticationException 
	 */
	@RequestMapping(method = RequestMethod.POST, value = {"","/"})
	public ResponseEntity<AgendamentoDTO> add(@RequestBody @Valid Agendamento agendamento) throws AuthenticationException {
		return new ResponseEntity<>(agendamentoService.create(agendamento), HttpStatus.CREATED);
	}

	/**
	 * ALTERAR: Altera uma agendamento préexistente
	 * @param agendamento
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = {"","/"})
	public ResponseEntity<AgendamentoDTO> update(@RequestBody @Valid Agendamento agendamento, BindingResult result) {
		agendamento.setUsuarioRealizacao(authService.getUsuarioLogado());
		return new ResponseEntity<>(agendamentoService.update(agendamento), HttpStatus.ACCEPTED);
	}
	
	/**
	 * DELETAR: Remove uma agendamento
	 * @param agendamentoId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{agendamentoId}")
	public ResponseEntity<Object> del(@PathVariable("agendamentoId") int agendamentoId) {
		agendamentoService.delete(agendamentoId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
}
