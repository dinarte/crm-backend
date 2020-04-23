package br.com.esig.edu.crm;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.esig.excecoes.ResourceNotFoundException;
import br.com.esig.utils.ValidatorUtil;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

@ControllerAdvice
public class AppExceptionHandler {
	

	@ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<AppError> resourceNotFoundException(ResourceNotFoundException e){
		AppError erro = new AppError(e, HttpStatus.NOT_FOUND);
		if (ValidatorUtil.isEmpty(erro.getMessage()))
	    	erro.setMessage("O recurso que você está tentando acessar não existe ou foi removido.");		
		return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }
	
	
	@ExceptionHandler(value = SignatureException.class)
    public ResponseEntity<AppError> appSignatureExceptionHandler(SignatureException e){
		AppError erro = new AppError(e, HttpStatus.UNAUTHORIZED);
		erro.setMessage("Não é possível conectar ao servidor pois a assinatura não é válida, efetue login novamente.");
		return new ResponseEntity<>(erro, HttpStatus.UNAUTHORIZED);
    }
	
	@ExceptionHandler(value = MalformedJwtException.class)
    public ResponseEntity<AppError> appMalformedJwtExceptionHandler(MalformedJwtException e){
		AppError erro = new AppError(e, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }
	
	
	
	@ExceptionHandler(value = Exception.class)
    public ResponseEntity<AppError> appExceptionHandler(ResourceNotFoundException e){
		e.printStackTrace();
		AppError erro = new AppError(e, HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
