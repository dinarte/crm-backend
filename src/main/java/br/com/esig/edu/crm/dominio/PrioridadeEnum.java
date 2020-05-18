package br.com.esig.edu.crm.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum PrioridadeEnum {
	
	URGENTE(0, "Urgente"),
	
	ALTA(1, "Alta"),
	
	LEVE(2, "Leve"),
	
	NAO_PRIORIZADO(3, "Não priorizado");
	
	
	@Getter @Setter private int ordinal; 
	@Getter @Setter private String descricao;
	

}
