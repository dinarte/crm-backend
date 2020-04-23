package br.com.esig.edu.crm;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AppError {

	private Date timestamp = new Date();

	@NonNull
	private String error;
	
	@NonNull
	private Integer status;
	
	@NonNull
	private String message;
	
	private String trace;
	
	public AppError(Exception e, HttpStatus status) {
		
		this.error = status.name();
		this.status = status.value();
		this.message = e.getMessage();		
		StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        trace = sw.toString();
        
	}
	
	
}
