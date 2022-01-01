package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Cliente;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.ItemCarrinho;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Pedido;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/***
 * @author Fernando Netto
 */
@Repository
public class PedidoRepository implements OurRepository<Pedido, Long>{
    List<Pedido> pedidos = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "pedidos.json";

    public Cliente salva(Pedido pedido) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Pedido> listagem(){
        try {
            File file = new File(PATH);
            FileInputStream is = new FileInputStream(file);
            pedidos = new ArrayList<>(Arrays.asList(objectMapper.readValue(is, Pedido[].class)));
            return pedidos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Pedido get(Long id) {
        return pedidos.stream()
                .filter(u -> u.getId().equals(id) )
                .findAny()
                .orElse(null); // null se nao existe produto
    }

    public Pedido criaPedido(List<ItemCarrinho> listItemCarrinho, long idCliente, String enderecoEntrega) {
        Long idPedido = getMaxId()+1L;
        BigDecimal valorPedido = BigDecimal.ZERO;
        BigDecimal valorFrete = BigDecimal.ZERO;
        Pedido p = new Pedido( idPedido, idCliente, listItemCarrinho, valorFrete, valorPedido);
        p.calculaValorTotalPedido(enderecoEntrega);
        salva(p);
        return p;
    }

    private Long getMaxId(){
        Long id = 0L;
        for ( Pedido p : pedidos ) {
            if (p.getId() != null && p.getId().compareTo(id) > 0 ){
                id = p.getId();
            }
        }
        return id;
    }

    public void adicionarProdutoNoCarrinho(Long idClt, String endereco, ItemCarrinho carrinho){
        Pedido ped = pedidos.stream()
                .filter(pdcl -> pdcl.getIdCliente().equals(idClt))
                .filter(pd -> Objects.equals(pd.getId(), null))
                .findAny()
                .orElse( criaCarrinhoNovo(idClt) );
        ped.atualizaCarrinho(carrinho, endereco);
        // TODO deve ter um jeito melhor que isso
        pedidos.remove(ped);
        pedidos.add(ped);
    }

    public Pedido getCarrinho(Long idClt) {
        return pedidos.stream()
                .filter(pdcl -> pdcl.getIdCliente().equals(idClt))
                .filter(pd -> Objects.equals(pd.getId(), null))
                .findAny()
                .orElse( criaCarrinhoNovo(idClt) );
    }

    public void limparCarrinho(Long idClt){
        pedidos.stream()
                .filter(pdcl -> pdcl.getIdCliente().equals(idClt))
                .filter(pd -> Objects.equals(pd.getId(), null))
                .findAny()
                .orElse( criaCarrinhoNovo(idClt) )
                .setListaItensCarrinho( new ArrayList<>() );
    }

    private Pedido criaCarrinhoNovo(Long idClt){
        return new Pedido(null, idClt, new ArrayList<>(), BigDecimal.ZERO, BigDecimal.ZERO);
    }
}
