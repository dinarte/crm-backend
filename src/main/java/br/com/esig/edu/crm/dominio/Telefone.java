package br.com.esig.edu.crm.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.esig.audit.annotations.CriadoEm;
import br.com.esig.audit.annotations.CriadoPor;
import br.com.esig.edu.crm.comum.dominio.Usuario;
import br.com.esig.edu.crm.dominio.Contato;
import groovy.transform.ToString;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Dinarte
 */

@Entity
@Table(schema = "crm", name = "telefone")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Telefone {

	public static final String PESSOAL = "Pessoal";
	public static final String COMERCIAL = "Comercial";
	public static final String TERCEIRO = "Terceiro";
	public static final String OUTRO = "Outro";
	
	@Id
	@GeneratedValue(generator = "telefone_generator")
	@GenericGenerator(name = "telefone_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "crm.telefone_seq") })
	@Column(name = "id_telefone", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;
	
	private String codigoArea;
	
	private String numero;
	
	private String observacao;
	
	private String tipo;
	
	@JsonBackReference
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_contato", unique = false, nullable = true)
	private Contato contato;
	
	@CriadoPor
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario_cadastro", unique = false, nullable = true)
	private Usuario usuarioCadastro;
	
	@CriadoEm
	@Column(name="data_cadastro")
	private Date dataCadastro;
	
	
	public boolean isPessoal() {
		return tipo.equals(PESSOAL);
	}
	
	public boolean isComercial() {
		return tipo.equals(COMERCIAL);
	}
	
	public boolean isTerceiro() {
		return tipo.equals(TERCEIRO);
	}
	
	public boolean isOutro() {
		return tipo.equals(OUTRO);
	}
	
	public String toString(){
		return super.toString();
	}
	
}
