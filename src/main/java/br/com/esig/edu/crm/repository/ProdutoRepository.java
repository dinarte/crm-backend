package br.com.esig.edu.crm.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.esig.edu.crm.dominio.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Integer>, JpaSpecificationExecutor<Produto> {
		
}
