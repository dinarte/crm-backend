package br.com.esig.edu.crm.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.esig.edu.crm.Paginacao;
import br.com.esig.edu.crm.PaginatedResponse;
import br.com.esig.edu.crm.dominio.FunilVenda;
import br.com.esig.edu.crm.dominio.FunilVendaEtapa;
import br.com.esig.edu.crm.dominio.dto.FunilVendaDTO;
import br.com.esig.edu.crm.filters.api.SpecificationsBuilder;
import br.com.esig.edu.crm.repository.FunilVendaEtapaRepository;
import br.com.esig.edu.crm.repository.FunilVendaRepository;
import br.com.esig.excecoes.ResourceNotFoundException;
import br.com.esig.validacao.ValidationException;

@Service
public class FunilVendaService {

	@Autowired
	FunilVendaRepository funilVendaRespository;
	
	@Autowired
	FunilVendaEtapaRepository funilVendaEtapaRepository;
	
	@Autowired
	SpecificationsBuilder specBuilder;
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public FunilVendaDTO create(FunilVenda funilVenda) {
		
		funilVenda.getEtapas().forEach(e ->{
			e.setOrdem(funilVenda.getEtapas().indexOf(e));
		});
		
		funilVendaRespository.save(funilVenda);

		return mapper.map(funilVenda, FunilVendaDTO.class);
	}
	
	@Transactional
	public FunilVendaDTO update(FunilVenda funilVenda) {
	
		if (Objects.isNull(funilVenda.getEtapas()) || funilVenda.getEtapas().size()==0) {
			throw new ValidationException("Não é possível remover todas as etapas, deve haver pelo menos uma.");
		}
		
		
		throwIfNotExists(funilVenda.getId());

		List<FunilVendaEtapa> etapasRemover = funilVenda.getEtapas()
				.stream().filter(e -> e.getId() > 0).collect(Collectors.toList());
		
		if (!Objects.isNull(etapasRemover) && etapasRemover.size()>0)
			funilVendaEtapaRepository.deleteWhereNotIn(etapasRemover, funilVenda.getId());

		funilVenda.getEtapas().forEach(e ->{
			e.setOrdem(funilVenda.getEtapas().indexOf(e));
		});
		
		funilVendaRespository.save(funilVenda);
		
		return mapper.map(funilVenda, FunilVendaDTO.class);
	}
	
	public void delete(int funilVendaId) {
		throwIfNotExists(funilVendaId);
		funilVendaRespository.deleteById(funilVendaId);
	}
	
	
	public PaginatedResponse<FunilVendaDTO> getAll(int instituicaoId,  String search, Paginacao paginacao){
				
		List<FunilVenda> list = funilVendaRespository.findAllfetchEtapasByInstituicaoId(instituicaoId);
		Long count = Long.valueOf(list.size());
		paginacao.setLimit(list.size());
		List<FunilVendaDTO> retorno = list.stream().map(it -> mapper.map(it, FunilVendaDTO.class)).collect(Collectors.toList());
		
		paginacao.setData("/funil-venda", search, count); 
		
		return new PaginatedResponse<FunilVendaDTO>(retorno, paginacao);		
	}

	public FunilVendaDTO getOne(int funilVendaId) {
		throwIfNotExists(funilVendaId);
		return mapper.map( funilVendaRespository
									.findAllfetchEtapasById(funilVendaId)
									.get(), FunilVendaDTO.class);
	}
	
	void throwIfNotExists(int funilVendaId) {
		if ( !funilVendaRespository.existsById(funilVendaId) )
			throw new ResourceNotFoundException("A campaanha que você está tentando acessar não existe ou foi removida.");
	}
}
