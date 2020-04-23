package br.com.esig.edu.crm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.esig.edu.crm.comum.dominio.Instituicao;
import br.com.esig.edu.crm.dominio.Campanha;

@Repository
public interface CampanhaRespository extends CrudRepository<Campanha, Integer> {
	
	public Page<Campanha> findAllByInstituicao(Instituicao instituicao, Pageable page);
	
}
