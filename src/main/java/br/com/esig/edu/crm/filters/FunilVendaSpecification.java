package br.com.esig.edu.crm.filters;

import br.com.esig.edu.crm.dominio.FunilVenda;
import br.com.esig.edu.crm.filters.api.GenericSpecification;
import br.com.esig.edu.crm.filters.api.SearchCriteria;

@SuppressWarnings("serial")
public class FunilVendaSpecification extends GenericSpecification<FunilVenda>{

	public FunilVendaSpecification(SearchCriteria criteria) {
		super(criteria);
	}

}
