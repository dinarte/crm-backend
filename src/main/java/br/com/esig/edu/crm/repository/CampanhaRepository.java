package br.com.esig.edu.crm.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.esig.edu.crm.dominio.Campanha;
import br.com.esig.edu.crm.dominio.Contato;

@Repository
public interface CampanhaRepository extends CrudRepository<Campanha, Integer>, JpaSpecificationExecutor<Campanha> {
	
}
