package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Categoria;
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

/*** Classe Repositório de Categorias
 *
 * @author Felipe
 * @author Fernando Netto
 */
@Repository
public class CategoriaRepository implements OurRepository<Categoria, Long>{

    /***
     * Lista com todas Categorias
     */
    List<Categoria> categorias = new ArrayList<>();
    /***
     * objectMapper para utilização na manipulação do JSON
     */
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    /***
     * PATH contendo o caminho/nome do arquivo JSON
     */
    private final String PATH = "categorias.json";

    /*** Método que irá salvar Categoria na lista
     *
     * @param categoria Objeto Categoria a ser persistida
     * @return Categoria persistida
     * @throws ErrorProcesamentoException Exceção ao carregar os JSON em memória.
     */
    public Categoria salva(Categoria categoria) throws ErrorProcesamentoException{
        try {
            /*Mesclar duas ArrayList<>
            * List<String> newList = new ArrayList<String>(listOne);
                newList.addAll(listTwo);
            * */
            categorias = listagem();
            if ( categoria.getId() == null ) categoria.setId(getMaxId()+1L);
            List<Categoria> novaLista2 =new ArrayList<>();
            novaLista2.add(categoria);
            List<Categoria> novaLista = new ArrayList<>(categorias);
            novaLista.addAll(novaLista2);
            objectMapper.writeValue(new File(PATH), novaLista);
            return categoria;
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
            objectMapper.writeValue(new File(PATH), categorias);
        } catch (IOException e) {
            throw new ErrorProcesamentoException("Erro ao localizar categoria");
        }
    }

    /*** Método que trará a lista de Categoria
     *
     * @return Lista contendo Categorias
     * @throws ErrorProcesamentoException Exceção ao carregar os JSON em memória.
     */
    public List<Categoria> listagem() throws ErrorProcesamentoException {
        try {
            File file = new File(PATH);
            FileInputStream is = new FileInputStream(file);
            categorias = new ArrayList<>(Arrays.asList(objectMapper.readValue(is, Categoria[].class)));
            return categorias;
        } catch (IOException e) {
            throw new ErrorProcesamentoException("Erro ao localizar categoria");
        }
    }

    /*** Método que busca 1 Categoria na lista do repositório
     *
     * @param id ID da Categoria
     * @return Categoria com o ID informado
     */
    public Categoria get(Long id) {
        return categorias.stream()
                .filter(u -> u.getId().equals(id) )
                .findAny()
                .orElse(null); // null se nao existe produto
    }

    /*** Método que verifica a lista de Categorias e retora o maior ID atual
     *
     * @return ID no formato Long
     */
    public Long getMaxId(){
        Long id = 0L;
        for ( Categoria p : categorias ) {
            if (p.getId() != null && p.getId().compareTo(id) > 0 ){
                id = p.getId();
            }
        }
        return id;
    }
}
