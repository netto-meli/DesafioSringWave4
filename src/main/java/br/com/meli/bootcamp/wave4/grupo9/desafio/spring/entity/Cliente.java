package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class Cliente {
    private Long id;
    private String nome;
    private String endereco;
    private String estado;
    private String cpf;
    private List<Pedido> listaPedidos;

    public void adicionarProdutoNoCarrinho(ItemCarrinho carrinho){
        Pedido ped = listaPedidos.stream()
                .filter(pd -> Objects.equals(pd.getId(), null))
                .findAny()
                .orElse( criaCarrinhoNovo() );
        ped.atualizaCarrinho(carrinho, this.endereco);
        // TODO deve ter um jeito melhor que isso
        listaPedidos.remove(ped);
        listaPedidos.add(ped);
    }

    public Pedido getCarrinho() {
        Pedido carrinho = this.getPedido(null);
        if (carrinho == null) carrinho = criaCarrinhoNovo();
        return carrinho;
    }

    public Pedido getPedido(Long idPedido) {
        return listaPedidos.stream()
                .filter( p -> Objects.equals(p.getId(), idPedido))
                .findAny()
                .orElse(null);
    }

    public void limparCarrinho(){
        listaPedidos.stream()
                .filter(pd -> Objects.equals(pd.getId(), null))
                .findAny()
                .orElse( criaCarrinhoNovo() )
                .setListaItensCarrinho( new ArrayList<>() );
    }

    private Pedido criaCarrinhoNovo(){
        return new Pedido(null, this.id, new ArrayList<>(), BigDecimal.ZERO, BigDecimal.ZERO);
    }

    /***
     * {@literal @}Override do método equals
     *
     * @param o Object a ser comparado com a instância desta Classe,
     *          comparando também a ID do Cliente para informar que o Cliente é a mesmo.
     * @return Boolean indicando se o Objeto é o mesmo ou não.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id);
    }

    /***
     * {@literal @}Override do método hash
     *
     * @return Inteiro referente ao retorno do metodo Objects.{@link java.util.Objects hash}(id, nome);
     * @see java.util.Objects hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
