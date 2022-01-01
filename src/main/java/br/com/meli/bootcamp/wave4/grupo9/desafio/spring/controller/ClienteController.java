package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.controller;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto.ClienteDTO;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Cliente;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * @author Rafael
 */
@RestController
@RequestMapping("/loja")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/cliente")
    public List<ClienteDTO> listaCliente() throws ErrorProcesamentoException {
        List<Cliente> cliente = clienteRepository.listagem();
        return ClienteDTO.converte(cliente);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente add(@RequestBody Cliente cliente) throws ErrorProcesamentoException{
        return clienteRepository.salva(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> update(@PathVariable Long clienteId, @RequestBody Cliente cliente) throws ErrorProcesamentoException{

        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }
        //client DTO
        //cliente.setId(clienteId);
        cliente = clienteRepository.salva(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long clienteId){

        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }

        clienteRepository.deleteById(clienteId);
        return ResponseEntity.noContent().build();
    }


    }

