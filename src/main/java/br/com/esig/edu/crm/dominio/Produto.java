package br.com.esig.edu.crm.dominio;

import java.math.BigDecimal;
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

import br.com.esig.audit.annotations.CriadoEm;
import br.com.esig.audit.annotations.CriadoPor;
import br.com.esig.edu.crm.comum.dominio.Instituicao;
import br.com.esig.edu.crm.comum.dominio.Serie;
import br.com.esig.edu.crm.comum.dominio.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Produtos e serviços oferecidos pela empresa, no caso do educacional, 
 * poderá ser mapeado aqui as séries, cursos e modalidades de ensino. 
 * Cada produto terá uma tabela de preço pré-definida.
 * @author Dinarte
 */
@Entity
@Table(schema = "crm", name = "produto")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Produto {

	public static final String UNIDADE_ANUIDADE = "ANUIDADE";
	public static final String UNIDADE_MENSALIDADE = "MENSALIDADE";
	public static final String UNIDADE_UNIDADE = "UNIDADE";
	
	@Id
	@GeneratedValue(generator = "produtoGenerator")
	@GenericGenerator(name = "produtoGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "crm.produto_seq") })
	@Column(name = "id_produto", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="valor")
	private BigDecimal valor;
	
	private String unidade = UNIDADE_ANUIDADE;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_serie", unique = false, nullable = true)
	private Serie serie;

	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_instituicao", unique = false, nullable = false)
	@br.com.esig.audit.annotations.Instituicao
	private Instituicao instituicao;
	
	@CriadoPor
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario_cadastro", unique = false, nullable = true)
	private Usuario usuarioCadastro;
	
	@CriadoEm
	@Column(name="data_cadastro")
	private Date dataCadastro;
	
	public boolean isAnuidade() {
		return unidade.equals(UNIDADE_ANUIDADE);
	}
	
	public boolean isMensalidade() {
		return unidade.equals(UNIDADE_MENSALIDADE);
	}
	
	public boolean isUnidade() {
		return unidade.equals(UNIDADE_UNIDADE);
	}
	
}
