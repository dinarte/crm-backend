package br.com.esig.edu.crm.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.esig.edu.crm.Paginacao;
import br.com.esig.edu.crm.PaginatedResponse;
import br.com.esig.edu.crm.dominio.Acao;
import br.com.esig.edu.crm.dominio.Agendamento;
import br.com.esig.edu.crm.dominio.Lead;
import br.com.esig.edu.crm.dominio.dto.AgendamentoDTO;
import br.com.esig.edu.crm.filters.AgendamentoSpecification;
import br.com.esig.edu.crm.filters.api.SpecificationsBuilder;
import br.com.esig.edu.crm.repository.AgendamentoRepository;
import br.com.esig.excecoes.ResourceNotFoundException;

@Service
public class AgendamentoService {

	@Autowired
	AgendamentoRepository agendamentoRespository;
	
	@Autowired
	SpecificationsBuilder specBuilder;
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public AgendamentoDTO create(Agendamento agendamento) {
		return mapper.map(agendamentoRespository.save(agendamento), AgendamentoDTO.class);
	}
	
	@Transactional
	public AgendamentoDTO update(Agendamento agendamento) {
		throwIfNotExists(agendamento.getId());
		return mapper.map(agendamentoRespository.save(agendamento), AgendamentoDTO.class);
	}
	
	@Transactional
	public void delete(int agendamentoId) {
		throwIfNotExists(agendamentoId);
		agendamentoRespository.deleteById(agendamentoId);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public PaginatedResponse<AgendamentoDTO> getAll(String search, Paginacao paginacao){
		
		Specification<Agendamento> spec = (Specification<Agendamento>) specBuilder.build(AgendamentoSpecification.class, search);
		
		Pageable pageable = paginacao.toPageable();
		Page<Agendamento> page = agendamentoRespository.findAll(spec, pageable);
		Long count = page.getTotalElements();
		List<AgendamentoDTO> retorno = page.stream().map(it -> mapper.map(it, AgendamentoDTO.class)).collect(Collectors.toList());
		
		paginacao.setData("/agendamento", search, count); 
		
		return new PaginatedResponse<AgendamentoDTO>(retorno, paginacao);		
	}
	
	@Transactional
	public List<AgendamentoDTO> getAllByAcao(Acao acao){
		return agendamentoRespository.findAllByAcaoOrderByInicio(acao)
				.stream()
				.map(it -> mapper.map(it, AgendamentoDTO.class)).collect(Collectors.toList());		
	}
	
	
	@Transactional
	public List<AgendamentoDTO> getAllByLead(Lead lead){
		return agendamentoRespository.findAllByAcao_leadOrderByInicio(lead)
				.stream()
				.map(it -> mapper.map(it, AgendamentoDTO.class)).collect(Collectors.toList());		
	}

	@Transactional
	public AgendamentoDTO getOne(int agendamentoId) {
		throwIfNotExists(agendamentoId);
		return mapper.map( agendamentoRespository
									.findById(agendamentoId)
									.get(), AgendamentoDTO.class);
	}
	
	void throwIfNotExists(int agendamentoId) {
		if ( !agendamentoRespository.existsById(agendamentoId) )
			throw new ResourceNotFoundException("A ação que você está tentando acessar não existe ou foi removida.");
	}
}
