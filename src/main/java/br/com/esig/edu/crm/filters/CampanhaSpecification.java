package br.com.esig.edu.crm.filters;

import br.com.esig.edu.crm.dominio.Campanha;
import br.com.esig.edu.crm.filters.api.GenericSpecification;
import br.com.esig.edu.crm.filters.api.SearchCriteria;

@SuppressWarnings("serial")
public class CampanhaSpecification extends GenericSpecification<Campanha>{

	public CampanhaSpecification(SearchCriteria criteria) {
		super(criteria);
	}

}
