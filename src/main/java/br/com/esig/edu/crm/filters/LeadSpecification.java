package br.com.esig.edu.crm.filters;

import br.com.esig.edu.crm.dominio.Lead;
import br.com.esig.edu.crm.filters.api.GenericSpecification;
import br.com.esig.edu.crm.filters.api.SearchCriteria;

@SuppressWarnings("serial")
public class LeadSpecification extends GenericSpecification<Lead>{

	public LeadSpecification(SearchCriteria criteria) {
		super(criteria);
	}

}
