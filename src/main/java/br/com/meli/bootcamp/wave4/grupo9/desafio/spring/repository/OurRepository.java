package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import org.springframework.stereotype.Repository;

import java.util.List;

/*** Repositório Intrface
 *
 * @author Felipe
 * @author Fernando Netto
 *
 * @param <T> Classe do Repositório
 * @param <U> Chave do Repositório
 */
@Repository
public interface OurRepository<T,U>{
	/*** Assinatura do método que irá salvar o Objeto T, com a chave U
	 *
	 * @param t Objeto a ser persistido
	 * @return Objeto Persistido
	 * @throws ErrorProcesamentoException Exceção ao carregar os JSON em memória.
	 */
	T salva(T t) throws ErrorProcesamentoException;

	/*** Assinatura do método que irá persistir as alterações realizadas
	 *
	 * @throws ErrorProcesamentoException Exceção ao carregar os JSON em memória.
	 */
	void grava() throws ErrorProcesamentoException;

	/*** Assinatura do método que trará a lista de Objetos T
	 *
	 * @return Lista contendo Objeto T
	 * @throws ErrorProcesamentoException Exceção ao carregar os JSON em memória.
	 */
	List<T> listagem() throws ErrorProcesamentoException;

	/*** Assinatura do método que busca 1 Objeto na lista do repositório
	 *
	 * @param id ID do Objeto
	 * @return Objeto com a ID informada
	 */
	T get(U id);
}
