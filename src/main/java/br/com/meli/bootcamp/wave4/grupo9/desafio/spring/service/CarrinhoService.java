package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Cliente;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.ItemCarrinho;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Pedido;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.ClienteRepository;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.EstoqueRepository;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/*** Service dos métodos do carrinho:<br>
 * <b>Adiciona Produtos no Carrinho</b><br>
 * <b>Retira Produtos do Carrinho</b><br>
 * <b>Limpa Carrinho Aberto</b><br>
 * <b>Exibir Carrinho Aberto</b><br>
 * <b>Fechar Carrinho / Gerar Pedido</b>
 *
 * @author Fernando Netto
 */
@Service
public class CarrinhoService {

    /*** Instancia de repositório: <b>ClienteRepository</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    private ClienteRepository clienteRepository;
    /*** Instancia de repositório: <b>EstoqueRepository</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    private EstoqueRepository estoqueRepository;
    /*** Instancia de repositório: <b>PedidoRepository</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    private PedidoRepository pedidoRepository;

    /*** Método para adicionar novo produto, ou mais itens de um mesmo produto, ao carrinho de compras do cliente.<br>
     * Criando persistência do carrinho no repositório.
     *
     * @param clienteId ID do Cliente que está fazendo o pedido
     * @param produtoId ID do Produto que o cliente deseja acrescentar no carrinho de compras
     * @param quantidade Quantos itens do produto selecionado, o Cliente deseja adicionar no carrinho
     * @return Retorna um carrinho de compras (internamente é um <b>Pedido</b> com <i>ID nula</i>,
     * pois se trata um pedido ainda não finalizado).
     */
    public Pedido adicionarProdutosNoCarrinho(String clienteId, String produtoId, String quantidade) {
        long idCliente = Long.parseLong(clienteId);
        long idProduto = Long.parseLong(produtoId);
        long qtdProdutos = Long.parseLong(quantidade);
        Cliente cliente = clienteRepository.getCliente(idCliente);
        ItemCarrinho itemCarrinho;
        if ( Objects.equals( cliente.getCarrinho(), null)
                || cliente.getCarrinho().getItemCarrinho(idProduto) == null) {
            itemCarrinho = new ItemCarrinho( qtdProdutos, estoqueRepository.getProduto(idProduto) );
        } else {
            itemCarrinho = cliente.getCarrinho().getItemCarrinho(idProduto);
            itemCarrinho.setQuantidade( itemCarrinho.getQuantidade() + qtdProdutos );
        }
        cliente.adicionarProdutoNoCarrinho(itemCarrinho);
        clienteRepository.atualizaPedidoCliente(cliente);
        return cliente.getCarrinho();
    }

    /*** Método para retirar uma quantidade de um produto do carrinho de compras do cliente.<br>
     * Criando persistência do carrinho no repositório.
     *
     * @param clienteId ID do Cliente que está fazendo o pedido
     * @param produtoId ID do Produto que o cliente deseja acrescentar no carrinho de compras
     * @param quantidadeRetirar Quantos itens do produto selecionado, o Cliente deseja adicionar no carrinho
     * @return Retorna um <b>Pedido</b>, com <i>ID nula</i>, pois é um pedido ainda não finalizado (carrinho aberto).
     */
    public Pedido retirarProdutosDoCarrinho(String clienteId, String produtoId, String quantidadeRetirar) {
        long idCliente = Long.parseLong(clienteId);
        long idProduto = Long.parseLong(produtoId);
        long qtdProdutos = Long.parseLong(quantidadeRetirar);
        Cliente cliente = clienteRepository.getCliente(idCliente);
        if (!Objects.equals(cliente.getCarrinho(), null)
                && cliente.getCarrinho().getItemCarrinho(idProduto) != null) {
            ItemCarrinho item = cliente.getCarrinho().getItemCarrinho(idProduto);
            item.retiraQuantidadeProduto(qtdProdutos);
            cliente.getCarrinho().atualizaCarrinho(item);
            clienteRepository.atualizaPedidoCliente(cliente);
        }
        return cliente.getCarrinho();
    }

    /*** Método para limpar o carrinho de compras de um cliente
     *
     * @param clienteId ID do Cliente que deseja zerar o carrinho de compras
     */
    public void limparCarrinho(String clienteId) {
        long idCliente = Long.parseLong(clienteId);
        clienteRepository.getCliente(idCliente).limparCarrinho();
    }

    /*** Método para exibir o carrinho de compras atual de um cliente
     *
     * @param clienteId ID do Cliente que deseja ver o estado atual do carrinho de compras
     * @return Retorna um <b>Pedido</b>, com <i>ID nula</i>, pois é um pedido ainda não finalizado (carrinho aberto).
     */
    public Pedido exibirCarrinhoAberto(String clienteId) {
        long idCliente = Long.parseLong(clienteId);
        Cliente cliente = clienteRepository.getCliente(idCliente);
        return cliente.getCarrinho();
    }

    /*** Método para fechar o carrinho de compras de um cliente e criar um pedido
     *
     * @param clienteId ID do Cliente que deseja ver o estado atual do carrinho de compras
     * @return Retorna um <b>Pedido</b>, com <i>ID nula</i>, pois é um pedido ainda não finalizado (carrinho aberto).
     */
    public Pedido fecharCarrinho(String clienteId) {
        long idCliente = Long.parseLong(clienteId);
        Cliente cliente = clienteRepository.getCliente(idCliente);
        List<ItemCarrinho> listItemCarrinho = cliente.getCarrinho().getListaItensCarrinho();
        estoqueRepository.verificarEstoque(listItemCarrinho);
        estoqueRepository.baixarEstoque(listItemCarrinho);
        Pedido pedido = pedidoRepository.criaPedido(listItemCarrinho);
        cliente.limparCarrinho();
        return pedido;
    }
}
