package br.com.esig.quaredu.crm.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.esig.edu.crm.Paginacao;
import br.com.esig.edu.crm.QuarkEduCrmApp;
import br.com.esig.edu.crm.dominio.Lead;
import br.com.esig.edu.crm.filters.LeadSpecification;
import br.com.esig.edu.crm.filters.api.SpecificationsBuilder;
import br.com.esig.edu.crm.repository.LeadRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = QuarkEduCrmApp.class)
public class LeadRepositoryTest {
	
	@Autowired
	LeadRepository repo;
	
	@Autowired
	SpecificationsBuilder specBuilder;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void findAllUsingEspec() {
	
		specBuilder.with("instituicao", "=", 4564);
		Specification<Lead> spec = (Specification<Lead>) specBuilder.build(LeadSpecification.class, null);
		Paginacao paginacao = new Paginacao();
		
		Page<Lead> page = repo.findAll(spec, paginacao.toPageable());
		
		page.getContent().forEach(l -> System.out.println(l));
		 
	}

}
