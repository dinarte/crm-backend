package br.com.esig.edu.crm.service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
import br.com.esig.edu.crm.dominio.dto.AcaoDTO;
import br.com.esig.edu.crm.filters.AcaoSpecification;
import br.com.esig.edu.crm.filters.api.SpecificationsBuilder;
import br.com.esig.edu.crm.repository.AcaoRepository;
import br.com.esig.excecoes.ResourceNotFoundException;
import br.com.esig.utils.stream.StreamUtils;

@Service
public class AcaoService {

	@Autowired
	AcaoRepository acaoRespository;
	
	@Autowired
	AgendamentoService agendamentoService;
	
	@Autowired
	LeadService leadService;
	
	@Autowired
	SpecificationsBuilder specBuilder;
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public AcaoDTO create(Acao acao) {
		StreamUtils.ofNullable(acao.getAgendamentos() ).forEach(a -> a.setAcao(acao));
		acaoRespository.save(acao);
		if (Objects.nonNull(acao.getResultadoDoAgendamento()) ) {
			acao.getResultadoDoAgendamento().setRealizacaoAgendamento(acao);
			agendamentoService.update(acao.getResultadoDoAgendamento());
		}

		acao.getLead().setConvertido(acao.isMarcarLeadComoConvertido());	
		acao.getLead().setDesinteressado(acao.isMarcarLeadComoDesinteressado());
		acao.getLead().setMotivoDesinteresse(acao.getMotivoDesinteresse());
		
		acao.getLead().setDataUltimaInteracao(new Date());
		leadService.update(acao.getLead());
		return mapper.map(acao, AcaoDTO.class);
	}
	
	public AcaoDTO update(Acao acao) {
		throwIfNotExists(acao.getId());
		return mapper.map(acaoRespository.save(acao), AcaoDTO.class);
	}
	
	public void delete(int acaoId) {
		throwIfNotExists(acaoId);
		acaoRespository.deleteById(acaoId);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public PaginatedResponse<AcaoDTO> getAll(String search, Paginacao paginacao){
		
		Specification<Acao> spec = (Specification<Acao>) specBuilder.build(AcaoSpecification.class, search);
		
		Pageable pageable = paginacao.toPageable();
		Page<Acao> page = acaoRespository.findAll(spec, pageable);
		Long count = page.getTotalElements();
		List<AcaoDTO> retorno = page.stream().map(it -> mapper.map(it, AcaoDTO.class)).collect(Collectors.toList());
		
		paginacao.setData("/acao", search, count); 
		

		retorno.sort(Comparator.comparing(AcaoDTO::getDataOperacao, (data1, data2) -> {
			return data2.compareTo(data1);
		}));
		
		return new PaginatedResponse<AcaoDTO>(retorno, paginacao);		
	}

	@Transactional
	public AcaoDTO getOne(int acaoId) {
		throwIfNotExists(acaoId);
		return mapper.map( acaoRespository
									.findById(acaoId)
									.get(), AcaoDTO.class);
	}
	
	void throwIfNotExists(int acaoId) {
		if ( !acaoRespository.existsById(acaoId) )
			throw new ResourceNotFoundException("A ação que você está tentando acessar não existe ou foi removida.");
	}
}
