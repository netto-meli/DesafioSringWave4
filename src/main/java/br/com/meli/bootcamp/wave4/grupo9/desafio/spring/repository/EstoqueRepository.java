package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.ItemCarrinho;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/***
 * @author Felipe
 * @author Fernando
 * @author Leonardo
 */
@Repository
public class EstoqueRepository implements OurRepository<Produto, Long>{

    private List<Produto> produtos = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "estoque.json";

    public void salva(Produto produto) throws IOException {
        produtos.add(produto);
        objectMapper.writeValue(new File(PATH), produtos);
    }

    public List<Produto> listagem() throws IOException{
        File file = new File(PATH);
        FileInputStream is = new FileInputStream(file);
        Produto[] u = objectMapper.readValue(is, Produto[].class);
        produtos = Arrays.asList(u);
        return produtos;
    }

    public Produto get(Long id) {
        return produtos.stream()
                .filter(u -> Long.valueOf(u.getId()).equals(id) )
                .findAny()
                .orElse(null); // null se nao existe produto
    }

    public void baixarEstoque(List<ItemCarrinho> listItemCarrinho) {
        for (ItemCarrinho pdCarrinho : listItemCarrinho) {
            // se nao achar idproduto no estoque, NULLPOINTER
            produtos.stream()
                    .filter( pdEstq -> pdEstq.getId() == pdCarrinho.getProduto().getId() )
                    .findFirst()
                    .orElse(null)
                    .baixarEstoque( pdCarrinho.getQuantidade() );
                    // exception se nao tiver estoque
        }
    }
}
