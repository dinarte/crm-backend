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
import br.com.esig.edu.crm.dominio.Campanha;
import br.com.esig.edu.crm.dominio.dto.CampanhaDTO;
import br.com.esig.edu.crm.filters.CampanhaSpecification;
import br.com.esig.edu.crm.filters.api.SpecificationsBuilder;
import br.com.esig.edu.crm.repository.CampanhaRepository;
import br.com.esig.excecoes.ResourceNotFoundException;

@Service
public class CampanhaService {

	@Autowired
	CampanhaRepository campanhaRespository;
	
	@Autowired
	SpecificationsBuilder specBuilder;
	
	@Autowired
	ModelMapper mapper;
	
	public CampanhaDTO create(Campanha campanha) {
		return mapper.map(campanhaRespository.save(campanha), CampanhaDTO.class);
	}
	
	public CampanhaDTO update(Campanha campanha) {
		throwIfNotExists(campanha.getId());
		return mapper.map(campanhaRespository.save(campanha), CampanhaDTO.class);
	}
	
	public void delete(int campanhaId) {
		throwIfNotExists(campanhaId);
		campanhaRespository.deleteById(campanhaId);
	}
	
	@SuppressWarnings("unchecked")
	public PaginatedResponse<CampanhaDTO> getAll(int instituicaoEnsinoId,  String search, Paginacao paginacao){
		
		specBuilder.with("instituicao", "=", instituicaoEnsinoId);
		Specification<Campanha> spec = (Specification<Campanha>) specBuilder.build(CampanhaSpecification.class, search);
		
		Page<Campanha> page = campanhaRespository.findAll(spec, paginacao.toPageable());
		Long count = page.getTotalElements();
		List<CampanhaDTO> retorno = page.stream().map(it -> mapper.map(it, CampanhaDTO.class)).collect(Collectors.toList());
		
		paginacao.setData("/campanha", search, count); 
		
		return new PaginatedResponse<CampanhaDTO>(retorno, paginacao);		
	}

	public CampanhaDTO getOne(int campanhaId) {
		throwIfNotExists(campanhaId);
		return mapper.map( campanhaRespository
									.findById(campanhaId)
									.get(), CampanhaDTO.class);
	}
	
	void throwIfNotExists(int campanhaId) {
		if ( !campanhaRespository.existsById(campanhaId) )
			throw new ResourceNotFoundException("A campaanha que você está tentando acessar não existe ou foi removida.");
	}
}
