package br.com.esig.edu.crm.filters;

import br.com.esig.edu.crm.dominio.FormaCaptacao;
import br.com.esig.edu.crm.filters.api.GenericSpecification;
import br.com.esig.edu.crm.filters.api.SearchCriteria;

@SuppressWarnings("serial")
public class FormaCapatacaoSpecification extends GenericSpecification<FormaCaptacao>{

	public FormaCapatacaoSpecification(SearchCriteria criteria) {
		super(criteria);
	}

}
