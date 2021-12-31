package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto.CategoriaDTO;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Categoria;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repository;

    public List<Categoria> encontrarTodos() {
        return repository.listagem();
    }

    public Categoria encontrarPorId(long id) {
        return repository.listagem().stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void inserir(Categoria obj) {
        // TODO ver isso
        //obj.seId(repository.listarCategoria().size());
        repository.salva(obj);
    }

    public Categoria fromDTO(CategoriaDTO objDto) {
        return new Categoria(objDto.getId(), objDto.getNome());
    }

}
