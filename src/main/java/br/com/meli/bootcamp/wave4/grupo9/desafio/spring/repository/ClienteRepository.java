package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Cliente;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD

import java.util.List;



=======
import java.util.ArrayList;
import java.util.List;

>>>>>>> 07adb87bc5c268faef66499fa29b44e752e3ee92
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
        return
    }

    public List<Cliente> findAll() {
        return
    }

    public Cliente save(Cliente cliente) {
        return
    }

    public boolean existsById(Long clienteId) {
        return
    }

    public void deleteById(Long clienteId) {
    }
}
