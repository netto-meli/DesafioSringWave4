package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
        listaPedidos.stream()
                .filter(pd -> Objects.equals(pd.getId(), null))
                .findAny()
                .orElse( new Pedido(this.id, null, new ArrayList<>(), BigDecimal.ZERO))
                .atualizaCarrinho(carrinho);
    }

    public void limparCarrinho(){
        listaPedidos.stream()
                .filter(pd -> Objects.equals(pd.getId(), null))
                .findAny()
                .orElse( new Pedido(this.id, null, new ArrayList<>(), BigDecimal.ZERO))
                .setListaItensCarrinho( new ArrayList<>() );
    }

    public Pedido getCarrinho() {
        return this.getPedido(null);
    }

    public Pedido getPedido(Long idPedido) {
        return listaPedidos.stream()
                .filter( p -> Objects.equals(p.getId(), idPedido))
                .findAny()
                .orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setId(Long clienteId) {
    }
}
