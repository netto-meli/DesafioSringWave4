package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import java.io.IOException;
import java.util.List;

public interface OurRepository<T,U>{

	void salva(T t) throws IOException;
	List<T> listagem()  throws IOException;
	T get(U id);
}
