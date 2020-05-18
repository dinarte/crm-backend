package br.com.esig.edu.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.esig.edu.crm.dominio.Time;
import br.com.esig.edu.crm.dominio.TimeMembro;

@Repository
public interface TimeMembroRepository extends CrudRepository<TimeMembro, Integer> {

	@Modifying
	@Query("delete from TimeMembro t where t not in :membros and t.time = :time")
	void deleteWhereNotIn(@Param("membros") List<TimeMembro> membros, @Param("time") Time time);
	
}
