package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity;

import com.sun.source.tree.ReturnTree;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Pedido {
    private long id;
    private Cliente cliente;
    private List<ItemCarrinho> listaItensCarrinho;

    /*
    public BigDecimal getValorTotal(){
        BigDecimal valorTotal = new BigDecimal(0);
        for(ItemCarrinho ic : listaItensCarrinho) {
            valorTotal = valorTotal.add(ic.calculaValorTotal());
        }
        return valorTotal;
    }

     */
}
