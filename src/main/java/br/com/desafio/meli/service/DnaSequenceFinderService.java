package br.com.desafio.meli.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.desafio.meli.resource.exception.AppException;
import br.com.desafio.meli.resource.exception.BusinessException;

/**
 * @author Ulisses Pereira da Silva Neto
 * @since 11/2019
 */
@Service
public class DnaSequenceFinderService {

	// Matriz de DNA
	private char[][] matrix;
	// Contador de sequencias	
	private int countSequence;
	// Tamanho da sequencia
	private static final int SIZE_OF_SEQUENCE = 4;
	// Letras pesquisadas 
	private static final String WORDS =  "ATCG";
		
	/**
	 * Prepara a matriz de dados
	 * @param dna
	 */
	private void setMatrix(List<String> values) {
		if(values == null || values.isEmpty()) {
			throw new BusinessException("A matriz de DNA não pode ser vazia!");
		}		
		int size = values.size(); // Tamanho matriz quadrada NxN
		this.matrix = new char[size][size];
		for (int i = 0; i < values.size(); i++) {
			if (values.get(i).length() != values.size()) {
				throw new BusinessException("A matriz deve ser quadrada NxN!");
			} 
			for (int y = 0; y < values.get(i).length(); y++) {
				Character charac = values.get(i).toUpperCase().charAt(y);
				if(!WORDS.contains(String.valueOf(charac)) ) {
					throw new BusinessException("O conteúdo da matriz não é válido!");
				}
				this.matrix[i][y] = charac;
			}
		}
	}
	
	/**
	 * Procura por sequencias
	 * @param row
	 * @param col
	 * @param word
	 * @return
	 */
	private boolean searchSequence(int row, int col, Character word) {
		boolean isSequenced = Boolean.FALSE;
		 
		if (word.equals( matrix[row][col] )) {
			
			final int[] X_POSITION = {  0, 0, 1, 1,  1 };
			final int[] Y_POSITION = { -1, 1, 1, 0, -1};
			
			for(int directionIndex = 0; directionIndex < 5; directionIndex++) { 
				if( this.find(row, col, word, X_POSITION[directionIndex], Y_POSITION[directionIndex]) ) {
					isSequenced = Boolean.TRUE;
					break;
				}
			}
			
		}
		
		return isSequenced;
	}

	/**
	 * Procura a sequencia
	 * @param row
	 * @param col
	 * @param word
	 * @param xDirection
	 * @param yDirection
	 * @return
	 */
	private boolean find(int row, int col, Character word, int xDirection, int yDirection) {
		
		boolean isSequenced = false;
		int charFinded = 1; 
		
		for (int goTo = 0; goTo < SIZE_OF_SEQUENCE - 1; goTo++) {
			
			int rowDirection = row + xDirection + (goTo * xDirection);
			int colDirection = col + yDirection + (goTo * yDirection);
				
			// Para o loop se a direcao não estiver no rage da matriz ou se o caracter nao for o pesquisado
			if ( rowDirection >= this.matrix.length || rowDirection < 0 || colDirection >= this.matrix.length || colDirection < 0 
					|| !word.equals( matrix[rowDirection][colDirection] )) {					
				break;
			}
			
			// Incrementa a sequencia encontrada
			charFinded++;
			
			// Verdadeiro se a sequencia possuir o tamanho desejado
			if (charFinded == SIZE_OF_SEQUENCE) {
				isSequenced = Boolean.TRUE;
			}
		}
		
		return isSequenced;
	}

	/**
	 * Conta sequencias encontradas 
	 * @param index
	 */
	private void countSequence(Character word) {		
		firstLoop:
		for (int row = 0; row < this.matrix.length; row++) {
			for (int col = 0; col < this.matrix.length; col++) {
				if (this.searchSequence(row, col, word)) {
					System.out.println("Sequencia encontrada! Linha: " + row + " Coluna: " + col);
					this.countSequence++;
					if(this.countSequence >= 2) {
						break firstLoop;
					}
				}
			}
		}
	}
	
	/**
	 * Executa verificacao
	 * @param values
	 * @throws AppException 
	 */
	public boolean isSimian(List<String> dna) throws BusinessException {
		this.countSequence = 0;
		this.setMatrix(dna);		
		for(int i = 0; i < WORDS.length(); i++) {			
			this.countSequence(WORDS.charAt(i));
		}		
		return (this.countSequence >= 2);
	}		
}
