package br.com.esig.edu.crm.comum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.esig.edu.crm.comum.dominio.UnidadeFederativa;

@Repository
public interface UnidadeFederativaRepository extends JpaRepository<UnidadeFederativa, Integer>{
	
	@Query("from UnidadeFederativa order by nome")
	public List<UnidadeFederativa> findAllOrderByNome();

}
