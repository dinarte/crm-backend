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
import br.com.esig.edu.crm.dominio.MotivoDesinteresse;
import br.com.esig.edu.crm.dominio.dto.MotivoDesinteresseDTO;
import br.com.esig.edu.crm.filters.MotivoDesinteresseSpecification;
import br.com.esig.edu.crm.filters.api.SpecificationsBuilder;
import br.com.esig.edu.crm.repository.MotivoDesinteresseRepository;
import br.com.esig.excecoes.ResourceNotFoundException;

@Service
public class MotivoDesinteresseService {

	@Autowired
	MotivoDesinteresseRepository motivoRespository;
	
	@Autowired
	SpecificationsBuilder specBuilder;
	
	@Autowired
	ModelMapper mapper;
	
	public MotivoDesinteresseDTO create(MotivoDesinteresse motivoDesinteresse) {
		return mapper.map(motivoRespository.save(motivoDesinteresse), MotivoDesinteresseDTO.class);
	}
	
	public MotivoDesinteresseDTO update(MotivoDesinteresse motivoDesinteresse) {
		throwIfNotExists(motivoDesinteresse.getId());
		return mapper.map(motivoRespository.save(motivoDesinteresse), MotivoDesinteresseDTO.class);
	}
	
	public void delete(int motivoDesinteresseId) {
		throwIfNotExists(motivoDesinteresseId);
		motivoRespository.deleteById(motivoDesinteresseId);
	}
	
	@SuppressWarnings("unchecked")
	public PaginatedResponse<MotivoDesinteresseDTO> getAll(int instituicaoEnsinoId,  String search, Paginacao paginacao){
		
		specBuilder.with("instituicao", "=", instituicaoEnsinoId);
		Specification<MotivoDesinteresse> spec = (Specification<MotivoDesinteresse>) specBuilder.build(MotivoDesinteresseSpecification.class, search);
		
		Page<MotivoDesinteresse> page = motivoRespository.findAll(spec, paginacao.toPageable());
		Long count = page.getTotalElements();
		List<MotivoDesinteresseDTO> retorno = page.stream().map(it -> mapper.map(it, MotivoDesinteresseDTO.class)).collect(Collectors.toList());
		
		paginacao.setData("/motivo-desinteresse", search, count); 
		
		return new PaginatedResponse<MotivoDesinteresseDTO>(retorno, paginacao);		
	}

	public MotivoDesinteresseDTO getOne(int motivoDesinteresseId) {
		throwIfNotExists(motivoDesinteresseId);
		return mapper.map( motivoRespository
									.findById(motivoDesinteresseId)
									.get(), MotivoDesinteresseDTO.class);
	}
	
	void throwIfNotExists(int motivoDesinteresseId) {
		if ( !motivoRespository.existsById(motivoDesinteresseId) )
			throw new ResourceNotFoundException("A campaanha que você está tentando acessar não existe ou foi removida.");
	}
}
