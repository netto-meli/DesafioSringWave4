package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.controller;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto.ClienteDTO;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Cliente;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loja")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/cliente")
    public List<ClienteDTO> listaCliente(){
        List<Cliente> cliente = clienteRepository.findAll();
        return ClienteDTO.converte(cliente);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente add(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> update(@PathVariable Long clienteId, @RequestBody Cliente cliente){

        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }
        //client DTO
        //cliente.setId(clienteId);
        cliente = clienteRepository.save(cliente);
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

