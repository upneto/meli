package br.com.desafio.meli.resource.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.desafio.meli.resource.exception.AppException;
import br.com.desafio.meli.resource.exception.BusinessException;

/**
 * @author Ulisses Pereira da Silva Neto
 * @since 11/2019
 */
@RestController
@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * Manipulador de exceção para erros do tipo 'AppException'
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler(AppException.class)
	public final ResponseEntity<ExceptionResponse> handleAppExceptions(Exception exception, WebRequest request){
		ExceptionResponse exResponse = new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(Boolean.FALSE));
		return new ResponseEntity<ExceptionResponse>(exResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Manipulador de exceção para erros do tipo 'BusinessException'
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler(BusinessException.class)
	public final ResponseEntity<ExceptionResponse> handleBusinessExceptions(Exception exception, WebRequest request){
		ExceptionResponse exResponse = new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(Boolean.FALSE));
		return new ResponseEntity<ExceptionResponse>(exResponse, HttpStatus.BAD_REQUEST);
	}

}
