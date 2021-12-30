package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Cliente;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Pedido;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ClienteRepository {
    List<Cliente> listaCliente = new ArrayList<>();

    public void salvarCiente(Cliente cliente) {
        listaCliente.add(cliente);
    }

    public Cliente getCliente(long idCliente) {
        return listaCliente.stream()
                .filter( c -> Objects.equals(c.getId(), idCliente))
                .findAny()
                .orElse(null);
    }

    public void atualizaPedidoCliente(Cliente cliente) {
        // TODO NÃO É MELHOR OPÇÃO
        // TODO precisa ser otimizado
        listaCliente.remove(cliente);
        listaCliente.add(cliente);
    }

    public List<Cliente> findAll() {
        return null;
    }

    public Cliente save(Cliente cliente) {
        return null;
    }

    public boolean existsById(Long clienteId) {
        return false;
    }

    public void deleteById(Long clienteId) {
    }
}
