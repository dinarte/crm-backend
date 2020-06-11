package br.com.esig.edu.crm.dominio.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.esig.mcore.auth.dto.UsuarioDTO;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AgendamentoDTO {
		
	private int id;
	
	private AcaoListDTO acao;
	
	private String titulo;
	
	private String descricao;
	
	@JsonFormat(pattern = "yyy-MM-dd:hh:mm.000Z")
	private Date inicio;
	
	@JsonFormat(pattern = "yyy-MM-dd:hh:mm.000Z")
	private Date fim;
	
	private String situacao;
	
	private String resultado;
	
	private Date dataResultado;
	
	private UsuarioDTO usuarioRealizacao;
	
	private AcaoListDTO realizacaoAgendamento;
	
	@Override
	public String toString() {
		return super.toString();
	}
}
