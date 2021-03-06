package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.controller;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto.CategoriaDTO;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Categoria;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.RepositoryException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/***
 * Categoria Controller:<br>
 *  <b>Lista todos as categorias</b><br>
 *  <b>Lista por id</b><br>
 *  <b>Insere Categoria</b><br>
 *
 * @author Marcos Sá
 */
@RestController
@RequestMapping(value = "/loja")
public class CategoriaController {

    /*** Instancia de Categoria: <b>CategoriaService</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    CategoriaService service;

    /***
     *
     * @return endpoint para listar todos as Categorias
     * @throws ErrorProcesamentoException excecao
     */
    @GetMapping(value = "/categorias")
    public ResponseEntity<List<Categoria>> encontrarTodos() throws ErrorProcesamentoException {
            List<Categoria> lista = service.encontrarTodos();
            return ResponseEntity.ok().body(lista);
    }

    /***
     *
     * @param id id
     * @return endpoint para listar todos as Categorias por id
     * @throws ErrorProcesamentoException excecao
     */
    @GetMapping(value = "/categorias/{id}")
    public ResponseEntity<Categoria> encontrarPorId(@PathVariable long id) throws ErrorProcesamentoException {
            Categoria obj = service.encontrarPorId(id);
            return ResponseEntity.ok().body(obj);
    }

    /***
     *
     * @param objeto obj
     * @return endpoint para inserir categorias
     * @throws ErrorProcesamentoException excecao
     * @throws RepositoryException excecao
     */
    @PostMapping(value = "/categorias")
    public ResponseEntity<Void> inserir(@RequestBody CategoriaDTO objeto) throws RepositoryException, ErrorProcesamentoException {
            Categoria obj = CategoriaDTO.converte(objeto);
            service.inserir(obj);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
            return ResponseEntity.created(uri).build();
    }
}
