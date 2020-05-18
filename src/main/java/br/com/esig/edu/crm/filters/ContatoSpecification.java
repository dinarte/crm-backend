package br.com.esig.edu.crm.filters;

import br.com.esig.edu.crm.dominio.Contato;
import br.com.esig.edu.crm.filters.api.GenericSpecification;
import br.com.esig.edu.crm.filters.api.SearchCriteria;

@SuppressWarnings("serial")
public class ContatoSpecification extends GenericSpecification<Contato>{

	public ContatoSpecification(SearchCriteria criteria) {
		super(criteria);
	}

}
