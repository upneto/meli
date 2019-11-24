package br.com.desafio.meli.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.desafio.meli.dto.DnaRatioResponse;
import br.com.desafio.meli.mocks.MockMatrix;
import br.com.desafio.meli.persistence.model.DnaResult;
import br.com.desafio.meli.persistence.model.DnaType;
import br.com.desafio.meli.repository.DnaPersistenceRepository;
import br.com.desafio.meli.resource.exception.AppException;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DnaPersistenceServiceTest {

	@InjectMocks
	private DnaPersistenceService dnaPersistenceService;
	
	@Mock
	private DnaPersistenceRepository dnaPersistenceRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);		 
	}
	
	@Test
	public void ratio() {
		long simian = 40l;
		long human  = 100;
		BigDecimal ratio = new BigDecimal(0.4);		
		DnaRatioResponse response = new DnaRatioResponse(simian, human, ratio);
		Mockito.when(dnaPersistenceRepository.countByType(DnaType.HUMAN)).thenReturn(human);
		Mockito.when(dnaPersistenceRepository.countByType(DnaType.SIMIAN)).thenReturn(simian);
		DnaRatioResponse ratioResponse = dnaPersistenceService.ratio();
		assertEquals(response, ratioResponse);
	}
	
	@Test
	public void saveResult() throws AppException {
		MockMatrix mockMatrix = new MockMatrix();		
		int hash = mockMatrix.getCorrectDna().hashCode();
		DnaResult response = new DnaResult(DnaType.SIMIAN, hash);
		dnaPersistenceService.saveResult(mockMatrix.getCorrectDna(), response.getDnaType().equals(DnaType.SIMIAN) ? true : false);
	}
		
	@Test
	public void findResult() throws AppException {
		MockMatrix mockMatrix = new MockMatrix();		
		int hash = mockMatrix.getCorrectDna().hashCode();
		DnaResult response = new DnaResult(hash);
		Mockito.doReturn(response).when(dnaPersistenceRepository).findResult(hash);
		DnaResult findResult = dnaPersistenceService.findResult(mockMatrix.getCorrectDna());
		assertEquals(response, findResult);
	}
	
}
