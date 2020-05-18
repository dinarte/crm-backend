package br.com.esig.edu.crm.comum.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import br.com.esig.edu.crm.comum.dominio.Municipio;
import br.com.esig.edu.crm.comum.dominio.UnidadeFederativa;
import br.com.esig.edu.crm.comum.service.EnderecoService;

@RestController()
@RequestScope
@RequestMapping("/api/comum/endereco")
public class EnderecoRestController {

	@Autowired
	EnderecoService enderecoService;
	
	@GetMapping("/uf")
	public List<UnidadeFederativa> listUfs(){
		return enderecoService.getUfs();
	}
	
	@GetMapping("/municipio/{ufId}")
	public List<Municipio> listMunicipios(@PathVariable("ufId") Integer ufId){
		return enderecoService.getMunicipiosByUf(new UnidadeFederativa(ufId));
	}
	
}
