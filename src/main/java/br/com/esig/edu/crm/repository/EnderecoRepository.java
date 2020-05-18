package br.com.esig.edu.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.esig.edu.crm.dominio.Contato;
import br.com.esig.edu.crm.dominio.Endereco;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Integer>, JpaSpecificationExecutor<Endereco> {

	List<Endereco> findAllByContato(Contato contato);

	@Modifying
	@Query("delete from Endereco f where f not in :enderecos")
	public void deleteWhereNotIn(@Param("enderecos") List<Endereco> enderecos);

	@Modifying
	void deleteAllByContato(Contato contato);
	
}
