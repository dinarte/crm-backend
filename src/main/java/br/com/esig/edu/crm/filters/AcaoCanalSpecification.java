package br.com.esig.edu.crm.filters;

import br.com.esig.edu.crm.dominio.AcaoCanal;
import br.com.esig.edu.crm.filters.api.GenericSpecification;
import br.com.esig.edu.crm.filters.api.SearchCriteria;

@SuppressWarnings("serial")
public class AcaoCanalSpecification extends GenericSpecification<AcaoCanal>{

	public AcaoCanalSpecification(SearchCriteria criteria) {
		super(criteria);
	}

}
