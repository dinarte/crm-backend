package br.com.esig.edu.crm.filters.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCriteria {
	private String key;
	private String operation;
	private Object value;
	
	
	public Object getValue() {
	
		try {
			return Double.valueOf(value.toString());
		} catch (Exception e) {
			return value;
		}
		
		
	}
}
	
	

