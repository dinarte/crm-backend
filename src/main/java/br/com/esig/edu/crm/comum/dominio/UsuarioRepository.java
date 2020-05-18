package br.com.esig.edu.crm.comum.dominio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Usuario findByLoginAndPassword(String login, String senha);

	Usuario findByLogin(String login);
	
	@Query(value="select u.* \n" + 
				"	from comum.usuario u\n" + 
				"	join comum.instituicao i using(instituicao_grupo_id)\n" + 
				"	where i.id = :instituicaoId\n" + 
				"	and id_solis is not null", nativeQuery = true)
	List<Usuario> findAllAdmByInstituicaoId(@Param("instituicaoId") Integer instituicaoId);
	
}
