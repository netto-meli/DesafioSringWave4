package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Cliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepository {
    List<Cliente> listaCliente = new ArrayList<>();

    public Cliente salvarCliente(Cliente cliente) {
        listaCliente.add(cliente);
        return cliente;
    }

    public List<Cliente> listarCliente(){
        return listaCliente;
    }

    public Cliente getCliente(long idCliente) {
        return null;
    }

    public void atualizaPedidoCliente(Cliente cliente) {
    }
}
