package br.com.esig.edu.crm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Paginacao {
	
	@Getter @Setter
	private int offset = 0;
	
	@Getter @Setter
	private int limit = 50;
	
	@Getter
	private long count;
	
	@Getter
	private String previus;
	
	@Getter
	private String current;
	
	@Getter
	private String next;
	
	@Getter
	private List<String> pages;

	public Pageable toPageable() {
		int page = offset > limit ? offset / limit : offset;
		int size = limit;
			
		return PageRequest.of(page, size);
	}
	
	public Pageable toPageable(Sort sort) {
		int page = offset > limit ? offset / limit : offset;
		int size = limit;
			
		return PageRequest.of(page, size, sort);
	}
	
	public void setData(String resource, String search, Long count) {
		this.count = count;
		search = search != null ? "&search=" + search : ""; 
		int totalPages = count <= limit ? 1 : count.intValue() / limit;
		totalPages = count > limit && count % limit > 0 ? totalPages+1 : totalPages;
		pages = new ArrayList<String>();
		
		for (int i = 0; i < totalPages; i++) {
			int offset = i == 0 ? i : i * limit + 1;
			String page = resource + "?offset="+offset+"&limit="+limit + search;
			
			pages.add( page );
			
			if (offset == this.offset) {
				current = page;
				if (offset > 0)
					previus = resource + "?offset="+(offset-limit-1)+"&limit="+limit + search;
				if(offset < count.intValue()-limit)
					next = resource + "?offset="+(offset+limit+(offset==0?1:0))+"&limit="+limit + search;
			}	
			
		}
	}
	
	
}

