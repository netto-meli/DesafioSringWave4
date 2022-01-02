package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.ItemCarrinho;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.RepositoryException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*** Classe Repositório de Produtos (Estoque)
 *
 * @author Felipe
 * @author Fernando Netto
 * @author Leonardo
 */
@Repository
@NoArgsConstructor
public class EstoqueRepository implements OurRepository<Produto, Long>{

    /***
     * Lista com todas Categorias
     */
    private List<Produto> produtos = new ArrayList<>();
    /***
     * objectMapper para utilização na manipulação do JSON
     */
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    /***
     * PATH contendo o caminho/nome do arquivo JSON
     */
    private final String PATH = "estoque.json";

    /*** Método que irá salvar Produto no estoque
     *
     * @param produto Objeto Produto a ser persistida
     * @return Produto persistido
     * @throws ErrorProcesamentoException Exceção ao carregar os JSON em memória.
     */
    public Produto salva(Produto produto) throws ErrorProcesamentoException{
        try {
            /*Mesclar duas ArrayList<>
            * List<String> newList = new ArrayList<String>(listOne);
                newList.addAll(listTwo);
            * */
            produtos = listagem();
            if ( produto.getId() == null ) produto.setId(getMaxId()+1L);
            List<Produto> novaLista2 =new ArrayList<>();
            novaLista2.add(produto);
            List<Produto> novaLista = new ArrayList<>(produtos);
            novaLista.addAll(novaLista2);
            objectMapper.writeValue(new File(PATH), novaLista);
            return produto;
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
            objectMapper.writeValue(new File(PATH), produtos);

        } catch (IOException e) {
            throw new ErrorProcesamentoException("Erro ao localizar categoria");
        }
    }

    /*** Método que trará a lista de Produto
     *
     * @return Estoque - (Lista contendo Produtos)
     * @throws ErrorProcesamentoException Exceção ao carregar os JSON em memória.
     */
    public List<Produto> listagem() throws ErrorProcesamentoException {
        try {
            File file = new File(PATH);
            FileInputStream is = new FileInputStream(file);
            produtos = new ArrayList<>(Arrays.asList(objectMapper.readValue(is, Produto[].class)));
            return produtos;
        } catch (IOException e) {
            throw new ErrorProcesamentoException("Erro ao localizar produto");
        }
    }

    /*** Método que busca 1 Produto na lista do repositório
     *
     * @param id ID do Produto
     * @return Produto com o ID informado
     */
    public Produto get(Long id) {
        return produtos.stream()
                .filter(u -> u.getId().equals(id) )
                .findAny()
                .orElse(null); // null se nao existe produto
    }

    /*** Método que verifica a lista de Categorias e retora o maior ID atual
     *
     * @return ID no formato Long
     */
    private Long getMaxId(){
        Long id = 0L;
        for ( Produto p : produtos ) {
            if (p.getId() != null && p.getId().compareTo(id) > 0 ){
                id = p.getId();
            }
        }
        return id;
    }

    /*** Método que grava toda lista de produtos no repositório
     *
     * @param listaProd Lista de produtos para serem persistids em repositório
     * @throws ErrorProcesamentoException Exceção ao carregar os JSON em memória.
     */
    public void salvaLista(List<Produto> listaProd) throws ErrorProcesamentoException{
        for (Produto p : listaProd) {
            salva(p);
        }
    }

    /*** Método que dá baixa de Produtos no estoque
     *
     * @param listItemCarrinho Lista de Produtos com as respectivas quantidades para dar baixa no estoque
     * @throws ErrorProcesamentoException Exceção ao carregar os JSON em memória.
     * @throws RepositoryException Exceção no repositória.
     */
    public void baixarEstoque(List<ItemCarrinho> listItemCarrinho) throws ErrorProcesamentoException, RepositoryException {
        for (ItemCarrinho pdCarrinho : listItemCarrinho) {
            try{
                produtos.stream()
                    .filter( pdEstq -> pdEstq.getId().equals(pdCarrinho.getProduto().getId()))
                    .findFirst()
                    .orElse(null)
                    .baixarEstoque( pdCarrinho.getQuantidade() );
                grava();
            } catch (NullPointerException e) {
                throw new RepositoryException("Não existe produto no estoque para dar baixa.");
            }
        }
    }
}
