package br.com.esig.edu.crm.filters;

import br.com.esig.edu.crm.dominio.Agendamento;
import br.com.esig.edu.crm.filters.api.GenericSpecification;
import br.com.esig.edu.crm.filters.api.SearchCriteria;

@SuppressWarnings("serial")
public class AgendamentoSpecification extends GenericSpecification<Agendamento>{

	public AgendamentoSpecification(SearchCriteria criteria) {
		super(criteria);
	}

}
