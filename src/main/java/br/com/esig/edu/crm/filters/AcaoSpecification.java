package br.com.esig.edu.crm.filters;

import br.com.esig.edu.crm.dominio.Acao;
import br.com.esig.edu.crm.filters.api.GenericSpecification;
import br.com.esig.edu.crm.filters.api.SearchCriteria;

@SuppressWarnings("serial")
public class AcaoSpecification extends GenericSpecification<Acao>{

	public AcaoSpecification(SearchCriteria criteria) {
		super(criteria);
	}

}
