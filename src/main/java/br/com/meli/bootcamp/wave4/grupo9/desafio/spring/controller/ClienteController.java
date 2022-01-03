package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.controller;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto.ClienteDTO;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Cliente;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.RepositoryException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
 * @author Rafael
 * @author Fernando
 * @author Marcos
 */
@RestController
@RequestMapping("/loja")
public class ClienteController {

    /*** Instancia de Cliente: <b>ClienteService</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    private ClienteService service;

    /***
     *
     * @return endpoint para listar todos as Categorias
     */
    @GetMapping(value = "/clientes")
    public ResponseEntity<List<Cliente>> encontrarTodos()  {
        try {
            List<Cliente> lista = service.encontrarTodos();
            return ResponseEntity.ok().body(lista);
        } catch (ErrorProcesamentoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /***
     *
     * @param id id
     * @return endpoint para listar todos os Clientes por id
     */
    @GetMapping(value = "/clientes/{id}")
    public ResponseEntity<Cliente> encontrarPorId(@PathVariable long id) {
        try {
            Cliente obj = service.encontrarPorId(id);
            return ResponseEntity.ok().body(obj);
        } catch (ErrorProcesamentoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /***
     *
     * @param objeto obj
     * @return endpoint para inserir cliente
     */
    @PostMapping(value = "/clientes")
    public ResponseEntity<Void> inserir(@RequestBody ClienteDTO objeto) {
        try {
            Cliente obj = ClienteDTO.converte(objeto);
            service.inserir(obj);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
            return ResponseEntity.created(uri).build();
        } catch (ErrorProcesamentoException | RepositoryException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /*** Lista de clientes ordenado por estado (crescente ou decrescente)
     *
     * @param ordem
     * 0 - Id Cliente crescente
     * 1 - Id Cliente decrescente
     * @return Lista de clientes
     */
    @GetMapping(value = "/ordenaEstadoCliente/{ordem}")
    public ResponseEntity<List<Cliente>> ordenacaoLista(@PathVariable Integer ordem) {
        try {
            List<Cliente> lista = service.ordenarLista(ordem);
            return ResponseEntity.ok(lista);
        } catch (ErrorProcesamentoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }
}

