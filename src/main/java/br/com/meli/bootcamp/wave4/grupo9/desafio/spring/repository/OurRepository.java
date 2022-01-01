package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * @author Felipe
 *
 * @param <T> Classe do repositorio
 * @param <U> Chave do Repositorio
 */
@Repository
public interface OurRepository<T,U>{
	T salva(T t);
	void grava();
	List<T> listagem();
	T get(U id);
}
