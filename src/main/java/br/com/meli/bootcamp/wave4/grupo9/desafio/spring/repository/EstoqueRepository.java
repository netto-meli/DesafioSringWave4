package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.ItemCarrinho;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;


@Component
public class EstoqueRepository implements OurRepository<Produto, Long>{

    private List<Produto> produtos = new ArrayList<Produto>();
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "estoque.json";

    public void salva(Produto produto) throws IOException {
        // TODO verificar questao do ID
        // produto.setId((long) produtos.size()+1);
        produtos.add(produto);
        objectMapper.writeValue(new File(PATH), produtos);
    }

    public List<Produto> listagem() throws IOException{
        File file = new File(PATH);
        FileInputStream is = new FileInputStream(file);
        produtos = Arrays.asList(objectMapper.readValue(is, Produto[].class));
        return produtos;
    }

    public Produto get(Long id) {
        Optional<Produto> optional = produtos.stream().filter(u -> Long.valueOf(u.getId()).equals(id) ).findAny();
        // TODO new produto
        // return optional.orElse(new Produto());
        return null;
    }

    //TODO fazer
    public boolean verificarEstoque(List<ItemCarrinho> listItemCarrinho) {
        return true;
    }

    public void baixarEstoque(List<ItemCarrinho> listItemCarrinho) {
        Optional<Produto> optional = produtos.stream().filter(u -> u.getId().equals(id)).findAny();
        return optional.orElse(new Produto());
    }
}
