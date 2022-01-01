package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.ItemCarrinho;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.RepositoryException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * @author Felipe
 * @author Fernando
 * @author Leonardo
 */
@Repository
@NoArgsConstructor
public class EstoqueRepository implements OurRepository<Produto, Long>{

    private List<Produto> produtos = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "estoque.json";

    public Produto salva(Produto produto) {
        try {
            /*Mesclar duas ArrayList<>
            * List<String> newList = new ArrayList<String>(listOne);
                newList.addAll(listTwo);
            * */
            produtos = listagem();
            if ( produto.getId() == null ) produto.setId(getMaxId()+1L);
            List<Produto> novaLista2 =new ArrayList<>();
            novaLista2.add(produto);
            List<Produto> novaLista = new ArrayList<>(produtos);
            novaLista.addAll(novaLista2);
            objectMapper.writeValue(new File(PATH), novaLista);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produto;
    }

    public void grava() {
        try {
            objectMapper.writeValue(new File(PATH), produtos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvaLista(List<Produto> listaprod) {
        for (Produto p : listaprod) {
            salva(p);
        }
    }

    public List<Produto> listagem() throws ErrorProcesamentoException {
        try {
            File file = new File(PATH);
            FileInputStream is = new FileInputStream(file);
            produtos = new ArrayList<>(Arrays.asList(objectMapper.readValue(is, Produto[].class)));
            return produtos;
        } catch (Exception e) {
            throw new ErrorProcesamentoException("Erro ao localizar produto");
        }
    }

    public Produto get(Long id) {
        return produtos.stream()
                .filter(u -> u.getId().equals(id) )
                .findAny()
                .orElse(null); // null se nao existe produto
    }

    public void baixarEstoque(List<ItemCarrinho> listItemCarrinho) {
        for (ItemCarrinho pdCarrinho : listItemCarrinho) {
            try{
                produtos.stream()
                    .filter( pdEstq -> pdEstq.getId().equals(pdCarrinho.getProduto().getId()))
                    .findFirst()
                    .orElse(null)
                    .baixarEstoque( pdCarrinho.getQuantidade() );
                grava();
            } catch (NullPointerException e) {
                throw new RepositoryException("Não existe produto no estoque para dar baixa.");
            }
        }
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
}
