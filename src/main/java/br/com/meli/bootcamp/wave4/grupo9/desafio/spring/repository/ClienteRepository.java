package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Cliente;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ClienteRepository implements OurRepository<Cliente, Long>{
    List<Cliente> clientes = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "clientes.json";

    public Cliente salva(Cliente cliente) throws ErrorProcesamentoException{
        try {
            /*Mesclar duas ArrayList<>
            * List<String> newList = new ArrayList<String>(listOne);
                newList.addAll(listTwo);
            * */
            clientes = listagem();
            if ( cliente.getId() == null ) cliente.setId(getMaxId()+1L);
            List<Cliente> novaLista2 =new ArrayList<>();
            novaLista2.add(cliente);
            List<Cliente> novaLista = new ArrayList<>(clientes);
            novaLista.addAll(novaLista2);
            objectMapper.writeValue(new File(PATH), novaLista);
            return cliente;
        } catch (IOException e) {
            throw new ErrorProcesamentoException("Erro ao localizar categoria");
        }
    }

    public void grava() {
        try {
            objectMapper.writeValue(new File(PATH), clientes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> listagem() throws ErrorProcesamentoException{
        try {
            File file = new File(PATH);
            FileInputStream is = new FileInputStream(file);
            clientes = new ArrayList<>(Arrays.asList(objectMapper.readValue(is, Cliente[].class)));
            return clientes;
        } catch (IOException e) {
            throw new ErrorProcesamentoException("Erro ao localizar categoria");
        }
    }

    public Cliente get(Long idCliente) {
        return clientes.stream()
                .filter( c -> c.getId().equals(idCliente))
                .findAny()
                .orElse(null);
    }

    //TODO fazer
    public boolean existsById(Long clienteId) {
        return false;
    }

    //TODO fazer
    public void deleteById(Long clienteId) {
    }

    private Long getMaxId(){
        Long id = 0L;
        for ( Cliente c : clientes ) {
            if (c.getId() != null && c.getId().compareTo(id) > 0 ){
                id = c.getId();
            }
        }
        return id;
    }
}
