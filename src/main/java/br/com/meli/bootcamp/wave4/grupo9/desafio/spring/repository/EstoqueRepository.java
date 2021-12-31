package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.ItemCarrinho;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class EstoqueRepository implements OurRepository<Produto, Long>{

    private List<Produto> produtos = new ArrayList<Produto>();

    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "estoque.json";

    public EstoqueRepository() throws IOException {
    }

    public void salva(Produto produto) throws IOException {
        // TODO verificar questao do ID
        try {
            /*Mesclar duas ArrayList<>
            * List<String> newList = new ArrayList<String>(listOne);
                newList.addAll(listTwo);
            * */
            produtos = listagem();
            produto.setId((long) produtos.size()+1);

            List<Produto> novaLista2 =new ArrayList<Produto>();
            novaLista2.add(produto);
            List<Produto> novaLista = new ArrayList<Produto>(produtos);
            novaLista.addAll(novaLista2);

            objectMapper.writeValue(new File(PATH), novaLista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Produto> listagem() throws IOException{
        try {
            File file = new File(PATH);
            FileInputStream is = new FileInputStream(file);
            produtos = Arrays.asList(objectMapper.readValue(is, Produto[].class));

            return produtos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Produto> salvaLista(List<Produto> listaprod) throws IOException {
        for (Produto p : listaprod) {
            salva(p);
        }
        return produtos;
    }

    public Produto get(Long id) {
        Optional<Produto> optional = produtos.stream().filter(u -> Long.valueOf(u.getId()).equals(id) ).findAny();
        // TODO funcao retorna produto pelo id
        return optional.orElse(new Produto());
        //return null;
    }
    public boolean verificarEstoque(List<ItemCarrinho> listItemCarrinho) {
        //TODO fazer funcao verificar estoque
        return true;
    }
    public void baixarEstoque(List<ItemCarrinho> listItemCarrinho) {
        //TODO fazer funcao baixar estoque.
    }
}
