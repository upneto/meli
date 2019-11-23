package br.com.desafio.meli.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ulisses Pereira da Silva Neto
 * @since 11/2019
 */
public class DnaRatioResponse {

	// Quantidade de simios consultados
	@JsonProperty(value= "count_simian_dna")	
	private long countSimianDNA;
	
	// Quantidade de humanos consultados
	@JsonProperty(value= "count_human_dna")	
	private long countHumanDNA;
	
	// Proporcao entre simios e humanos
	@JsonProperty(value= "ratio")	
	private BigDecimal ratio;
	
	/**
	 * CONSTRUTOR PADRAO
	 */
	public DnaRatioResponse() {
		super();
	}
		
	/**
	 * CONSTRUTOR
	 * @param countSimianDNA
	 * @param countHumanDNA
	 * @param ratio
	 */
	public DnaRatioResponse(long countSimianDNA, long countHumanDNA, BigDecimal ratio) {
		super();
		this.countSimianDNA = countSimianDNA;
		this.countHumanDNA = countHumanDNA;
		this.ratio = ratio;
	}


	// Getters and setters 

	/**
	 * @return the countSimianDNA
	 */
	public long getCountSimianDNA() {
		return countSimianDNA;
	}

	/**
	 * @param countSimianDNA the countSimianDNA to set
	 */
	public void setCountSimianDNA(long countSimianDNA) {
		this.countSimianDNA = countSimianDNA;
	}

	/**
	 * @return the countHumanDNA
	 */
	public long getCountHumanDNA() {
		return countHumanDNA;
	}

	/**
	 * @param countHumanDNA the countHumanDNA to set
	 */
	public void setCountHumanDNA(long countHumanDNA) {
		this.countHumanDNA = countHumanDNA;
	}

	/**
	 * @return the ratio
	 */
	public BigDecimal getRatio() {
		return ratio;
	}

	/**
	 * @param ratio the ratio to set
	 */
	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}
	
	
}
