package br.com.esig.edu.crm.comum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.esig.edu.crm.comum.dominio.Municipio;
import br.com.esig.edu.crm.comum.dominio.UnidadeFederativa;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Integer>{
	
	public List<Municipio> findAllByUfOrderByNome(UnidadeFederativa uf);

}
