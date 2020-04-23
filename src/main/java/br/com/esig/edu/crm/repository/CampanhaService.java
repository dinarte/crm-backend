package br.com.esig.edu.crm.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.esig.edu.crm.Paginacao;
import br.com.esig.edu.crm.comum.dominio.Instituicao;
import br.com.esig.edu.crm.dominio.Campanha;
import br.com.esig.edu.crm.dominio.dto.CampanhaDTO;
import br.com.esig.excecoes.ResourceNotFoundException;

@Service
public class CampanhaService {

	@Autowired
	CampanhaRespository campanhaRespository;
	
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
	
	public List<CampanhaDTO> getAll(int instituicaoEnsinoId,  Paginacao paginacao){
		return campanhaRespository.findAllByInstituicao(new Instituicao(instituicaoEnsinoId), paginacao.toPageable()).stream()
				.map(it -> mapper.map(it, CampanhaDTO.class)).collect(Collectors.toList());
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
