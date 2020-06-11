package br.com.esig.edu.crm.dominio.dto;

import java.util.Date;

import br.com.esig.mcore.auth.dto.UsuarioDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class AcaoListDTO {
	
	public static String ATIVO = "Interação Ativa";
	public static String PASSIVO = "Interação Passiva";
	public static String INTRENO = "Procedimento Interno";
	
	
	private int id;
	
	private String tipo;

	private LeadDTO lead;
	
	private AcaoCanalDTO canal;
	
	private String resumo;
	
	private String descricao;
	
	private Date dataOperacao;
	
	private UsuarioDTO usuarioOperacao;
	
}
