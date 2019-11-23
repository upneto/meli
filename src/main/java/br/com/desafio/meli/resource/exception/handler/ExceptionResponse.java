package br.com.desafio.meli.resource.exception.handler;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ulisses Pereira da Silva Neto
 * @since 11/2019
 */
public class ExceptionResponse implements Serializable  {

	/** Serial */
	private static final long serialVersionUID = 5322973812934129022L;
	
	// Hora do erro
	private Date timestamp;	
	// Mensagem do erro
	private String message;
	// Detalhamento do erro 
	private String details;
	
	/**
	 * CONSTRUTOR
	 * @param timestamp
	 * @param message
	 * @param details
	 */
	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	// --------- Getters ----------

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}
	
}
