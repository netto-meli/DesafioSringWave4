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

/*** Classe Repositório de Clientes
 *
 * @author Felipe
 * @author Fernando Netto
 */
@Repository
public class ClienteRepository implements OurRepository<Cliente, Long>{

    /***
     * Lista com todas Categorias
     */
    List<Cliente> clientes = new ArrayList<>();
    /***
     * objectMapper para utilização na manipulação do JSON
     */
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    /***
     * PATH contendo o caminho/nome do arquivo JSON
     */
    private final String PATH = "clientes.json";

    /*** Método que irá salvar Cliente na lista
     *
     * @param cliente Objeto Cliente a ser persistida
     * @return Cliente persistido
     * @throws ErrorProcesamentoException Exceção ao carregar os JSON em memória.
     */
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

    /*** Método que irá persistir as alterações realizadas
     *
     * @throws ErrorProcesamentoException Exceção ao carregar os JSON em memória.
     */
    public void grava() throws ErrorProcesamentoException{
        try {
            objectMapper.writeValue(new File(PATH), clientes);
        } catch (IOException e) {
            throw new ErrorProcesamentoException("Erro ao localizar categoria");
        }
    }

    /*** Método que trará a lista de Cliente
     *
     * @return Lista contendo Clientes
     * @throws ErrorProcesamentoException Exceção ao carregar os JSON em memória.
     */
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

    /*** Método que busca 1 Cliente na lista do repositório
     *
     * @param id ID do Cliente
     * @return Cliente com o ID informado
     */
    public Cliente get(Long id) {
        return clientes.stream()
                .filter( c -> c.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    /*** Método que verifica a lista de Categorias e retora o maior ID atual
     *
     * @return ID no formato Long
     */
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
