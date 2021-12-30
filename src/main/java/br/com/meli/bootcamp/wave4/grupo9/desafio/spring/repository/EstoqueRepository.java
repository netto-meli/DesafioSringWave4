package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.ItemCarrinho;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstoqueRepository {
    public Produto getProduto(long idProduto) {
        return null;
    }

    public void verificarEstoque(List<ItemCarrinho> listItemCarrinho) {
    }

    public void baixarEstoque(List<ItemCarrinho> listItemCarrinho) {
    }
}
