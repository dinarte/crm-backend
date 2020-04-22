package br.com.esig.edu.crm.comum.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstituicaoEnsinoDTO {

	private int id;

	private String nome;

	private InstituicaoDTO instituicao;

}
