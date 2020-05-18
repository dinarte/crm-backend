package br.com.esig.edu.crm.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.esig.edu.crm.Paginacao;
import br.com.esig.edu.crm.PaginatedResponse;
import br.com.esig.edu.crm.dominio.Produto;
import br.com.esig.edu.crm.dominio.dto.ProdutoDTO;
import br.com.esig.edu.crm.filters.ProdutoSpecification;
import br.com.esig.edu.crm.filters.api.SpecificationsBuilder;
import br.com.esig.edu.crm.repository.ProdutoRepository;
import br.com.esig.excecoes.ResourceNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRespository;
	
	@Autowired
	SpecificationsBuilder specBuilder;
	
	@Autowired
	ModelMapper mapper;
	
	public ProdutoDTO create(Produto produto) {
		return mapper.map(produtoRespository.save(produto), ProdutoDTO.class);
	}
	
	public ProdutoDTO update(Produto produto) {
		throwIfNotExists(produto.getId());
		return mapper.map(produtoRespository.save(produto), ProdutoDTO.class);
	}
	
	public void delete(int produtoId) {
		throwIfNotExists(produtoId);
		produtoRespository.deleteById(produtoId);
	}
	
	@SuppressWarnings("unchecked")
	public PaginatedResponse<ProdutoDTO> getAll(int instituicaoEnsinoId,  String search, Paginacao paginacao){
		
		specBuilder.with("instituicao", "=", instituicaoEnsinoId);
		Specification<Produto> spec = (Specification<Produto>) specBuilder.build(ProdutoSpecification.class, search);
		
		Page<Produto> page = produtoRespository.findAll(spec, paginacao.toPageable());
		Long count = page.getTotalElements();
		List<ProdutoDTO> retorno = page.stream().map(it -> mapper.map(it, ProdutoDTO.class)).collect(Collectors.toList());
		
		paginacao.setData("/produto", search, count); 
		
		return new PaginatedResponse<ProdutoDTO>(retorno, paginacao);		
	}

	public ProdutoDTO getOne(int produtoId) {
		throwIfNotExists(produtoId);
		return mapper.map( produtoRespository
									.findById(produtoId)
									.get(), ProdutoDTO.class);
	}
	
	void throwIfNotExists(int produtoId) {
		if ( !produtoRespository.existsById(produtoId) )
			throw new ResourceNotFoundException("A campaanha que você está tentando acessar não existe ou foi removida.");
	}
}
