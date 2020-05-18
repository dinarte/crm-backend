package br.com.esig.edu.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.esig.edu.crm.dominio.Contato;
import br.com.esig.edu.crm.dominio.Telefone;

@Repository
public interface TelefoneRepository extends CrudRepository<Telefone, Integer>, JpaSpecificationExecutor<Telefone> {

	List<Telefone> findAllByContato(Contato contato);

	@Modifying
	@Query("delete from Telefone t where t not in :telefones")
	void deleteWhereNotIn(@Param("telefones") List<Telefone> telefones);

	@Modifying
	void deleteAllByContato(Contato contato);
	
}
