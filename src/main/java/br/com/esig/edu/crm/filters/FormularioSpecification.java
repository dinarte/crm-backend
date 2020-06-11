package br.com.esig.edu.crm.filters;

import br.com.esig.edu.crm.dominio.Formulario;
import br.com.esig.edu.crm.filters.api.GenericSpecification;
import br.com.esig.edu.crm.filters.api.SearchCriteria;

@SuppressWarnings("serial")
public class FormularioSpecification extends GenericSpecification<Formulario>{

	public FormularioSpecification(SearchCriteria criteria) {
		super(criteria);
	}

}
