package br.com.esig.edu.crm.dominio.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class FunilVendaListDTO {

	private int id;
	
	private String nome;
		
}
