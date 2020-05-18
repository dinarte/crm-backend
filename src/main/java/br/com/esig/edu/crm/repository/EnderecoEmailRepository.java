package br.com.esig.edu.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.esig.edu.crm.dominio.Contato;
import br.com.esig.edu.crm.dominio.EnderecoEmail;

@Repository
public interface EnderecoEmailRepository extends CrudRepository<EnderecoEmail, Integer>, JpaSpecificationExecutor<EnderecoEmail> {

	List<EnderecoEmail> findAllByContato(Contato contato);

	@Modifying
	@Query("delete from EnderecoEmail f where f not in :emails")
	public void deleteWhereNotIn(@Param("emails") List<EnderecoEmail> emails);

	@Modifying
	void deleteAllByContato(Contato contato);
	
}
