package br.com.desafio.meli.resource.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Ulisses Pereira da Silva Neto
 * @since 11/2019
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

	/** Serial */
	private static final long serialVersionUID = -2326997925673630411L;
	
	/**
	 * CONSTRUTOR
	 * @param message
	 */
	public BusinessException(String message) {
		super(message);
	}
	
	/**
	 * CONSTRUTOR
	 * @param throwable
	 */
	public BusinessException(Throwable throwable) {
		super(throwable.getMessage(), throwable);
	}
	
	/**
	 * CONSTRUTOR
	 * @param message
	 * @param throwable
	 */
	public BusinessException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
