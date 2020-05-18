package br.com.esig.edu.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.esig.edu.crm.dominio.dto.IntegracaoProdutoMapper;
import br.com.esig.edu.crm.dominio.dto.ProdutoVO;
import br.com.esig.edu.crm.repository.IntegracaoProdutoRepository;
import lombok.extern.java.Log;

@Service
@Log
public class IntegracaoProdutoService {
	

	@Autowired
	IntegracaoProdutoRepository integracaoProdutoRepository;
	
	public void atualizarProdutosComServicosQuarkEdu(){
		
		log.info("Iniciando a sincronização de prosutos a partir do QuarkEdu");
		log.info("Recuperando dados dos serviços no edu...");
		List<ProdutoVO> produtosVo = integracaoProdutoRepository.findAllServicosFromEdu();
		log.info("Criandao / Atualizando os produtos no CRM...");
		integracaoProdutoRepository.saveAll(IntegracaoProdutoMapper.mapFromVo(produtosVo));
		log.info("Fim da sincronização de prosutos a partir do QuarkEdu");
		
	}

}
