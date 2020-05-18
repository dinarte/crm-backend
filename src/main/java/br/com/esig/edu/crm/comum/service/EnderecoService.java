package br.com.esig.edu.crm.comum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.esig.edu.crm.comum.dominio.Municipio;
import br.com.esig.edu.crm.comum.dominio.UnidadeFederativa;
import br.com.esig.edu.crm.comum.repository.MunicipioRepository;
import br.com.esig.edu.crm.comum.repository.UnidadeFederativaRepository;

@Service
public class EnderecoService {

	@Autowired
	private UnidadeFederativaRepository ufRepo;
	
	@Autowired
	private MunicipioRepository municipioRepo;
	
	
	public List<UnidadeFederativa> getUfs(){
		return ufRepo.findAllOrderByNome();
	}

	public List<Municipio> getMunicipiosByUf(UnidadeFederativa uf){
		return municipioRepo.findAllByUfOrderByNome(uf);
	}
	
}
