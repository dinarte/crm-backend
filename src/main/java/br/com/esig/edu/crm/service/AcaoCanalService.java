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
import br.com.esig.edu.crm.dominio.AcaoCanal;
import br.com.esig.edu.crm.dominio.dto.AcaoCanalDTO;
import br.com.esig.edu.crm.filters.AcaoCanalSpecification;
import br.com.esig.edu.crm.filters.api.SpecificationsBuilder;
import br.com.esig.edu.crm.repository.AcaoCanalRepository;
import br.com.esig.excecoes.ResourceNotFoundException;

@Service
public class AcaoCanalService {

	@Autowired
	AcaoCanalRepository acaoCanalRespository;
	
	@Autowired
	SpecificationsBuilder specBuilder;
	
	@Autowired
	ModelMapper mapper;
	
	public AcaoCanalDTO create(AcaoCanal acaoCanal) {
		return mapper.map(acaoCanalRespository.save(acaoCanal), AcaoCanalDTO.class);
	}
	
	public AcaoCanalDTO update(AcaoCanal acaoCanal) {
		throwIfNotExists(acaoCanal.getId());
		return mapper.map(acaoCanalRespository.save(acaoCanal), AcaoCanalDTO.class);
	}
	
	public void delete(int acaoCanalId) {
		throwIfNotExists(acaoCanalId);
		acaoCanalRespository.deleteById(acaoCanalId);
	}
	
	@SuppressWarnings("unchecked")
	public PaginatedResponse<AcaoCanalDTO> getAll(int instituicaoId,  String search, Paginacao paginacao){
		
		specBuilder.with("instituicao", "=", instituicaoId);
		Specification<AcaoCanal> spec = (Specification<AcaoCanal>) specBuilder.build(AcaoCanalSpecification.class, search);
		
		Page<AcaoCanal> page = acaoCanalRespository.findAll(spec, paginacao.toPageable());
		Long count = page.getTotalElements();
		List<AcaoCanalDTO> retorno = page.stream().map(it -> mapper.map(it, AcaoCanalDTO.class)).collect(Collectors.toList());
		
		paginacao.setData("/acao-canal", search, count); 
		
		return new PaginatedResponse<AcaoCanalDTO>(retorno, paginacao);		
	}

	public AcaoCanalDTO getOne(int acaoCanalId) {
		throwIfNotExists(acaoCanalId);
		return mapper.map( acaoCanalRespository
									.findById(acaoCanalId)
									.get(), AcaoCanalDTO.class);
	}
	
	void throwIfNotExists(int acaoCanalId) {
		if ( !acaoCanalRespository.existsById(acaoCanalId) )
			throw new ResourceNotFoundException("A ação que você está tentando acessar não existe ou foi removida.");
	}
}
