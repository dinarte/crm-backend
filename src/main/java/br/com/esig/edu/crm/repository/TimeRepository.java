package br.com.esig.edu.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.esig.edu.crm.comum.dominio.Instituicao;
import br.com.esig.edu.crm.comum.dominio.Usuario;
import br.com.esig.edu.crm.dominio.Time;

@Repository
public interface TimeRepository extends CrudRepository<Time, Integer>, JpaSpecificationExecutor<Time> {

	@Query("select tm.time from TimeMembro tm where tm.usuario = :usuario and tm.time.instituicao = :instituicao")
	List<Time> findAllByUsuario(@Param("usuario") Usuario usuario, @Param("instituicao") Instituicao instituicao);
	
}
