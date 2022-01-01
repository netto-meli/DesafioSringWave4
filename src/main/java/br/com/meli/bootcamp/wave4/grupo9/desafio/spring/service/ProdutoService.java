package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.NotFoundExceptionProduct;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.RepositoryException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/***
 * Service implementação dos métodos do carrinho:<br>
 *  * <b>Lista todos os produtos</b><br>
 *  * <b>Lista por categoria</b><br>
 *  * <b>lista por nome</b><br>
 *  * <b>Lista ordenando</b><br>
 *  * <b>lista personalizada / com dois parâmetros</b>
 *  *
 *  * @author Leonardo Assunção
 */
@Service
public class ProdutoService {

    /***
     * Variáveis final static para ser utilizador no metodo de ordenação
     */
    private static final int ORDENCAO_AFABETICA_DEC = 0;
    private static final int ORDENCAO_AFABETICA_CRES = 1;
    private static final int ORDENCAO_MAIOR_PRECO = 2;
    private static final int ORDENCAO_MENOR_PRECO = 3;
    private static final String PRODUTO_AINDA_NAO_DISPONIVEL = "OPS! Nao temos este produto ainda disponível, logo atualizaremos o catalogo da loja, desculpe pelo incoveniente";
    private static final String PRODUTO_ERRO_ORDENACAO = "Tivemos um erro na ordenação tente novamente mais tarde =]";
    private static final String ERROR_LOCALIZAR_PELO_FILTRO = "Tivemos um erro ao tentar filtrar pelos parâmetros enviados, tente novamente";
    /*** Instancia de estoque: <b>EstoqueRepository</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    private EstoqueRepository estoqueRepository;

    public List<Produto> listaProduto() {
        try {
            return estoqueRepository.listagem();
        } catch (Exception e) {
            throw new RepositoryException("erro ao localizar produtos");
        }
    }

    public List<Produto> listaProdutoCategoria(Long idCategoria) throws NotFoundExceptionProduct {
        try {
            return estoqueRepository.listagem().stream()
                    .filter(p -> p.getCategoria().getId().equals(idCategoria)).
                    collect(Collectors.toList());
        } catch (Exception e) {
            throw new NotFoundExceptionProduct(PRODUTO_AINDA_NAO_DISPONIVEL);
        }
    }

    public List<Produto> listaProdutoOrdenado(int ordenacao) throws NotFoundExceptionProduct{
        try {
            // TODO poderia ser switch?
            if (ORDENCAO_AFABETICA_CRES == ordenacao) {
                return estoqueRepository.listagem().stream()
                        .sorted(Comparator.comparing(Produto::getNome))
                        .collect(Collectors.toList());
            } else if (ORDENCAO_AFABETICA_DEC == ordenacao) {
                return estoqueRepository.listagem().stream()
                        .sorted(Comparator.comparing(Produto::getNome).reversed())
                        .collect(Collectors.toList());
            } else if (ORDENCAO_MAIOR_PRECO == ordenacao) {
                return estoqueRepository.listagem().stream()
                        .sorted(Comparator.comparing(Produto::getValor))
                        .collect(Collectors.toList());
            } else if (ORDENCAO_MENOR_PRECO == ordenacao) {
                return estoqueRepository.listagem().stream()
                        .sorted(Comparator.comparing(Produto::getValor).reversed())
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            throw new NotFoundExceptionProduct(PRODUTO_ERRO_ORDENACAO);
        }
        return null;
    }

    public List<Produto> listaProdutoFiltroNomeCategoria(String nome, String categoria) throws NotFoundExceptionProduct{
        try {
            return estoqueRepository.listagem().stream()
                    .filter(u -> nome.equals(u.getNome()))
                    .filter(u -> categoria.equals(u.getCategoria().getNome()))
                    .collect(Collectors.toList());
        } catch (ErrorProcesamentoException e) {
            throw new NotFoundExceptionProduct(ERROR_LOCALIZAR_PELO_FILTRO);
        }
    }

    public List<Produto> listaProdutoFiltroNomeFrete(String nome, boolean frete) throws ErrorProcesamentoException{
        return  estoqueRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome()))
                .filter(u -> frete == (u.isFreteGratis()))
                .collect(Collectors.toList());
    }

    public List<Produto> listaProdutoFiltroNomeMarca(String nome, String marca) throws ErrorProcesamentoException{
        return estoqueRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome()))
                .filter(u -> marca.equals(u.getMarca()))
                .collect(Collectors.toList());
    }

    public List<Produto> listaProdutoFiltroFreteCategoria(boolean frete, String categoria) throws ErrorProcesamentoException {
        return estoqueRepository.listagem().stream()
                .filter(u -> frete == (u.isFreteGratis()))
                .filter(u -> categoria.equals(u.getCategoria().getNome()))
                .collect(Collectors.toList());
    }

    //TODO ainda nao utilizado
    public List<Produto> listaProdutoFiltroMarcaEstrela(String marca, int estrela) throws ErrorProcesamentoException {
        return estoqueRepository.listagem().stream()
                .filter(u -> marca.equals(u.getNome()))
                .filter(u -> estrela == (u.getEstrelas()))
                .collect(Collectors.toList());
    }

    // TODO ainda nao utilizado
    public List<Produto> ordenaFrete(int ordenacao, boolean frete) throws ErrorProcesamentoException {
        if (ORDENCAO_AFABETICA_CRES == ordenacao) {
            return estoqueRepository.listagem().stream()
                    .filter(u -> (frete == u.isFreteGratis()))
                    .sorted(Comparator.comparing(Produto::isFreteGratis))
                    .collect(Collectors.toList());
        } else if (ORDENCAO_AFABETICA_DEC == ordenacao) {
            return estoqueRepository.listagem().stream()
                    .sorted(Comparator.comparing(Produto::isFreteGratis).reversed())
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<Produto> ordenaCategoria(int ordenacao, String categoria) throws ErrorProcesamentoException {
        if (ORDENCAO_AFABETICA_CRES == ordenacao) {
            return estoqueRepository.listagem().stream()
                    .filter(u -> categoria.equals(u.getCategoria().getNome()))
                    .sorted(Comparator.comparing(a -> a.getCategoria().getNome()))
                    .collect(Collectors.toList());
        } else if (ORDENCAO_AFABETICA_DEC == ordenacao) {
            return estoqueRepository.listagem().stream()
                    .filter(u -> categoria.equals(u.getCategoria().getNome()))
                    .sorted((a, b) -> b.getCategoria().getNome().compareTo(a.getCategoria().getNome()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<Produto> ordenaMarca(int ordenacao, String marca) throws ErrorProcesamentoException {
        if (ORDENCAO_AFABETICA_CRES == ordenacao) {
            return estoqueRepository.listagem().stream()
                    .filter(u -> marca.equals(u.getMarca()))
                    .sorted(Comparator.comparing(Produto::getMarca))
                    .collect(Collectors.toList());
        } else if (ORDENCAO_AFABETICA_DEC == ordenacao) {
            return estoqueRepository.listagem().stream()
                    .sorted(Comparator.comparing(Produto::getMarca).reversed())
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<Produto> ordenaEstrelas(int ordenacao, int qtdestrelas) throws ErrorProcesamentoException {
        if (ORDENCAO_AFABETICA_CRES == ordenacao) {
            return estoqueRepository.listagem().stream()
                    .filter(u -> (qtdestrelas == u.getEstrelas()))
                    .sorted(Comparator.comparing(Produto::getEstrelas))
                    .collect(Collectors.toList());
        } else if (ORDENCAO_AFABETICA_DEC == ordenacao) {
            return estoqueRepository.listagem().stream()
                    .sorted(Comparator.comparing(Produto::getEstrelas).reversed())
                    .collect(Collectors.toList());
        }
        return null;
    }

    // TODO este é um agregador de todas ordenações? nao precisa mais?
    /*
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
    */

    public List<Produto> salvaLista(List<Produto> listaProduto) throws ErrorProcesamentoException{
        estoqueRepository.salvaLista(listaProduto);
        return listaProduto;
    }

    public Produto obter(long id) {
        return estoqueRepository.get(id);
    }

    public void salvar(Produto produto) throws ErrorProcesamentoException{
        estoqueRepository.salva(produto);
    }
}
