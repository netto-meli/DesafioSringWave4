package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.EstoqueRepository;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.RepositoryException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/***
 * @author Leo
 */
@Service
public class ProdutoService {

    private static final String ORDENCAO_AFABETICA_DEC = "0";
    private static final String ORDENCAO_AFABETICA_CRES = "1";
    private static final String ORDENCAO_MAIOR_PRECO = "2";
    private static final String ORDENCAO_MENOR_PRECO = "3";

    private EstoqueRepository estoqueRepository;
    private Logger logger = null;

    public ProdutoService(EstoqueRepository repo) {
        this.estoqueRepository = repo;
        this.logger = Logger.getLogger(String.valueOf(EstoqueRepository.class));

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
            produtos = estoqueRepository.listagem();
        } catch (IOException e) {
            throw new RepositoryException("erro ao localizar produtos");
        }
        return produtos;
    }

    public List<Produto> listaProdutoCategoria(String categoria) {
        try {
            return estoqueRepository.listagem().stream()
                    .filter(p -> p.getCategoria().getNome().equals(categoria)).
                            collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    private EstoqueRepository estoqueRepository;
    private Logger logger = null;

    public ProdutoService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
        this.logger = LoggerFactory.getLogger(ProdutoService.class);
    }

    public void salvar(Produto produto) {
        if(true) {
            try {
                estoqueRepository.salva(produto);
                logger.debug("produto salvo");
            }catch(IOException e) {
                logger.error(e.getMessage());
            }
        }else {
            throw new RuntimeException("======");
        }
    }

    public List<Produto> lista(){
        //List<Produto> produtos = null;
        try {
            return estoqueRepository.listagem().stream().collect(Collectors.toList());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<Produto> listaProdutoOrdenado(String ordenacao) throws IOException {
        if (ORDENCAO_AFABETICA_CRES.equals(ordenacao)) {
            estoqueRepository.listagem().stream().sorted(Comparator.comparing(Produto::getNome)).collect(Collectors.toList());
        } else if (ORDENCAO_AFABETICA_DEC.equals(ordenacao)) {
            estoqueRepository.listagem().stream().sorted(Comparator.comparing(Produto::getNome).reversed()).collect(Collectors.toList());
        } else if (ORDENCAO_MAIOR_PRECO.equals(ordenacao)) {
            estoqueRepository.listagem().stream().sorted(Comparator.comparing(Produto::getValor)).collect(Collectors.toList());
        } else if (ORDENCAO_MENOR_PRECO.equals(ordenacao)) {
            estoqueRepository.listagem().stream().sorted(Comparator.comparing(Produto::getValor).reversed()).collect(Collectors.toList());
        }
        return null;
    }

    public List<Produto> listaProdutoFiltorPersonalizado(String nome, String categoria) throws IOException {

        return estoqueRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome())).filter(u -> categoria.equals(u.getCategoria()))
                .collect(Collectors.toList());
    }

    public List<Produto> listaProdutoFiltorPersonalizado2(String nome, String categoria) throws IOException {

        return estoqueRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome())).filter(u -> categoria.equals(u.getCategoria()))
                .collect(Collectors.toList());
    }
    public List<Produto> listaProdutoFiltorPersonalizado3(String nome, String categoria) throws IOException {

        return estoqueRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome())).filter(u -> categoria.equals(u.getCategoria()))
                .collect(Collectors.toList());
    }
    public List<Produto> listaProdutoFiltorPersonalizado4(String nome, String categoria) throws IOException {

        return estoqueRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome())).filter(u -> categoria.equals(u.getCategoria()))
                .collect(Collectors.toList());
    }
    public List<Produto> listaProdutoFiltorPersonalizado5(String nome, String categoria) throws IOException {

        return estoqueRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome())).filter(u -> categoria.equals(u.getCategoria()))
                .collect(Collectors.toList());
    }
    public List<Produto> listaProdutoFiltorPersonalizado6(String nome, String categoria) throws IOException {

        return estoqueRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome())).filter(u -> categoria.equals(u.getCategoria()))
                .collect(Collectors.toList());
    }
    public List<Produto> listaProdutoFiltorPersonalizado7(String nome, String categoria) throws IOException {

        return estoqueRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome())).filter(u -> categoria.equals(u.getCategoria()))
                .collect(Collectors.toList());
    }




    public Produto obter(Long id) {
        return estoqueRepository.get(id);
    }
}
