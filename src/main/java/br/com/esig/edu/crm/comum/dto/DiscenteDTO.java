package br.com.esig.edu.crm.comum.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiscenteDTO {

	private int id;
	private String matricula;
	private StatusDiscenteDTO statusDiscente;
	private PessoaDTO pessoa;
	private InstituicaoEnsinoDTO instituicaoEnsino;

}