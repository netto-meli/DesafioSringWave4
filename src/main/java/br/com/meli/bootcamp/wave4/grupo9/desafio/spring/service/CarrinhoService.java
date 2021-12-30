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

@Service
public class CarrinhoService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido adicionarProdutosNoCarrinho(String clienteId, String produtoId, String quantidade) {
        long idCliente = Long.parseLong(clienteId);
        long idProduto = Long.parseLong(produtoId);
        long qtdProdutos = Long.parseLong(quantidade);
        Cliente cliente = clienteRepository.getCliente(idCliente);
        ItemCarrinho itemCarrinho;
        if ( Objects.equals( cliente.getCarrinho(), null)
                || cliente.getCarrinho().getItemCarrinho(idProduto) == null) {
            itemCarrinho = new ItemCarrinho( qtdProdutos, estoqueRepository.get(idProduto) );
        } else {
            itemCarrinho = cliente.getCarrinho().getItemCarrinho(idProduto);
            itemCarrinho.setQuantidade( itemCarrinho.getQuantidade() + qtdProdutos );
        }
        cliente.adicionarProdutoNoCarrinho(itemCarrinho);
        clienteRepository.atualizaPedidoCliente(cliente);
        return cliente.getCarrinho();
    }

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

    public void limparCarrinho(String clienteId) {
        long idCliente = Long.parseLong(clienteId);
        clienteRepository.getCliente(idCliente).limparCarrinho();
    }

    public Pedido exibirCarrinhoAberto(String clienteId) {
        long idCliente = Long.parseLong(clienteId);
        Cliente cliente = clienteRepository.getCliente(idCliente);
        return cliente.getCarrinho();
    }

    public Pedido fecharCarrinho(String clienteId) {
        long idCliente = Long.parseLong(clienteId);
        Cliente cliente = clienteRepository.getCliente(idCliente);
        List<ItemCarrinho> listItemCarrinho = cliente.getCarrinho().getListaItensCarrinho();
        if ( ! estoqueRepository.verificarEstoque(listItemCarrinho) ) {
            // TODO erro pois esta sem estoque
            System.out.println("sem estoque");
        }
        estoqueRepository.baixarEstoque(listItemCarrinho);
        Pedido pedido = pedidoRepository.criaPedido(listItemCarrinho);
        cliente.limparCarrinho();
        return pedido;
    }
}
