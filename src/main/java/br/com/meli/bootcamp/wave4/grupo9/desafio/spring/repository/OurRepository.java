package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * @author Felipe
 *
 * @param <T> Classe do Repositorio
 * @param <U> Chave do Repositorio
 */
@Repository
public interface OurRepository<T,U>{
	T salva(T t) throws ErrorProcesamentoException;
	List<T> listagem()  throws ErrorProcesamentoException;
	void grava();
	T get(U id);
}
