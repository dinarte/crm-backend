package br.com.esig.edu.crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.esig.edu.crm.comum.dominio.Instituicao;
import br.com.esig.edu.crm.dominio.FormaCaptacao;
import br.com.esig.edu.crm.dominio.FunilVenda;

@Repository
public interface FunilVendaRepository extends CrudRepository<FunilVenda, Integer>, JpaSpecificationExecutor<FunilVenda> {
	
	public Page<FormaCaptacao> findAllByInstituicao(Instituicao instituicao, Pageable page);
	
	public Page<FormaCaptacao> findAll(Pageable page);

	public Long countByInstituicaoId(int instituicaoEnsinoId);

	@Query("select distinct f "
			+ "from FunilVenda as f "
			+ "left join fetch f.etapas as e "
			+ "where f.instituicao.id = :instituicaoId ")
	public List<FunilVenda> findAllfetchEtapasByInstituicaoId(@Param("instituicaoId") Integer instituicaoId);
	
	@Query("select distinct f "
			+ "from FunilVenda as f "
			+ "left join fetch f.etapas as e "
			+ "where f.id = :id ")
	public Optional<FunilVenda> findAllfetchEtapasById(@Param("id") Integer id);
	
}
