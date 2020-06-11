package br.com.esig.edu.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.esig.edu.crm.dominio.Acao;
import br.com.esig.edu.crm.dominio.Agendamento;
import br.com.esig.edu.crm.dominio.Lead;

@Repository
public interface AgendamentoRepository extends CrudRepository<Agendamento, Integer>, JpaSpecificationExecutor<Agendamento> {
	
	
	List<Agendamento> findAllByAcaoOrderByInicio(Acao acao);
	
	List<Agendamento> findAllByAcao_leadOrderByInicio(Lead lead);
	
}
