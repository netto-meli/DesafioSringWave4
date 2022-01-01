package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.NotFoundExceptionProduct;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.EstoqueRepository;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
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
    private static final int ORDENCAO_AFABETICA_DEC = 0;
    private static final int ORDENCAO_AFABETICA_CRES = 1;
    private static final int ORDENCAO_MAIOR_PRECO = 2;
    private static final int ORDENCAO_MENOR_PRECO = 3;
    private static final String PRODUTO_AINDA_NAO_DISPONIVEL = "OPS! Nao temos este produto ainda disponivel, logo atualizaremos o catalogo da loja, desculpe pelo incoveniente";
    private static final String PRODUTO_ERRO_ORDENACAO = "Tivemos um erro na ordenacao tente novamente mais tarde =]";
    private static final String ERROR_LOCALIZAR_PELO_FILTRO = "Tivemos um erro ao tentar filtar pelos parametros enviados tenta novamento";
    /*** Instancia de estoque: <b>EstoqueRepository</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    private EstoqueRepository estoqueRepository;

    public List<Produto> listaProduto() {
        List<Produto> produtos;
        try {
            produtos = estoqueRepository.listagem();
        } catch (IOException e) {
            throw new RepositoryException("erro ao localizar produtos");
        }
        return produtos;
    }

    public List<Produto> listaProdutoCategoria(String categoria) throws NotFoundExceptionProduct {
        try {
            return estoqueRepository.listagem().stream()
                    .filter(p -> p.getCategoria().getNome().equals(categoria)).
                            collect(Collectors.toList());
        } catch (IOException e) {
            throw new NotFoundExceptionProduct(PRODUTO_AINDA_NAO_DISPONIVEL);
        }
    }

    public List<Produto> listaProdutoOrdenado(int ordenacao) throws NotFoundExceptionProduct{
        try {
            if (ORDENCAO_AFABETICA_CRES == ordenacao) {
                return estoqueRepository.listagem().stream().sorted(Comparator.comparing(Produto::getNome)).collect(Collectors.toList());
            } else if (ORDENCAO_AFABETICA_DEC == ordenacao) {
                return estoqueRepository.listagem().stream().sorted(Comparator.comparing(Produto::getNome).reversed()).collect(Collectors.toList());
            } else if (ORDENCAO_MAIOR_PRECO == ordenacao) {
                return estoqueRepository.listagem().stream().sorted(Comparator.comparing(Produto::getValor)).collect(Collectors.toList());
            } else if (ORDENCAO_MENOR_PRECO == ordenacao) {
                return estoqueRepository.listagem().stream().sorted(Comparator.comparing(Produto::getValor).reversed()).collect(Collectors.toList());
            }
        } catch (IOException e) {
            throw new NotFoundExceptionProduct(PRODUTO_ERRO_ORDENACAO);
        }
        return null;
    }

    private List<Produto> ordenaCategoria(String categoria, String ordenacao) throws IOException {
        if (ORDENCAO_AFABETICA_CRES.equals(ordenacao)) {
            return estoqueRepository.listagem().stream()
                    .filter(u -> categoria.equals(u.getCategoria().getNome()))
                    .sorted(Comparator.comparing(a -> a.getCategoria().getNome()))
                    .collect(Collectors.toList());
        } else if (ORDENCAO_AFABETICA_DEC.equals(ordenacao)) {
            return estoqueRepository.listagem().stream()
                    .filter(u -> marca.equals(u.getMarca())).sorted(Comparator.comparing(Produto::getMarca)).collect(Collectors.toList());
        } else if (ORDENCAO_AFABETICA_DEC == ordenacao) {
            return estoqueRepository.listagem().stream().sorted(Comparator.comparing(Produto::getMarca).reversed()).collect(Collectors.toList());
        }
        return null;
    }

    public List<Produto> listaProdutoFiltorPersonalizado(String nome, String categoria) throws NotFoundExceptionProduct{
        try {
            return estoqueRepository.listagem().stream()
                    .filter(u -> nome.equals(u.getNome())).filter(u -> categoria.equals(u.getCategoria().getNome()))
                    .collect(Collectors.toList());
        } catch (ErrorProcesamentoException e) {
            throw new NotFoundExceptionProduct(ERROR_LOCALIZAR_PELO_FILTRO);
        }
    }

    public List<Produto> listaProdutoFiltoNomeFrete(String nome, boolean frete) throws IOException {
        return  estoqueRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome())).filter(u -> frete == (u.isFreteGratis()))
                .collect(Collectors.toList());

    }

    public List<Produto> listaProdutoFiltorPersonalizado3(String nome, String marca) throws IOException {
        return estoqueRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome())).filter(u -> marca.equals(u.getMarca()))
                .collect(Collectors.toList());
    }

    public List<Produto> listaProdutoFiltorPersonalizado4(boolean frete, String categoria) throws ErrorProcesamentoException {
        return estoqueRepository.listagem().stream()
                .filter(u -> frete == (u.isFreteGratis())).filter(u -> categoria.equals(u.getCategoria().getNome()))
                .collect(Collectors.toList());
    }

    public List<Produto> listaProdutoFiltorPersonalizado5(String marca, int estrela) throws ErrorProcesamentoException {
        return estoqueRepository.listagem().stream()
                .filter(u -> marca.equals(u.getNome())).filter(u -> estrela == (u.getEstrelas()))
                .collect(Collectors.toList());
    }

    public List<Produto> listaProdutoFiltorPersonalizadoOrdenacao(String ordenacao, String categoria) {return  null;
    }

    public List<Produto> listaProdutoFiltorPersonalizadoOrdenacao2(String ordenacao, boolean frete) {return  null;
    }

    public List<Produto> listaProdutoFiltorPersonalizadoOrdenacao3(String ordenacao, String marca) {return  null;
    }

    public List<Produto> listaProdutoFiltorPersonalizadoOrdenacao4(String ordenacao, int qtdestrelas) {
        return  null;
    }

    public List<Produto> ordenaCategoria(int ordenacao, String categoria) throws ErrorProcesamentoException {
        if (ORDENCAO_AFABETICA_CRES == ordenacao) {
            return estoqueRepository.listagem().stream()
                    .filter(u -> categoria.equals(u.getCategoria().getNome())).sorted(Comparator.comparing(a -> a.getCategoria().getNome())).collect(Collectors.toList());
        } else if (ORDENCAO_AFABETICA_DEC == ordenacao) {
            return estoqueRepository.listagem().stream()
                    .filter(u -> categoria.equals(u.getCategoria().getNome())).sorted((a, b) -> b.getCategoria().getNome().compareTo(a.getCategoria().getNome())).collect(Collectors.toList());
        }
        return null;
    }
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
