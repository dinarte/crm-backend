package br.com.esig.edu.crm.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.esig.edu.crm.Paginacao;
import br.com.esig.edu.crm.PaginatedResponse;
import br.com.esig.edu.crm.comum.dominio.Instituicao;
import br.com.esig.edu.crm.dominio.FunilVendaEtapaTipo;
import br.com.esig.edu.crm.dominio.dto.FunilVendaEtapaTipoDTO;
import br.com.esig.edu.crm.filters.api.SpecificationsBuilder;
import br.com.esig.edu.crm.repository.FunilVendaEtapaTipoRepository;
import br.com.esig.excecoes.ResourceNotFoundException;

@Service
public class FunilVendaEtapaTipoService {

	@Autowired
	FunilVendaEtapaTipoRepository funilRespository;
	
	@Autowired
	SpecificationsBuilder specBuilder;
	
	@Autowired
	ModelMapper mapper;
	
	public FunilVendaEtapaTipoDTO create(FunilVendaEtapaTipo funilVendaEtapaTipo) {
		Optional<Long> ultimo = funilRespository.findMaxOrdemByInstituicao(funilVendaEtapaTipo.getInstituicao());
		funilVendaEtapaTipo.setOrdem(ultimo.orElse(0l).intValue() + 1);
		return mapper.map(funilRespository.save(funilVendaEtapaTipo), FunilVendaEtapaTipoDTO.class);
	}
	
	public FunilVendaEtapaTipoDTO update(FunilVendaEtapaTipo funilVendaEtapaTipo) {
		throwIfNotExists(funilVendaEtapaTipo.getId());
		return mapper.map(funilRespository.save(funilVendaEtapaTipo), FunilVendaEtapaTipoDTO.class);
	}
	
	public void delete(int funilVendaEtapaTipoId) {
		
		throwIfNotExists(funilVendaEtapaTipoId);
		
		FunilVendaEtapaTipo tipoDeletar = funilRespository.findById(funilVendaEtapaTipoId).get();
		Instituicao instituicao = tipoDeletar.getInstituicao();
		
		funilRespository.deleteById(funilVendaEtapaTipoId);

		List<FunilVendaEtapaTipo> tipos = funilRespository.findAllByInstituicaoOrderByOrdem(instituicao);
		int count = 0;
		for (FunilVendaEtapaTipo t : tipos)
			t.setOrdem(count++);
		funilRespository.saveAll(tipos);
		
	}
	
	public PaginatedResponse<FunilVendaEtapaTipoDTO> getAll(int instituicaoEnsinoId,  String search, Paginacao paginacao){
				
		Pageable pageable = paginacao.toPageable();
		Page<FunilVendaEtapaTipo> page = funilRespository.findAllByInstituicaoOrderByOrdem(new Instituicao(instituicaoEnsinoId), pageable); 
		Long count = page.getTotalElements();
		List<FunilVendaEtapaTipoDTO> retorno = page.stream().map(it -> mapper.map(it, FunilVendaEtapaTipoDTO.class)).collect(Collectors.toList());
		
		paginacao.setData("/funil-venda-etapa-tipo", search, count); 
		
		return new PaginatedResponse<FunilVendaEtapaTipoDTO>(retorno, paginacao);		
	}

	public FunilVendaEtapaTipoDTO getOne(int funilVendaEtapaTipoId) {
		throwIfNotExists(funilVendaEtapaTipoId);
		return mapper.map( funilRespository
									.findById(funilVendaEtapaTipoId)
									.get(), FunilVendaEtapaTipoDTO.class);
	}
	
	/**
	 * Dispara erro caso não exista o item 
	 * @param funilVendaEtapaTipoId
	 */
	void throwIfNotExists(int funilVendaEtapaTipoId) {
		if ( !funilRespository.existsById(funilVendaEtapaTipoId) )
			throw new ResourceNotFoundException("O tipo de etapa de funil que você está tentando acessar não existe ou foi removida.");
	}
	
	/**
	 * Sobe um nível acima na ordenação.
	 * @param funilVendaEtapaTipo
	 * @return
	 */
	public FunilVendaEtapaTipoDTO up(@Valid FunilVendaEtapaTipo funilVendaEtapaTipo) {
		
		throwIfNotExists(funilVendaEtapaTipo.getId());
		
		FunilVendaEtapaTipo next =  funilRespository.findByOrdemAndInstituicao(funilVendaEtapaTipo.getOrdem()-1, funilVendaEtapaTipo.getInstituicao())
				.orElseThrow(() -> new ResourceNotFoundException("Não existe um tipo de etapa em um nível acima") );
		
		next.setOrdem(next.getOrdem()+1);
		funilVendaEtapaTipo.setOrdem(next.getOrdem()-1);
		
		
		funilRespository.save(next);
		funilRespository.save(funilVendaEtapaTipo);
		
		return mapper.map(funilVendaEtapaTipo, FunilVendaEtapaTipoDTO.class);
		
	}
	
	/**
	 * Desce um nível acima na prdenação.
	 * @param funilVendaEtapaTipo
	 * @return
	 */
	public FunilVendaEtapaTipoDTO down(@Valid FunilVendaEtapaTipo funilVendaEtapaTipo) {
		
		throwIfNotExists(funilVendaEtapaTipo.getId());
		
		FunilVendaEtapaTipo previus =  funilRespository.findByOrdemAndInstituicao(funilVendaEtapaTipo.getOrdem()+1, funilVendaEtapaTipo.getInstituicao())
				.orElseThrow(() -> new ResourceNotFoundException("Não existe um tipo de etapa em um nível abaixo") );
		
		previus.setOrdem(previus.getOrdem()-1);
		funilVendaEtapaTipo.setOrdem(previus.getOrdem()+1);
		
		
		funilRespository.save(previus);
		funilRespository.save(funilVendaEtapaTipo);
		
		return mapper.map(funilVendaEtapaTipo, FunilVendaEtapaTipoDTO.class);
		
	}

	
}
