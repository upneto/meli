package br.com.desafio.meli.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ulisses Pereira da Silva Neto
 * @since 11/2019
 */
public class DnaSequenceResponse {
	
	// Resultado da pesquisa se simio ou nao
	@JsonProperty(value= "is_simian")	
	private final boolean simian;
	
	/**
	 * CONSTRUTOR
	 * @param simian
	 */
	public DnaSequenceResponse(boolean simian) {
		super();
		this.simian = simian;
	}

	/**
	 * @return the is_simian
	 */
	public boolean isSimian() {
		return simian;
	}
	
}
