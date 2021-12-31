package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Categoria;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Pedido;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/***
 * @author Marcos
 */
@Repository
public class CategoriaRepository {

    List<Categoria> listaCategoria = new ArrayList<>();

    public Categoria salvarCategoria(Categoria categoria) {
        listaCategoria.add(categoria);
        return categoria;
    }

    private Long getMaxId(){
        Long id = 0L;
        for ( Categoria p : listaCategoria ) {
            if (p.getId() != null && p.getId().compareTo(id) > 0 ){
                id = p.getId();
            }
        }
        return id;
    }

    public List<Categoria> listarCategoria(){
        return listaCategoria;
    }
}
