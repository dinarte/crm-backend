package br.com.esig.edu.crm.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.esig.edu.crm.dominio.AcaoCanal;

@Repository
public interface AcaoCanalRepository extends CrudRepository<AcaoCanal, Integer>, JpaSpecificationExecutor<AcaoCanal> {
	
}
