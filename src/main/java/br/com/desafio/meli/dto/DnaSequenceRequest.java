package br.com.desafio.meli.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ulisses Pereira da Silva Neto
 * @since 11/2019
 */
public class DnaSequenceRequest {

	// Sequencia de DNA para ser analizada
	@JsonProperty(value= "dna")	
	private List<String> values;

	// ---------- Getters and Setters ---------
	
	/**
	 * @return the values
	 */
	public List<String> getValues() {
		return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(List<String> values) {
		this.values = values;
	}

}
