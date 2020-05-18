package br.com.esig.edu.crm.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

public interface ProdutoVO {
	
	Integer getId();
	
	Date getDataCatadtro();
	
	String getDescricao();
	
	String getNome();
	
	String getUnidade();
	
	BigDecimal getValor();
	
	Integer getIdInstituicao();
	
	Integer getIdServicoSerie();

}
