package br.com.desafio.meli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.meli.dto.DnaRatioResponse;
import br.com.desafio.meli.dto.DnaSequenceRequest;
import br.com.desafio.meli.dto.DnaSequenceResponse;
import br.com.desafio.meli.persistence.model.DnaResult;
import br.com.desafio.meli.persistence.model.DnaType;
import br.com.desafio.meli.resource.exception.AppException;
import br.com.desafio.meli.resource.exception.BusinessException;
import br.com.desafio.meli.service.DnaPersistenceService;
import br.com.desafio.meli.service.DnaSequenceFinderService;

/**
 * @author Ulisses Pereira da Silva Neto
 * @since 11/2019
 */
@RestController
public class DnaSequenceController {
	
	// Servico responsavel pelo algoritimo de consulta de sequencias na matriz 
	@Autowired
	private DnaSequenceFinderService dnaSequenceFinderService;
	
	// Servico responsavel pela interacao com o banco de dados
	@Autowired
	private DnaPersistenceService dnaPersistenceService; 

	/**
	 * Pesquisa a proporção de simios nas consultas já efetuadas 
	 * @return
	 */
	@GetMapping("/stats")
	public DnaRatioResponse stats() {
		return this.dnaPersistenceService.ratio();
	}
	
	/**
	 * Verifica se a sequencia de DNA é humana ou simia
	 * @param name
	 * @return
	 * @throws AppException 
	 * @throws BusinessException 
	 */
	@PostMapping("/simian") 
	public DnaSequenceResponse simian(@RequestBody DnaSequenceRequest dna) throws BusinessException, AppException {
		boolean isSimian = false;
		DnaResult resultSaved = this.dnaPersistenceService.findResult(dna.getValues());
		if(resultSaved == null) {
			isSimian = this.dnaSequenceFinderService.isSimian(dna.getValues());			
			this.dnaPersistenceService.saveResult(dna.getValues(), isSimian);
		} else {
			isSimian = resultSaved.getDnaType().equals(DnaType.SIMIAN);
		}		
		return new DnaSequenceResponse(isSimian);
	}
	
}
