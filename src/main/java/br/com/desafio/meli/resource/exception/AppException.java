package br.com.desafio.meli.resource.exception;

/**
 * @author Ulisses Pereira da Silva Neto
 * @since 11/2019
 */
public class AppException extends Exception {

	/** Serial */
	private static final long serialVersionUID = 2965917055969712499L;
	
	/**
	 * CONSTRUTOR PADRAO
	 */
	public AppException() {
		super();
	}
	
	/**
	 * CONSTRUTOR
	 * @param message
	 */
	public AppException(String message) {
		super(message);
	}
	
	/**
	 * CONSTRUTOR
	 * @param throwable
	 */
	public AppException(Throwable throwable) {
		super(throwable.getMessage(), throwable);
	}
	
	/**
	 * CONSTRUTOR
	 * @param message
	 * @param throwable
	 */
	public AppException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
