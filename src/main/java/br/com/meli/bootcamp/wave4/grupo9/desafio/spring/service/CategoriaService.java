package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto.CategoriaDTO;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Categoria;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repository;

    public List<Categoria> encontrarTodos() {
        return repository.listarCategoria();
    }

    public Categoria encontrarPorId(long id) {
        Optional<Categoria> obj = repository.listarCategoria().stream().filter(x -> x.getId() == id).findFirst();
        return obj.orElse(null);
    }

    public Categoria inserir(Categoria obj) {
        obj.setId(getMaxId()+1);
        return repository.salvarCategoria(obj);
    }

    public Categoria fromDTO(CategoriaDTO objDto) {
        return new Categoria(objDto.getId(), objDto.getNome());
    }

    private Long getMaxId(){
        Long id = 0L;
        for ( Categoria p : repository.listarCategoria() ) {
            if (p.getId() != null && p.getId().compareTo(id) > 0 ){
                id = p.getId();
            }
        }
        return id;
    }
}
