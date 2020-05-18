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
import br.com.esig.edu.crm.dominio.Campanha;
import br.com.esig.edu.crm.dominio.FunilVendaEtapaTipo;

@Repository
public interface FunilVendaEtapaTipoRepository extends CrudRepository<FunilVendaEtapaTipo, Integer>, JpaSpecificationExecutor<FunilVendaEtapaTipo> {
	
	public Page<Campanha> findAllByInstituicao(Instituicao instituicao, Pageable page);
	
	public Long countByInstituicaoId(int instituicaoEnsinoId);

	@Query("select max(ordem) from FunilVendaEtapaTipo where instituicao = :instituicao")
	public Optional<Long> findMaxOrdemByInstituicao(@Param("instituicao") Instituicao instituicao);
	
	public Optional<FunilVendaEtapaTipo> findByOrdemAndInstituicao(Integer ordem, Instituicao instituicao);

	public List<FunilVendaEtapaTipo> findAllByInstituicaoOrderByOrdem(Instituicao instituicao);

	public Page<FunilVendaEtapaTipo> findAllByInstituicaoOrderByOrdem(Instituicao instituicao, Pageable pageable);

	
}
