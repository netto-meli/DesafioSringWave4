package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class Pedido {
    private final long id;
    private final long idCliente;
    private List<ItemCarrinho> listaItensCarrinho;
    private BigDecimal valorTotal;

    public void calculaValorTotal(){
        //
    }


}
