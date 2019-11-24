package br.com.desafio.meli.persistence.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Ulisses Pereira da Silva Neto
 * @since 11/2019
 */
@Entity
@Table(name="DNA_RESULTS")
public class DnaResult implements Serializable {

	/** Serial */
	private static final long serialVersionUID = -8642593883806990406L;

	// Chave da tabela
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_RESULT")
	private long idResult;

	// Data da gravacao
	@Column(name="DATA_TIME", nullable=false)
	private Date dataTime;

	// Hash da consulta (Sequencia de DNA)
	@Column(name="HASH_MATRIX", nullable=false, unique=true)
	private int hashMatrix;
	
	// Tipo de sequencia (Simio ou Humano)
	@Enumerated(EnumType.ORDINAL)
	@Column(name="DNA_TYPE", length=2)
	private DnaType dnaType;

	/**
	 * CONSTRUTOR PADRAO
	 */
	public DnaResult() {
		super();
		this.dataTime = new Date();
	}
	
	/**
	 * CONSTRUTOR
	 * @param dnaType
	 */
	public DnaResult(DnaType dnaType) {
		super();
		this.dnaType = dnaType;
		this.dataTime = new Date();
	}
	
	/**
	 * CONSTRUTOR
	 * @param hashMatrix
	 */
	public DnaResult(int hashMatrix) {
		super();
		this.hashMatrix = hashMatrix;
		this.dataTime = new Date();
	}
	
	
	/**
	 * CONSTRUTOR
	 * @param dnaType
	 * @param hashMatrix
	 */
	public DnaResult(DnaType dnaType, int hashMatrix) {
		super();
		this.dnaType = dnaType;
		this.hashMatrix = hashMatrix;
		this.dataTime = new Date();
	}
	
	// ------------- Getters and Setters -----------

	/**
	 * @return the idResult
	 */
	public long getIdResult() {
		return idResult;
	}

	/**
	 * @param idResult the idResult to set
	 */
	public void setIdResult(long idResult) {
		this.idResult = idResult;
	}

	/**
	 * @return the dataTime
	 */
	public Date getDataTime() {
		return dataTime;
	}

	/**
	 * @param dataTime the dataTime to set
	 */
	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}

	/**
	 * @return the hashMatrix
	 */
	public int getHashMatrix() {
		return hashMatrix;
	}

	/**
	 * @param hashMatrix the hashMatrix to set
	 */
	public void setHashMatrix(int hashMatrix) {
		this.hashMatrix = hashMatrix;
	}

	/**
	 * @return the dnaType
	 */
	public DnaType getDnaType() {
		return dnaType;
	}

	/**
	 * @param dnaType the dnaType to set
	 */
	public void setDnaType(DnaType dnaType) {
		this.dnaType = dnaType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hashMatrix;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DnaResult other = (DnaResult) obj;
		if (hashMatrix != other.hashMatrix)
			return false;
		return true;
	}

	
}