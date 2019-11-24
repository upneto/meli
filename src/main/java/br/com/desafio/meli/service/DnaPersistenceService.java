package br.com.desafio.meli.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.meli.dto.DnaRatioResponse;
import br.com.desafio.meli.persistence.model.DnaResult;
import br.com.desafio.meli.persistence.model.DnaType;
import br.com.desafio.meli.repository.DnaPersistenceRepository;
import br.com.desafio.meli.resource.exception.AppException;

/**
 * @author Ulisses Pereira da Silva Neto
 * @since 11/2019
 */
@Service
public class DnaPersistenceService {
	
	// Classe da API responsavel pela interacao com o BD
	@Autowired
	private DnaPersistenceRepository dnaPersistenceRepository;
	
	/**
	 * Verifica a proporção de simios em uma população de humanos pesquisada
	 * @return
	 */
	public DnaRatioResponse ratio() {
		long simians = this.dnaPersistenceRepository.countByType(DnaType.SIMIAN);
		long humans  = this.dnaPersistenceRepository.countByType(DnaType.HUMAN);
		BigDecimal ratio = new BigDecimal(4).divide(new BigDecimal(9), 3, RoundingMode.HALF_UP); 		
		return new DnaRatioResponse(simians, humans, ratio);
	}
	
	/**
	 * Salva o rersultado da pesquisa
	 * @param dna
	 * @param isSimian
	 * @throws AppException 
	 */
	public void saveResult(List<String> dna, boolean isSimian) throws AppException {
		DnaResult result = new DnaResult();
		result.setDnaType(isSimian ? DnaType.SIMIAN : DnaType.HUMAN);
		result.setHashMatrix(dna.hashCode());
		this.dnaPersistenceRepository.save(result);
	}
	
	/**
	 * Verifica se a sequencia de DNA é existente
	 * @param dna
	 * @return
	 * @throws AppException
	 */
	public DnaResult findResult(List<String> dna) throws AppException {
		return this.dnaPersistenceRepository.findResult(dna.hashCode());		 
	}

}
