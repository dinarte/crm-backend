package br.com.esig.edu.crm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.esig.edu.crm.dominio.FunilVendaEtapa;
import br.com.esig.edu.crm.dominio.FunilVendaEtapaTipo;
import br.com.esig.edu.crm.dominio.Lead;

@Repository
public interface LeadRepository extends CrudRepository<Lead, Integer>, JpaSpecificationExecutor<Lead> {

	Page<Lead> findAllByEtapa(FunilVendaEtapa etapa, Pageable pageable);

	Page<Lead> findAllByEtapa_Tipo(FunilVendaEtapaTipo funilVendaEtapaTipo, Pageable pageable);
	
	@Query(value = "select distinct l.* \r\n" + 
					"from crm.lead l \r\n" + 
					"join crm.funil_venda_etapa e on e.id_funil_venda_etapa = l.id_etapa\r\n" + 
					"join crm.time_membro tm on tm.id_usuario = l.id_usuario_responsavel\r\n" + 
					"left join crm.lead_produto lp using(id_lead)\r\n" + 
					"where l.id_instituicao = :instituicaoId \r\n" + 
					" and (l.id_etapa = :etpaId or 0 = :etpaId) \r\n" + 
					" and (e.id_funil_venda_etapa_tipo = :tipoEtapaId or 0 = :tipoEtapaId) \r\n" + 
					" and (l.id_campanha = :campanhaId or 0 = :campanhaId) \r\n" + 
					" and (lp.id_produto = :produtoId or 0 = :produtoId)\r\n" + 
					" and (tm.id_time = :timeId or 0 = :timeId)",
				nativeQuery = true	
			)	
	List<Lead> findAllByEtapaTtipoEtapaTimeCampanhaProduto(
			@Param("instituicaoId") int instituicaoId,
			@Param("etpaId") int etapaId,
			@Param("tipoEtapaId") int tipoEtapaId,
			@Param("timeId") int timeId,
			@Param("campanhaId") int campanhaId,
			@Param("produtoId") int produtoId);
	
}
