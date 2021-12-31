package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.ItemCarrinho;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Pedido;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.RepositoryException;
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

import org.springframework.stereotype.Component;

@Component
public class EstoqueRepository implements OurRepository<Produto, Long>{

    private List<Produto> produtos = new ArrayList<>();

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
            //produto.setId((long) produtos.size()+1);
            if ( produto.getId() == null ) produto.setId(getMaxId()+1L);

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

    private Long getMaxId(){
        Long id = 0L;
        for ( Produto p : produtos ) {
            if (p.getId() != null && p.getId().compareTo(id) > 0 ){
                id = p.getId();
            }
        }
        return id;
    }

    public Produto get(Long id) {
        return produtos.stream()
                .filter(u -> Long.valueOf(u.getId()).equals(id) )
                .findAny()
                .orElse(null); // null se nao existe produto
    }

    public void baixarEstoque(List<ItemCarrinho> listItemCarrinho) {
        for (ItemCarrinho pdCarrinho : listItemCarrinho) {
            try{
                produtos.stream()
                    .filter( pdEstq -> pdEstq.getId() == pdCarrinho.getProduto().getId() )
                    .findFirst()
                    .orElse(null)
                    .baixarEstoque( pdCarrinho.getQuantidade() );
            } catch (NullPointerException e) {
                throw new RepositoryException("Não existe produto no estoque para dar baixa.");
            }
        }
    }
}
