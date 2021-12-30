package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.EstoqueRepository;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.RepositoryException;
import exception.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
        }
        return null;
    }

    public List<Produto> listaProdutoOrdenado(String ordenacao,String nome) throws IOException {
        ordenaNome(nome,ordenacao);
        if (ORDENCAO_AFABETICA_CRES.equals(ordenacao)) {
            estoqueRepository.listagem().stream().sorted(Comparator.comparing(Produto::getNome)).collect(Collectors.toList());
        } else if (ORDENCAO_AFABETICA_DEC.equals(ordenacao)) {
            estoqueRepository.listagem().stream().sorted(Comparator.comparing(Produto::getNome).reversed()).collect(Collectors.toList());
        } else if (ORDENCAO_MAIOR_PRECO.equals(ordenacao)) {
            return estoqueRepository.listagem().stream().sorted(Comparator.comparing(Produto::getPreco)).collect(Collectors.toList());
        } else if (ORDENCAO_MENOR_PRECO.equals(ordenacao)) {
            return  estoqueRepository.listagem().stream().sorted(Comparator.comparing(Produto::getPreco).reversed()).collect(Collectors.toList());
        }
        return null;
    }

    private List<Produto> ordenaNome(String nome, String ordenacao) throws IOException{
        if (ORDENCAO_AFABETICA_CRES.equals(ordenacao)) {
            return  estoqueRepository.listagem().stream().sorted(Comparator.comparing(Produto::getNome)).collect(Collectors.toList());
        } else if (ORDENCAO_AFABETICA_DEC.equals(ordenacao)) {
            return estoqueRepository.listagem().stream().sorted(Comparator.comparing(Produto::getNome).reversed()).collect(Collectors.toList());
        }
        return null;
    }

    public List<Produto> listaProdutoFiltorPersonalizado(String nome, String categoria) throws IOException {
        return estoqueRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome())).filter(u -> categoria.equals(u.getCategoria().getNome()))
                .collect(Collectors.toList());
    }

    public List<Produto> listaProdutoFiltorPersonalizado2(String nome, boolean frete) throws IOException {

        return estoqueRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome())).filter(u -> frete == (u.isFreteGratis()))
                .collect(Collectors.toList());
    }

    public List<Produto> listaProdutoFiltorPersonalizado3(String nome, String marca) throws IOException {

        return estoqueRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome())).filter(u -> marca.equals(u.getMarca()))
                .collect(Collectors.toList());
    }

    public List<Produto> listaProdutoFiltorPersonalizado4(boolean frete, String categoria) throws IOException {

        return estoqueRepository.listagem().stream()
                .filter(u -> frete == (u.isFreteGratis())).filter(u -> categoria.equals(u.getCategoria().getNome()))
                .collect(Collectors.toList());
    }

    public List<Produto> listaProdutoFiltorPersonalizado5(String marca, int estrela) throws IOException {

        return estoqueRepository.listagem().stream()
                .filter(u -> marca.equals(u.getNome())).filter(u -> estrela == (u.getEstrelas()))
                .collect(Collectors.toList());
    }




}
