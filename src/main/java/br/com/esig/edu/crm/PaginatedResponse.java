package br.com.esig.edu.crm;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginatedResponse<T> {
	
	private List<T> data;
	
	private Paginacao paginacao;

}
