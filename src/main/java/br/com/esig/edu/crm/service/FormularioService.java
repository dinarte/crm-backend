package br.com.esig.edu.crm.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.esig.edu.crm.Paginacao;
import br.com.esig.edu.crm.PaginatedResponse;
import br.com.esig.edu.crm.dominio.Formulario;
import br.com.esig.edu.crm.dominio.dto.FormularioDTO;
import br.com.esig.edu.crm.filters.FormularioSpecification;
import br.com.esig.edu.crm.filters.api.SpecificationsBuilder;
import br.com.esig.edu.crm.repository.FormularioRepository;
import br.com.esig.excecoes.ResourceNotFoundException;

@Transactional
@Service
public class FormularioService {

	@Autowired
	FormularioRepository formularioRespository;
	
	@Autowired
	SpecificationsBuilder specBuilder;
	
	@Autowired
	ModelMapper mapper;
	
	public FormularioDTO create(Formulario formulario) {
		return mapper.map(formularioRespository.save(formulario), FormularioDTO.class);
	}
	
	public FormularioDTO update(Formulario formulario) {
		throwIfNotExists(formulario.getId());
		return mapper.map(formularioRespository.save(formulario), FormularioDTO.class);
	}
	
	public void delete(int formularioId) {
		throwIfNotExists(formularioId);
		formularioRespository.deleteById(formularioId);
	}
	
	
	@SuppressWarnings("unchecked")
	public PaginatedResponse<FormularioDTO> getAll(int instituicaoEnsinoId,  String search, Paginacao paginacao){
		
		specBuilder.with("instituicao", "=", instituicaoEnsinoId);
		Specification<Formulario> spec = (Specification<Formulario>) specBuilder.build(FormularioSpecification.class, search);
		
		Page<Formulario> page = formularioRespository.findAll(spec, paginacao.toPageable());
		Long count = page.getTotalElements();
		List<FormularioDTO> retorno = page.stream().map(it -> mapper.map(it, FormularioDTO.class)).collect(Collectors.toList());
		
		paginacao.setData("/formulario", search, count); 
		
		return new PaginatedResponse<FormularioDTO>(retorno, paginacao);		
	}

	public FormularioDTO getOne(int formularioId) {
		throwIfNotExists(formularioId);
		return mapper.map( formularioRespository
									.findById(formularioId)
									.get(), FormularioDTO.class);
	}
	
	void throwIfNotExists(int formularioId) {
		if ( !formularioRespository.existsById(formularioId) )
			throw new ResourceNotFoundException("A campaanha que você está tentando acessar não existe ou foi removida.");
	}
}
