package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.EstoqueRepository;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.List;

/***
 * Service implementacao dos métodos do carrinho:<br>
 *  * <b>Lista todos os produtos</b><br>
 *  * <b>Lista por categoria</b><br>
 *  * <b>lista por nome</b><br>
 *  * <b>Lista ordenando</b><br>
 *  * <b>lista ppersonalizada / com dois parametros</b>
 *  *
 *  * @author Leonardo Assuncao
 */

@Service
public class ProdutoService {

    /***
     * Variaveis final static para ser utilizadar no metodo de ordenacao
     */
    private static final String ORDENCAO_AFABETICA_DEC = "0";
    private static final String ORDENCAO_AFABETICA_CRES = "1";
    private static final String ORDENCAO_MAIOR_PRECO = "2";
    private static final String ORDENCAO_MENOR_PRECO = "3";

    /*** Instancia de estoque: <b>EstoqueRepository</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    private EstoqueRepository estoqueRepository;

    public List<Produto> listaProduto() {
        return estoqueRepository.listagem();
    }

    public List<Produto> listaProdutoCategoria(String categoria) {
        return estoqueRepository.listagem().stream()
                .filter(p -> p.getCategoria().getNome().equals(categoria))
                .collect(Collectors.toList());
    }

    public List<Produto> listaProdutoOrdenado(String ordenacao, String nome, String marca, String categoria) {
        if (!nome.isEmpty() || !ordenacao.isEmpty()) {
            ordenaNome(nome, ordenacao);
        }
        if (!ordenacao.isEmpty()) {
            ordenaPreco(ordenacao);
        }
        if (!categoria.isEmpty() || !ordenacao.isEmpty()) {
            ordenaMarca(ordenacao, marca);
        }
        if (!marca.isEmpty() || !ordenacao.isEmpty()) {
            ordenaCategoria(categoria, ordenacao);
        }
        return null;
    }

    private List<Produto> ordenaCategoria(String categoria, String ordenacao){
        if (ORDENCAO_AFABETICA_CRES.equals(ordenacao)) {
            return estoqueRepository.listagem().stream()
                    .filter(u -> categoria.equals(u.getCategoria().getNome()))
                    .sorted(Comparator.comparing(a -> a.getCategoria().getNome()))
                    .collect(Collectors.toList());
        } else if (ORDENCAO_AFABETICA_DEC.equals(ordenacao)) {
            return estoqueRepository.listagem().stream()
                    .filter(u -> categoria.equals(u.getCategoria().getNome()))
                    .sorted((a, b) -> b.getCategoria().getNome().compareTo(a.getCategoria().getNome()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    private List<Produto> ordenaMarca(String ordenacao, String marca) {
        if (ORDENCAO_AFABETICA_CRES.equals(ordenacao)) {
            return estoqueRepository.listagem().stream()
                    .filter(u -> marca.equals(u.getMarca()))
                    .sorted(Comparator.comparing(Produto::getMarca))
                    .collect(Collectors.toList());
        } else if (ORDENCAO_AFABETICA_DEC.equals(ordenacao)) {
            return estoqueRepository.listagem().stream()
                    .sorted(Comparator.comparing(Produto::getMarca).reversed())
                    .collect(Collectors.toList());
        }
        return null;
    }

    private List<Produto> ordenaPreco(String ordenacao) {
        if (ORDENCAO_MAIOR_PRECO.equals(ordenacao)) {
            return estoqueRepository.listagem().stream()
                    .sorted(Comparator.comparing(Produto::getValor))
                    .collect(Collectors.toList());
        } else if (ORDENCAO_MENOR_PRECO.equals(ordenacao)) {
            return estoqueRepository.listagem().stream()
                    .sorted(Comparator.comparing(Produto::getValor).reversed())
                    .collect(Collectors.toList());
        }
        return null;
    }

    private List<Produto> ordenaNome(String nome, String ordenacao)  {
        if (ORDENCAO_AFABETICA_CRES.equals(ordenacao)) {
            return estoqueRepository.listagem().stream()
                    .filter(u -> nome.equals(u.getMarca()))
                    .sorted(Comparator.comparing(Produto::getNome))
                    .collect(Collectors.toList());
        } else if (ORDENCAO_AFABETICA_DEC.equals(ordenacao)) {
            return estoqueRepository.listagem().stream()
                    .sorted(Comparator.comparing(Produto::getNome).reversed())
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<Produto> listaProdutoFiltorPersonalizado(String nome, String categoria) {
        return estoqueRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome()))
                .filter(u -> categoria.equals(u.getCategoria().getNome()))
                .collect(Collectors.toList());
    }

    public List<Produto> listaProdutoFiltorPersonalizado2(String nome, boolean frete)  {
        return estoqueRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome()))
                .filter(u -> frete == (u.isFreteGratis()))
                .collect(Collectors.toList());
    }

    public List<Produto> listaProdutoFiltorPersonalizado3(String nome, String marca) {
        return estoqueRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome()))
                .filter(u -> marca.equals(u.getMarca()))
                .collect(Collectors.toList());
    }

    public List<Produto> listaProdutoFiltorPersonalizado4(boolean frete, String categoria) {
        return estoqueRepository.listagem().stream()
                .filter(u -> frete == (u.isFreteGratis()))
                .filter(u -> categoria.equals(u.getCategoria().getNome()))
                .collect(Collectors.toList());
    }

    public List<Produto> listaProdutoFiltorPersonalizado5(String marca, int estrela) {
        return estoqueRepository.listagem().stream()
                .filter(u -> marca.equals(u.getNome()))
                .filter(u -> estrela == (u.getEstrelas()))
                .collect(Collectors.toList());
    }

//TODO ver se ta ok
    public List<Produto> salvaLista(List<Produto> listaProduto) {
        estoqueRepository.salvaLista(listaProduto);
        return listaProduto;
    }

    public Produto obter(long id) {
        return estoqueRepository.get(id);
    }

    public void salvar(Produto produto) {
        estoqueRepository.salva(produto);
    }
}
