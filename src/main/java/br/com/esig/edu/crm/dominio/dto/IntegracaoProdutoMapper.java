package br.com.esig.edu.crm.dominio.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.com.esig.edu.crm.comum.dominio.Instituicao;
import br.com.esig.edu.crm.comum.dominio.ServicoSerie;
import br.com.esig.edu.crm.dominio.Produto;

public class IntegracaoProdutoMapper {

	
	public static Produto mapFromVo(ProdutoVO vo){
		Produto produto = new Produto();
		
		produto.setId(vo.getId());
		produto.setDescricao(vo.getDescricao());
		produto.setInstituicao(new Instituicao(vo.getIdInstituicao()));
		produto.setNome(vo.getNome());
		produto.setUnidade(vo.getUnidade());
		produto.setValor(vo.getValor());
		produto.setServicoSerie(new ServicoSerie(vo.getIdServicoSerie()));
		if (vo.getId() == 0)
			produto.setDataCadastro(new Date());
		
		return produto;
	}
	
	public static List<Produto> mapFromVo(List<ProdutoVO> voList){
		return voList.stream().map(vo -> mapFromVo(vo)).collect(Collectors.toList());
	}
}
