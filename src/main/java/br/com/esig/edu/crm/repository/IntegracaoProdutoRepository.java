package br.com.esig.edu.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.esig.edu.crm.dominio.Produto;
import br.com.esig.edu.crm.dominio.dto.ProdutoVO;

public interface IntegracaoProdutoRepository extends CrudRepository<Produto, Integer> {
	
	static final String SQL_BASE = 	"select  coalesce(p.id_produto,0) as idProduto,  \r\n" + 
										"		ee.denominacao || ' - ' || s.descricao || ' - ' || t.descricao as descricao, \r\n" + 
										"		ee.denominacao || ' - ' || s.descricao || ' - ' || t.descricao as nome, \r\n" + 
										"		'ANUIDADE' as unidade, \r\n" + 
										"		ps.valor as valor, \r\n" + 
										"		ss.id_instituicao as idInstituicao,\r\n" + 
										"		ss.id_servico_serie as idServicoSerie\r\n" + 
										"from financeiro.servico_serie ss \r\n" + 
										"join financeiro.preco_servico ps using(id_servico)\r\n" + 
										"join basico.serie s using(id_serie)\r\n" + 
										"join basico.etapa_ensino ee using(id_etapa_ensino)\r\n" + 
										"join ensino.turno  t using(id_turno)\r\n" + 
										"left join crm.produto p using(id_servico_serie)\r\n" + 
										"where ps.data_inicio <= now() and ps.data_fim is null\r\n "; 

	@Query(value= SQL_BASE + " and ss.id_instituicao = :instituicaoId", nativeQuery = true)
	public List<ProdutoVO> findAllServicosFromEduByInstituicao(@Param("instituicaoId") Integer instituicaoId);

	@Query(value= SQL_BASE, nativeQuery = true)
	public List<ProdutoVO> findAllServicosFromEdu();
		

	
	
	
}
