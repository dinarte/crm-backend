package br.com.esig.edu.crm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.esig.edu.crm.comum.dominio.Instituicao;
import br.com.esig.edu.crm.dominio.Campanha;
import br.com.esig.edu.crm.dominio.MotivoDesinteresse;

@Repository
public interface MotivoDesinteresseRepository extends CrudRepository<MotivoDesinteresse, Integer>, JpaSpecificationExecutor<MotivoDesinteresse> {
	
	public Page<Campanha> findAllByInstituicao(Instituicao instituicao, Pageable page);
	
	public Page<Campanha> findAll(Pageable page);

	public Long countByInstituicaoId(int instituicaoEnsinoId);
	
}
