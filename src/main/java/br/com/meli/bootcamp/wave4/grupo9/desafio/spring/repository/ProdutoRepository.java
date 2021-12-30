package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Repository
public class ProdutoRepository {

    private List<Produto> produtos = new ArrayList<Produto>();
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "produto.json";

    public void salvaProduto(Produto produto) throws IOException {
        produto.setId((long) produtos.size()+1);
        produtos.add(produto);
        objectMapper.writeValue(new File(PATH), produto);
    }

    public List<Produto> listagem() throws IOException{
        File file = new File(PATH);
        FileInputStream is = new FileInputStream(file);
        produtos = Arrays.asList(objectMapper.readValue(is, Produto[].class));
        return produtos;
    }

    public Produto get(Long id) {
        Optional<Produto> optional = produtos.stream().filter(u -> u.getId() == id).findAny();
        return optional.orElse(new Produto());
    }

}
