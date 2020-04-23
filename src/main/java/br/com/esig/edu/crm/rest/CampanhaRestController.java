package br.com.esig.edu.crm.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.esig.api.auth.SecuredApi;
import br.com.esig.edu.crm.Paginacao;
import br.com.esig.edu.crm.auth.AutenticacaoService;
import br.com.esig.edu.crm.comum.dominio.Instituicao;
import br.com.esig.edu.crm.dominio.Campanha;
import br.com.esig.edu.crm.dominio.dto.CampanhaDTO;
import br.com.esig.edu.crm.repository.CampanhaService;

@RestController
@Scope("request")
@RequestMapping("/api/campanha")
public class CampanhaRestController {
	
	@Autowired
	CampanhaService campanhaService;
	
	@Autowired
	AutenticacaoService authService;
	
	/**
	 * Retorna todas as campanhas da instituição do usuário
	 * @param paginacao
	 * @return
	 */
	@SecuredApi(userAuth = true)
	@RequestMapping(method = RequestMethod.GET, value = {"","/"})
	public List<CampanhaDTO> list(Optional<Paginacao> paginacao) {
		return campanhaService.getAll(authService.getDados().getInstituicaoId(), paginacao.orElse(new Paginacao()));
	}
	
	/**
	 * Retorna a campanha referente ao id informado
	 * @param campanhaId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{campanhaId}")
	public CampanhaDTO get(@PathVariable("campanhaId") int campanhaId) {
		return campanhaService.getOne(campanhaId);
	}
	
	/**
	 * Adiciona uma nova campanha
	 * @param campanha
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = {"","/"})
	public ResponseEntity<CampanhaDTO> add(@RequestBody Campanha campanha) {
		campanha.setInstituicao(new Instituicao(authService.getDados().getInstituicaoId()));
		return new ResponseEntity<>(campanhaService.create(campanha), HttpStatus.CREATED);
	}

	/**
	 * Altera uma campanha préexistente
	 * @param campanha
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = {"","/"})
	public ResponseEntity<CampanhaDTO> update(@RequestBody Campanha campanha) {
		return new ResponseEntity<>(campanhaService.update(campanha), HttpStatus.ACCEPTED);
	}
	
	/**
	 * Remove uma campanha
	 * @param campanhaId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{campanhaId}")
	public ResponseEntity<Object> del(@PathVariable("campanhaId") int campanhaId) {
		campanhaService.delete(campanhaId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
}
