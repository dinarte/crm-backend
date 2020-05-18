package br.com.esig.edu.crm.dominio.dto;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.esig.mcore.auth.dto.UsuarioDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * @author Dinarte
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class EnderecoEmailDTO {


	private int id;
	
	private String email;

	@JsonBackReference
	private ContatoDTO contato;
	
	private UsuarioDTO usuarioCadastro;
	
	private Date dataCadastro;

	
	public static void main(String[] args) {

		Class clazz = EnderecoEmailDTO.class;
		
		StringBuffer out = new StringBuffer( "export class " + clazz.getSimpleName() + " {" + "\n\n" );
		out.append("\n");
		Arrays.asList( clazz.getDeclaredFields() ).forEach( f -> {
			out.append("	"+ f.getName() + ": " + f.getType().getSimpleName().replace("DTO", "") + "\n\n");
		
		});
		out.append("}");
		
		System.out.println(out);
	}
	
}
