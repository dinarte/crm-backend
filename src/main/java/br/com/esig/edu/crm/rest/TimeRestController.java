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
import br.com.esig.edu.crm.dominio.Time;
import br.com.esig.edu.crm.dominio.dto.TimeDTO;
import br.com.esig.edu.crm.service.TimeService;
import io.swagger.annotations.Api;


@Api(value = "Times - Este recurso prover os endpoints responsáveis por "
		+ "fazer a manutenção dos registros de times de vendas")
@RestController
@RequestScope
@RequestMapping("/api/time")
public class TimeRestController {
	
	@Autowired
	TimeService timeService;
	
	@Autowired
	AutenticacaoService authService;
	
	/**
	 * LISTAGEM: Retorna todas os times da instituição do usuário
	 * @param paginacao
	 * @return
	 * @throws AuthenticationException 
	 */
	@SecuredApi(userAuth = true)
	@RequestMapping(method = RequestMethod.GET, value = {"","/"})
	public PaginatedResponse<TimeDTO> list(Optional<Paginacao> paginacao, String search) throws AuthenticationException {
		return timeService.getAll(authService.getDados().getInstituicaoId(), search, paginacao.orElse(new Paginacao()));
	}
	
	
	
	/**
	 * LISTAGEM: Retorna todos os times da instituição do usuário em que ele é membro
	 * @param paginacao
	 * @return
	 * @throws AuthenticationException 
	 */
	@SecuredApi(userAuth = true)
	@RequestMapping(method = RequestMethod.GET, value = {"/usuario"})
	public List<TimeDTO> listByUsuario() throws AuthenticationException {
		return timeService.getAllByUsuario(authService.getDados().getInstituicaoId(), authService.getUsuarioLogado());
	}
	
	
	
	/**
	 * GET: Retorna a time referente ao id informado
	 * @param timeId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{timeId}")
	public TimeDTO get(@PathVariable("timeId") int timeId) {
		return timeService.getOne(timeId);
	}
	
	/**
	 * ADICIONAR: Adiciona uma nova time
	 * @param time
	 * @return
	 * @throws AuthenticationException 
	 */
	@RequestMapping(method = RequestMethod.POST, value = {"","/"})
	public ResponseEntity<TimeDTO> add(@RequestBody @Valid Time time) throws AuthenticationException {
		time.setInstituicao(new Instituicao(authService.getDados().getInstituicaoId()));
		return new ResponseEntity<>(timeService.create(time), HttpStatus.CREATED);
	}

	/**
	 * ALTERAR: Altera uma time préexistente
	 * @param time
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = {"","/"})
	public ResponseEntity<TimeDTO> update(@RequestBody @Valid Time time, BindingResult result) {
		return new ResponseEntity<>(timeService.update(time), HttpStatus.ACCEPTED);
	}
	
	/**
	 * DELETAR: Remove uma time
	 * @param timeId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{timeId}")
	public ResponseEntity<Object> del(@PathVariable("timeId") int timeId) {
		timeService.delete(timeId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
}
