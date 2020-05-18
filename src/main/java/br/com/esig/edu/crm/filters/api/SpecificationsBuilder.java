package br.com.esig.edu.crm.filters.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class SpecificationsBuilder {
	
	private static String PATTERN = "(\\w+?)(=|:|<|>|<=|>=|!=|<b>|<i>)(\\w+?),"; 
     
    private final List<SearchCriteria> params;
 
    public SpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }
    
 
    public SpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }
 

	@SuppressWarnings({"unchecked","rawtypes"})
	public Specification<?> build(Class<? extends Specification<?>> clazz, String search) {
		 
	    Pattern pattern = Pattern.compile(PATTERN);
	    Matcher matcher = pattern.matcher(search + ",");
	    while (matcher.find()) {
	        with(matcher.group(1), matcher.group(2), matcher.group(3));
	    }
		
        if (params.size() == 0) {
            return null;
        }
 
        Iterator<SearchCriteria> it = params.iterator();
        
		Specification result;
		try {
			SearchCriteria item = it.next();
			result = Specification.where( clazz.getConstructor(SearchCriteria.class).newInstance(item));
			System.out.println("where " + item.getKey() + item.getOperation() + item.getValue());
			while(it.hasNext()) {
				item = it.next();
	        	result =  result.and((clazz.getConstructor(SearchCriteria.class).newInstance(item)));
	        	System.out.println("and " + item.getKey() + item.getOperation() + item.getValue()); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
        
        
        return result;
    }
	
	public static void main(String[] args) {
		 Pattern pattern = Pattern.compile(PATTERN);
		    Matcher matcher = pattern.matcher("custo_id>500001" + ",");
		    while (matcher.find()) {
		      System.out.println(matcher.group(1));
		      System.out.println(matcher.group(2));
		      System.out.println(matcher.group(3));
		      System.out.println("-------------");		    	
		    }
	}
}