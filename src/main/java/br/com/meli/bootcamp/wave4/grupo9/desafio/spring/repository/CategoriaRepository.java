package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Categoria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoriaRepository {

    List<Categoria> listaCategoria = new ArrayList<>();

    public Categoria salvarCategoria(Categoria categoria) {
        listaCategoria.add(categoria);
        return categoria;
    }

    public List<Categoria> listarCategoria(){
        return listaCategoria;
    }
}
