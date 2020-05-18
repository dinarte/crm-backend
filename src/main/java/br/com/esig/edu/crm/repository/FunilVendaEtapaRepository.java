package br.com.esig.edu.crm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.esig.edu.crm.dominio.FormaCaptacao;
import br.com.esig.edu.crm.dominio.FunilVendaEtapa;

@Repository
public interface FunilVendaEtapaRepository extends CrudRepository<FunilVendaEtapa, Integer>, JpaSpecificationExecutor<FunilVendaEtapa> {
	
	public Page<FormaCaptacao> findAll(Pageable page);

	@Modifying
	@Query("delete from FunilVendaEtapa f where f not in :etapas and f.funilVenda.id = :funilId")
	public void deleteWhereNotIn(@Param("etapas") List<FunilVendaEtapa> etapas, @Param("funilId") Integer funilId);

	
}
