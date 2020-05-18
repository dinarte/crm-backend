package br.com.esig.edu.crm.filters;

import br.com.esig.edu.crm.dominio.Produto;
import br.com.esig.edu.crm.filters.api.GenericSpecification;
import br.com.esig.edu.crm.filters.api.SearchCriteria;

@SuppressWarnings("serial")
public class ProdutoSpecification extends GenericSpecification<Produto>{

	public ProdutoSpecification(SearchCriteria criteria) {
		super(criteria);
	}

}
