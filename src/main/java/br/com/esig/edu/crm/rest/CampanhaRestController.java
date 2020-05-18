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
import br.com.esig.edu.crm.dominio.Campanha;
import br.com.esig.edu.crm.dominio.dto.CampanhaDTO;
import br.com.esig.edu.crm.service.CampanhaService;
import io.swagger.annotations.Api;


@Api(value = "Campanhas - Este recurso prover os endpoints responsáveis por "
		+ "fazer a manutenção dos registros de campanhas de marketing")
@RestController
@RequestScope
@RequestMapping("/api/campanha")
public class CampanhaRestController {
	
	@Autowired
	CampanhaService campanhaService;
	
	@Autowired
	AutenticacaoService authService;
	
	/**
	 * LISTAGEM: Retorna todas as campanhas da instituição do usuário
	 * @param paginacao
	 * @return
	 * @throws AuthenticationException 
	 */
	@SecuredApi(userAuth = true)
	@RequestMapping(method = RequestMethod.GET, value = {"","/"})
	public PaginatedResponse<CampanhaDTO> list(Optional<Paginacao> paginacao, String search) throws AuthenticationException {
		return campanhaService.getAll(authService.getDados().getInstituicaoId(), search, paginacao.orElse(new Paginacao()));
	}
	
	/**
	 * GET: Retorna a campanha referente ao id informado
	 * @param campanhaId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{campanhaId}")
	public CampanhaDTO get(@PathVariable("campanhaId") int campanhaId) {
		return campanhaService.getOne(campanhaId);
	}
	
	/**
	 * ADICIONAR: Adiciona uma nova campanha
	 * @param campanha
	 * @return
	 * @throws AuthenticationException 
	 */
	@RequestMapping(method = RequestMethod.POST, value = {"","/"})
	public ResponseEntity<CampanhaDTO> add(@RequestBody @Valid Campanha campanha) throws AuthenticationException {
		campanha.setInstituicao(new Instituicao(authService.getDados().getInstituicaoId()));
		return new ResponseEntity<>(campanhaService.create(campanha), HttpStatus.CREATED);
	}

	/**
	 * ALTERAR: Altera uma campanha préexistente
	 * @param campanha
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = {"","/"})
	public ResponseEntity<CampanhaDTO> update(@RequestBody @Valid Campanha campanha, BindingResult result) {
		return new ResponseEntity<>(campanhaService.update(campanha), HttpStatus.ACCEPTED);
	}
	
	/**
	 * DELETAR: Remove uma campanha
	 * @param campanhaId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{campanhaId}")
	public ResponseEntity<Object> del(@PathVariable("campanhaId") int campanhaId) {
		campanhaService.delete(campanhaId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
}
