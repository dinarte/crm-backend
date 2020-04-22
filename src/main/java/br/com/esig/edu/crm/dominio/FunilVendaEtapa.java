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

import br.com.esig.audit.annotations.CriadoEm;
import br.com.esig.audit.annotations.CriadoPor;
import br.com.esig.edu.crm.comum.dominio.Usuario;


/**
 * Etapas de um funil de vendas que determinam o processo pr onde uma oportunidade passa.
 * @author Dinarte
 */

@Entity
@Table(name = "crm", schema = "funil_venda_etapa")
public class FunilVendaEtapa {
	
	@Id
	@GeneratedValue(generator = "funil_venda_etapa_generator")
	@GenericGenerator(name = "funil_venda_etapa_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "crm.funil_venda_etapa_seq") })
	@Column(name = "id_funil_venda_etapa", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;
	
	private String nome;
	
	private Integer ordem;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_funil_venda_etapa_tipo", unique = false, nullable = false)
	private FunilVendaEtapaTipo tipo;

	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_funil_venda", unique = false, nullable = false)
	private FunilVenda funilVenda;
	
	@CriadoPor
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario_cadastro", unique = false, nullable = true)
	private Usuario usuarioCadastro;
	
	@CriadoEm
	@Column(name="data_cadastro")
	private Date dataCadastro;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public FunilVenda getFunilVenda() {
		return funilVenda;
	}

	public void setFunilVenda(FunilVenda funilVenda) {
		this.funilVenda = funilVenda;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public FunilVendaEtapaTipo getTipo() {
		return tipo;
	}

	public void setTipo(FunilVendaEtapaTipo tipo) {
		this.tipo = tipo;
	}
	
}
