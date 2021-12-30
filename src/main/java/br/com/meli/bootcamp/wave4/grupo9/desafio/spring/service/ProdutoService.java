package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.ProdutoRepository;
import exception.RepositoryException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private static final String ORDENCAO_AFABETICA_DEC = "0";
    private static final String ORDENCAO_AFABETICA_CRES = "1";
    private static final String ORDENCAO_MAIOR_PRECO = "2";
    private static final String ORDENCAO_MENOR_PRECO = "3";

    private ProdutoRepository produtoRepository;
    private Logger logger = null;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
        this.logger = Logger.getLogger(String.valueOf(ProdutoRepository.class));

    }

    /*   public void salvar(Produto produto) {
           if(produto != null) {
               try {
                   produtoRepository.salvaProduto(produto);
                   logger.info("Produto cadastrado");
               }catch(IOException e) {
                   logger.warning(e.getMessage());
                   throw new RepositoryException("Erro ao cadastrar o produto tente novamente");
               }
           }else {
               throw new RuntimeException("O produto deve conter alguma informacao para  ser cadastrado");
           }
       }
   */
    public List<Produto> listaProduto() {
        List<Produto> produtos = null;
        try {
            produtos = produtoRepository.listagem();
        } catch (IOException e) {
            throw new RepositoryException("erro ao localizar produtos");
        }
        return produtos;
    }

    public List<Produto> listaProdutoCategoria(String categoria) {
        try {
            return produtoRepository.listagem().stream()
                    .filter(p -> p.getCategoria().getNome().equals(categoria)).
                            collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Produto> listaProdutoOrdenado(String ordenacao) throws IOException {
        if (ORDENCAO_AFABETICA_CRES.equals(ordenacao)) {
            produtoRepository.listagem().stream().sorted(Comparator.comparing(Produto::getNome)).collect(Collectors.toList());
        } else if (ORDENCAO_AFABETICA_DEC.equals(ordenacao)) {
            produtoRepository.listagem().stream().sorted(Comparator.comparing(Produto::getNome).reversed()).collect(Collectors.toList());
        } else if (ORDENCAO_MAIOR_PRECO.equals(ordenacao)) {
            produtoRepository.listagem().stream().sorted(Comparator.comparing(Produto::getPreco)).collect(Collectors.toList());
        } else if (ORDENCAO_MENOR_PRECO.equals(ordenacao)) {
            produtoRepository.listagem().stream().sorted(Comparator.comparing(Produto::getPreco).reversed()).collect(Collectors.toList());
        }
        return null;
    }

    public List<Produto> listaProdutoFiltorPersonalizado(String nome, String categoria) throws IOException {

        return produtoRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome())).filter(u -> categoria.equals(u.getCategoria()))
                .collect(Collectors.toList());
    }

    public List<Produto> listaProdutoFiltorPersonalizado2(String nome, String categoria) throws IOException {

        return produtoRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome())).filter(u -> categoria.equals(u.getCategoria()))
                .collect(Collectors.toList());
    }
    public List<Produto> listaProdutoFiltorPersonalizado3(String nome, String categoria) throws IOException {

        return produtoRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome())).filter(u -> categoria.equals(u.getCategoria()))
                .collect(Collectors.toList());
    }
    public List<Produto> listaProdutoFiltorPersonalizado4(String nome, String categoria) throws IOException {

        return produtoRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome())).filter(u -> categoria.equals(u.getCategoria()))
                .collect(Collectors.toList());
    }
    public List<Produto> listaProdutoFiltorPersonalizado5(String nome, String categoria) throws IOException {

        return produtoRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome())).filter(u -> categoria.equals(u.getCategoria()))
                .collect(Collectors.toList());
    }
    public List<Produto> listaProdutoFiltorPersonalizado6(String nome, String categoria) throws IOException {

        return produtoRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome())).filter(u -> categoria.equals(u.getCategoria()))
                .collect(Collectors.toList());
    }
    public List<Produto> listaProdutoFiltorPersonalizado7(String nome, String categoria) throws IOException {

        return produtoRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome())).filter(u -> categoria.equals(u.getCategoria()))
                .collect(Collectors.toList());
    }


}
