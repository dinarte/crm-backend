package br.com.esig.edu.crm.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.esig.edu.crm.comum.dto.InstituicaoDTO;
import br.com.esig.edu.crm.dominio.LeadProduto;
import br.com.esig.edu.crm.dominio.PrioridadeEnum;
import br.com.esig.mcore.auth.dto.UsuarioDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class LeadDTO {

	private int id;
	
	private ContatoListDTO contato;
	
	private BigDecimal valor; 
	
	private boolean desinteressado;
	
	private FunilVendaEtapaDTO etapa;
	
	@JsonManagedReference
	private List<LeadProdutoDTO> produtos;

	private CampanhaDTO campanha;
	
	private MotivoDesinteresseDTO motivoDesinteresse;
	
	private FormaCaptacaoDTO formaCapatacao;
		
	private InstituicaoDTO instituicao;
	
	private UsuarioDTO usuarioCadastro;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	private Date dataCadastro;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	private Date dataUltimaInteracao;
	
	private PrioridadeEnum prioridade;
	
	private UsuarioDTO usuarioResponsavel;

}
