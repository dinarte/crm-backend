package br.com.esig.edu.crm;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class Paginacao {
	private int offset = 0;
	private int limit = 5;
	
	public int getOffset() {
		return offset;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public int getLimit() {
		return limit;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public Pageable toPageable() {
		return PageRequest.of(offset, limit);
	}
}

