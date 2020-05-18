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
import br.com.esig.edu.crm.dominio.Produto;
import br.com.esig.edu.crm.dominio.dto.ProdutoDTO;
import br.com.esig.edu.crm.service.ProdutoService;
import io.swagger.annotations.Api;


@Api(value = "Produtos - Este recurso prover os endpoints responsáveis por "
		+ "fazer a manutenção dos registros de produtos")
@RestController
@RequestScope
@RequestMapping("/api/produto")
public class ProdutoRestController {
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	AutenticacaoService authService;
	
	/**
	 * LISTAGEM: Retorna todas as produtos da instituição do usuário
	 * @param paginacao
	 * @return
	 * @throws AuthenticationException 
	 */
	@SecuredApi(userAuth = true)
	@RequestMapping(method = RequestMethod.GET, value = {"","/"})
	public PaginatedResponse<ProdutoDTO> list(Optional<Paginacao> paginacao, String search) throws AuthenticationException {
		return produtoService.getAll(authService.getDados().getInstituicaoId(), search, paginacao.orElse(new Paginacao()));
	}
	
	/**
	 * GET: Retorna a produto referente ao id informado
	 * @param produtoId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{produtoId}")
	public ProdutoDTO get(@PathVariable("produtoId") int produtoId) {
		return produtoService.getOne(produtoId);
	}
	
	/**
	 * ADICIONAR: Adiciona uma nova produto
	 * @param produto
	 * @return
	 * @throws AuthenticationException 
	 */
	@RequestMapping(method = RequestMethod.POST, value = {"","/"})
	public ResponseEntity<ProdutoDTO> add(@RequestBody @Valid Produto produto) throws AuthenticationException {
		produto.setInstituicao(new Instituicao(authService.getDados().getInstituicaoId()));
		return new ResponseEntity<>(produtoService.create(produto), HttpStatus.CREATED);
	}

	/**
	 * ALTERAR: Altera uma produto préexistente
	 * @param produto
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = {"","/"})
	public ResponseEntity<ProdutoDTO> update(@RequestBody @Valid Produto produto, BindingResult result) {
		return new ResponseEntity<>(produtoService.update(produto), HttpStatus.ACCEPTED);
	}
	
	/**
	 * DELETAR: Remove uma produto
	 * @param produtoId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{produtoId}")
	public ResponseEntity<Object> del(@PathVariable("produtoId") int produtoId) {
		produtoService.delete(produtoId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
}
