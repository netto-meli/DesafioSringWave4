package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.ItemCarrinho;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Pedido;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/*** Classe Repositório de Pedidos
 *
 * @author Felipe
 * @author Fernando Netto
 */
@Repository
public class PedidoRepository implements OurRepository<Pedido, Long>{

    /***
     * Lista com todas Categorias
     */
    List<Pedido> pedidos = new ArrayList<>();
    /***
     * objectMapper para utilização na manipulação do JSON
     */
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    /***
     * PATH contendo o caminho/nome do arquivo JSON
     */
    private final String PATH = "pedidos.json";

    /*** Método que irá salvar Pedido no estoque
     *
     * @param pedido Objeto Pedido a ser persistida
     * @return Pedido persistido
     * @throws ErrorProcesamentoException Exceção ao carregar os JSON em memória.
     */
    public Pedido salva(Pedido pedido) throws ErrorProcesamentoException{
        try {
            /*Mesclar duas ArrayList<>
            * List<String> newList = new ArrayList<String>(listOne);
                newList.addAll(listTwo);
            * */
            pedidos = listagem();
            if ( pedido.getId() == null ) pedido.setId(getMaxId()+1L);
            List<Pedido> novaLista2 =new ArrayList<>();
            novaLista2.add(pedido);
            List<Pedido> novaLista = new ArrayList<>(pedidos);
            novaLista.addAll(novaLista2);
            objectMapper.writeValue(new File(PATH), novaLista);
            return pedido;
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
            objectMapper.writeValue(new File(PATH), pedidos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*** Método que trará a lista de Pedido
     *
     * @return Lista contendo Pedidos
     * @throws ErrorProcesamentoException Exceção ao carregar os JSON em memória.
     */
    public List<Pedido> listagem() throws ErrorProcesamentoException {
        try {
            File file = new File(PATH);
            FileInputStream is = new FileInputStream(file);
            pedidos = new ArrayList<>(Arrays.asList(objectMapper.readValue(is, Pedido[].class)));
            return pedidos;
        } catch (IOException e) {
            throw new ErrorProcesamentoException("Erro ao localizar categoria");
        }
    }

    /*** Método que busca 1 Pedido na lista do repositório
     *
     * @param id ID do Pedido
     * @return Pedido com o ID informado
     */
    public Pedido get(Long id) {
        return pedidos.stream()
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
        for ( Pedido p : pedidos ) {
            if (p.getId() != null && p.getId().compareTo(id) > 0 ){
                id = p.getId();
            }
        }
        return id;
    }

    /*** Método que cria um pedido a partir do carrinho decompras atual do cliente
     *
     * @param listItemCarrinho Lista de itens de produtos no carrinho
     * @param idCliente ID do cliente que está fechando o pedido
     * @param enderecoEntrega Endereço de entrega do cliente para cálculo do frete
     * @return Pedido persistido com nova ID
     * @throws ErrorProcesamentoException Exceção ao carregar os JSON em memória.
     */
    public Pedido criaPedido(List<ItemCarrinho> listItemCarrinho, long idCliente, String enderecoEntrega)
            throws ErrorProcesamentoException{
        Long idPedido = getMaxId()+1L;
        BigDecimal valorPedido = BigDecimal.ZERO;
        BigDecimal valorFrete = BigDecimal.ZERO;
        Pedido p = new Pedido( idPedido, idCliente, listItemCarrinho, valorFrete, valorPedido);
        p.calculaValorTotalPedido(enderecoEntrega);
        salva(p);
        return p;
    }

    /*** Método que adiciona produtos no carrinho de compras do Cliente
     *
     * @param idClt ID do Cliente que está realizando compras
     * @param endereco Endereço de entrega do cliente para cálculo do frete
     * @param carrinho Itens de um produto no carrinho
     * @return Carrinho de compras no formato Pedido
     */
    public Pedido adicionarProdutoNoCarrinho(Long idClt, String endereco, ItemCarrinho carrinho){
        Pedido ped = pedidos.stream()
                .filter(pdcl -> pdcl.getIdCliente().equals(idClt))
                .filter(pd -> Objects.equals(pd.getId(), null))
                .findAny()
                .orElse( criaCarrinhoNovo(idClt) );
        ped.atualizaCarrinho(carrinho, endereco);
        // TODO deve ter um jeito melhor que isso
        pedidos.remove(ped);
        pedidos.add(ped);
        return ped;
    }

    /*** Método que buscao carrinho de compras do cliente
     *
     * @param idClt ID do Cliente que está realizando compras
     * @return Carrinho de compras no formato Pedido
     */
    public Pedido getCarrinho(Long idClt) {
        return pedidos.stream()
                .filter(pdcl -> pdcl.getIdCliente().equals(idClt))
                .filter(pd -> Objects.equals(pd.getId(), null))
                .findAny()
                .orElse( criaCarrinhoNovo(idClt) );
    }

    /*** Método que apaga o carrinho atual do cliente e apresenta um carrinho de compras novo para o cliente
     *
     * @param idClt ID do Cliente que está realizando compras
     */
    public void limparCarrinho(Long idClt){
        pedidos.stream()
                .filter(pdcl -> pdcl.getIdCliente().equals(idClt))
                .filter(pd -> Objects.equals(pd.getId(), null))
                .findAny()
                .orElse( criaCarrinhoNovo(idClt) )
                .setListaItensCarrinho( new ArrayList<>() );
    }

    /*** Método que cria um Carrinho de compras novo
     *
     * @param idClt ID do Cliente que está realizando compras
     * @return Carrinho de compras no formato Pedido
     */
    private Pedido criaCarrinhoNovo(Long idClt){
        return new Pedido(null, idClt, new ArrayList<>(), BigDecimal.ZERO, BigDecimal.ZERO);
    }
}
