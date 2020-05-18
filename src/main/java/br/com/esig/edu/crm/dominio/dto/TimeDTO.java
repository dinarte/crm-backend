package br.com.esig.edu.crm.dominio.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.esig.edu.crm.comum.dto.InstituicaoDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class TimeDTO {

	private int id;
	
	private String nome;
	
	private String descricao;
	
	@JsonManagedReference
	private List<TimeMembroDTO> membros;
	
	private InstituicaoDTO instituicao;
	
	public TimeDTO(int id) {
		this.id = id;
	}
}
