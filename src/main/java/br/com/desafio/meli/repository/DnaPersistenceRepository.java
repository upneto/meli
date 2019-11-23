package br.com.desafio.meli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.desafio.meli.persistence.model.DnaResult;
import br.com.desafio.meli.persistence.model.DnaType;

/**
 * @author Ulisses Pereira da Silva Neto
 * @since 11/2019
 */
@Repository
public interface DnaPersistenceRepository extends JpaRepository<DnaResult, String> {

	/**
	 * Verifica se a matriz já é existente
	 * @param matrix
	 * @return
	 */
	@Query("SELECT rst FROM DnaResult rst WHERE rst.hashMatrix = ?1")
	DnaResult findResult(int hashMatrix);
	
	/**
	 * Conta o total de sequencias de DNA guardadas
	 * @param dnaType
	 * @return
	 */
	@Query("SELECT count(rst.dnaType) FROM DnaResult rst WHERE rst.dnaType = ?1")
	long countByType(DnaType dnaType);
}
