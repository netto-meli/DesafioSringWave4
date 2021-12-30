package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.ItemCarrinho;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class EstoqueRepository {
    private List<Produto> produtos = new ArrayList<Produto>();
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "produto.json";

    public Produto getProduto(long idProduto) {
        return null;
    }

    public void verificarEstoque(List<ItemCarrinho> listItemCarrinho) {
    }

    public void baixarEstoque(List<ItemCarrinho> listItemCarrinho) {
    }

    public List<Produto> listagem() throws IOException{
        File file = new File(PATH);
        FileInputStream is = new FileInputStream(file);
        produtos = Arrays.asList(objectMapper.readValue(is, Produto[].class));
        return produtos;
    }

}
