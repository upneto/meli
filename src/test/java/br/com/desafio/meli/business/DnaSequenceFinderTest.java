package br.com.desafio.meli.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.desafio.meli.mocks.MockMatrix;
import br.com.desafio.meli.resource.exception.AppException;
import br.com.desafio.meli.resource.exception.BusinessException;
import br.com.desafio.meli.service.DnaSequenceFinderService;

@SpringBootTest
class DnaSequenceFinderTest {

	private MockMatrix mockMatrix;
	private DnaSequenceFinderService dnaSequenceFinder;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@BeforeEach
	public void setUp() {
		this.mockMatrix = new MockMatrix();
		this.dnaSequenceFinder = new DnaSequenceFinderService();
	}

	@Test
	public void testBusinessError() {
		this.exception.expect(BusinessException.class);
		try {
			this.dnaSequenceFinder.isSimian(null);			
		} catch (BusinessException e ) {
			this.exception.expect(e.getClass());
		}
		
		try {
			this.dnaSequenceFinder.isSimian(this.mockMatrix.getIncorrectDna());			
		} catch (BusinessException e ) {
			this.exception.expect(e.getClass());
		}
		
		try {
			this.dnaSequenceFinder.isSimian(this.mockMatrix.getIncorrectCharDna());			
		} catch (BusinessException e ) {
			this.exception.expect(e.getClass());
		}		
	}

	@Test
	public void testDnaSequence() throws BusinessException, AppException {
		assertEquals(true, this.dnaSequenceFinder.isSimian(this.mockMatrix.getCorrectDna()));
	}

	@Test
	public void testBigestDnaSequence() throws BusinessException, AppException {
		assertEquals(false, this.dnaSequenceFinder.isSimian(this.mockMatrix.getBigestDnaSequence()));
	}
}
