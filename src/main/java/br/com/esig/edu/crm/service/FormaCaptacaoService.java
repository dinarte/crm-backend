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
import br.com.esig.edu.crm.dominio.FormaCaptacao;
import br.com.esig.edu.crm.dominio.dto.FormaCaptacaoDTO;
import br.com.esig.edu.crm.filters.FormaCapatacaoSpecification;
import br.com.esig.edu.crm.filters.api.SpecificationsBuilder;
import br.com.esig.edu.crm.repository.FormaCaptacaoRepository;
import br.com.esig.excecoes.ResourceNotFoundException;

@Service
public class FormaCaptacaoService {

	@Autowired
	FormaCaptacaoRepository formaCaptacaoRespository;
	
	@Autowired
	SpecificationsBuilder specBuilder;
	
	@Autowired
	ModelMapper mapper;
	
	public FormaCaptacaoDTO create(FormaCaptacao formaCaptacao) {
		return mapper.map(formaCaptacaoRespository.save(formaCaptacao), FormaCaptacaoDTO.class);
	}
	
	public FormaCaptacaoDTO update(FormaCaptacao formaCaptacao) {
		throwIfNotExists(formaCaptacao.getId());
		return mapper.map(formaCaptacaoRespository.save(formaCaptacao), FormaCaptacaoDTO.class);
	}
	
	public void delete(int formaCaptacaoId) {
		throwIfNotExists(formaCaptacaoId);
		formaCaptacaoRespository.deleteById(formaCaptacaoId);
	}
	
	@SuppressWarnings("unchecked")
	public PaginatedResponse<FormaCaptacaoDTO> getAll(int instituicaoEnsinoId,  String search, Paginacao paginacao){
		
		specBuilder.with("instituicao", "=", instituicaoEnsinoId);
		Specification<FormaCaptacao> spec = (Specification<FormaCaptacao>) specBuilder.build(FormaCapatacaoSpecification.class, search);
		
		Page<FormaCaptacao> page = formaCaptacaoRespository.findAll(spec, paginacao.toPageable());
		Long count = page.getTotalElements();
		List<FormaCaptacaoDTO> retorno = page.stream().map(it -> mapper.map(it, FormaCaptacaoDTO.class)).collect(Collectors.toList());
		
		paginacao.setData("/forma-captacao", search, count); 
		
		return new PaginatedResponse<FormaCaptacaoDTO>(retorno, paginacao);		
	}

	public FormaCaptacaoDTO getOne(int formaCaptacaoId) {
		throwIfNotExists(formaCaptacaoId);
		return mapper.map( formaCaptacaoRespository
									.findById(formaCaptacaoId)
									.get(), FormaCaptacaoDTO.class);
	}
	
	void throwIfNotExists(int formaCaptacaoId) {
		if ( !formaCaptacaoRespository.existsById(formaCaptacaoId) )
			throw new ResourceNotFoundException("A campaanha que você está tentando acessar não existe ou foi removida.");
	}
}
