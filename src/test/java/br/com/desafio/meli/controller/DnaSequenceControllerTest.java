package br.com.desafio.meli.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.desafio.meli.dto.DnaRatioResponse;
import br.com.desafio.meli.dto.DnaSequenceRequest;
import br.com.desafio.meli.dto.DnaSequenceResponse;
import br.com.desafio.meli.mocks.MockMatrix;
import br.com.desafio.meli.persistence.model.DnaResult;
import br.com.desafio.meli.persistence.model.DnaType;
import br.com.desafio.meli.repository.DnaPersistenceRepository;
import br.com.desafio.meli.resource.exception.AppException;
import br.com.desafio.meli.resource.exception.BusinessException;
import br.com.desafio.meli.service.DnaPersistenceService;
import br.com.desafio.meli.service.DnaSequenceFinderService;

/**
 * @author Ulisses Pereira da Silva Neto
 * @since 11/2019
 */

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DnaSequenceControllerTest {
	
	@Mock
	private DnaPersistenceRepository dnaPersistenceRepository;

	@Spy
	private DnaSequenceFinderService dnaSequenceFinderService;
	
	@Spy
	private DnaPersistenceService dnaPersistenceService; 
	
	@InjectMocks
	private DnaSequenceController dnaSequenceController;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);		 
	}
	
	@Test
	public void stats() throws BusinessException, AppException {
		long simian = 40l;
		long human  = 100;
		BigDecimal ratio = new BigDecimal(0.4);		
		DnaRatioResponse respose = new DnaRatioResponse(simian, human, ratio);
		
		Mockito.when(dnaPersistenceRepository.countByType(DnaType.HUMAN)).thenReturn(human);
		Mockito.when(dnaPersistenceRepository.countByType(DnaType.SIMIAN)).thenReturn(simian);
		
		Mockito.doReturn(respose).when(dnaPersistenceService).ratio();
		
		DnaRatioResponse stats = dnaSequenceController.stats();		
		assertEquals(respose, stats);
	}
	
	@Test
	public void simianSaved() throws BusinessException, AppException {
		MockMatrix mockMatrix = new MockMatrix();
		DnaSequenceRequest request = new DnaSequenceRequest();
		request.setValues(mockMatrix.getCorrectDna());
		
		DnaResult response = new DnaResult(DnaType.HUMAN);
		
		Mockito.doReturn(response).when(dnaPersistenceService).findResult(request.getValues());
		
		DnaSequenceResponse simian = dnaSequenceController.simian(request);		
		assertEquals(simian, simian);		
	}
	
	@Test
	public void simianNotSaved() throws BusinessException, AppException {
		MockMatrix mockMatrix = new MockMatrix();
		DnaSequenceRequest request = new DnaSequenceRequest();
		request.setValues(mockMatrix.getCorrectDna());
		
		DnaResult response = new DnaResult(DnaType.HUMAN);
		
		Mockito.when(dnaPersistenceRepository.findResult(1)).thenReturn(response);
		Mockito.when(dnaPersistenceRepository.save(new DnaResult())).thenReturn(null);
				
		Mockito.doReturn(null).when(dnaPersistenceService).findResult(request.getValues());
		Mockito.doReturn(true).when(dnaSequenceFinderService).isSimian(request.getValues());
		Mockito.doNothing().when(dnaPersistenceService).saveResult(request.getValues(), true);
				
		DnaSequenceResponse simian = dnaSequenceController.simian(request);		
		assertEquals(true, simian.isSimian());		
	}
	
}
