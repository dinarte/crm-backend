package br.com.esig.edu.crm.dominio.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.esig.mcore.auth.dto.UsuarioDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class TimeMembroDTO {

	private int id;	
	
	@JsonBackReference
	private TimeDTO time;
	
	private UsuarioDTO usuario;

	public TimeMembroDTO(int id) {
		this.id = id;
	}
}
