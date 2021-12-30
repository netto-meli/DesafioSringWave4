package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.controller;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto.CategoriaDTO;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Categoria;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/loja")
public class CategoriaController {

    @Autowired
    CategoriaService service;

    @GetMapping(value = "/categorias")
    public ResponseEntity<List<Categoria>> encontrarTodos() {
        List<Categoria> lista = service.encontrarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/categorias/{id}")
    public ResponseEntity<Categoria> encontrarPorId(@PathVariable long id) {
        Categoria obj = service.encontrarPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/categorias")
    public ResponseEntity<Void> inserir(@RequestBody CategoriaDTO objDto) {
        Categoria obj = service.fromDTO(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        service.inserir(obj);
        return ResponseEntity.created(uri).build();
    }
}
