package br.com.esig.edu.crm.filters;

import br.com.esig.edu.crm.dominio.Time;
import br.com.esig.edu.crm.filters.api.GenericSpecification;
import br.com.esig.edu.crm.filters.api.SearchCriteria;

@SuppressWarnings("serial")
public class TimeSpecification extends GenericSpecification<Time>{

	public TimeSpecification(SearchCriteria criteria) {
		super(criteria);
	}

}
