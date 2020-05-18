package br.com.esig.edu.crm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.esig.edu.crm.comum.dominio.Instituicao;
import br.com.esig.edu.crm.dominio.FormaCaptacao;

@Repository
public interface FormaCaptacaoRepository extends CrudRepository<FormaCaptacao, Integer>, JpaSpecificationExecutor<FormaCaptacao> {
	
	public Page<FormaCaptacao> findAllByInstituicao(Instituicao instituicao, Pageable page);
	
	public Page<FormaCaptacao> findAll(Pageable page);

	public Long countByInstituicaoId(int instituicaoEnsinoId);
	
}
