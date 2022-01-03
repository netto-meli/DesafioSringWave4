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
 *  Verifica Duplicidade<br>
 *  Verifica Dados<br>
 *  *
 *  * @author Leonardo Assunção
 */
@Service
public class ProdutoService {

    /***
     * Variáveis final static para ser utilizador no metodo de ordenação
     */
    private static final int ORDENCAO_AFABETICA_DEC = 0;
    /***
     * Variáveis final static para ser utilizador no metodo de ordenação
     */
    private static final int ORDENCAO_AFABETICA_CRES = 1;
    /***
     * Variáveis final static para ser utilizador no metodo de ordenação
     */
    private static final int ORDENCAO_MAIOR_PRECO = 2;
    /***
     * Variáveis final static para ser utilizador no metodo de ordenação
     */
    private static final int ORDENCAO_MENOR_PRECO = 3;
    /***
     * Variáveis final static para ser utilizador no metodo de ordenação
     */
    private static final String PRODUTO_AINDA_NAO_DISPONIVEL = "OPS! Nao temos este produto ainda disponível, logo atualizaremos o catalogo da loja, desculpe pelo incoveniente";
    /***
     * Variáveis final static para ser utilizador no metodo de ordenação
     */
    private static final String PRODUTO_ERRO_ORDENACAO = "Tivemos um erro na ordenação tente novamente mais tarde =]";
    /***
     * Variáveis final static para ser utilizador no metodo de ordenação
     */
    private static final String ERROR_LOCALIZAR_PELO_FILTRO = "Tivemos um erro ao tentar filtrar pelos parâmetros enviados, tente novamente";
    /*** Instancia de estoque: <b>EstoqueRepository</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    private EstoqueRepository estoqueRepository;

    /***
     *
     * @return lista de produtos
     * @throws RepositoryException Erro ao tentar persistir os dados
     */
    public List<Produto> listaProduto() throws RepositoryException {
        try {
            return estoqueRepository.listagem();
        } catch (Exception e) {
            throw new RepositoryException("erro ao localizar produtos");
        }
    }

    /***
     *
     * @param idCategoria id categoria
     * @return lista de produtos por categoria
     * @throws NotFoundExceptionProduct Erro ao tentar encontrar os dados
     */
    public List<Produto> listaProdutoCategoria(Long idCategoria) throws NotFoundExceptionProduct {
        try {
            return estoqueRepository.listagem().stream()
                    .filter(p -> p.getCategoria().getId().equals(idCategoria)).
                    collect(Collectors.toList());
        } catch (Exception e) {
            throw new NotFoundExceptionProduct(PRODUTO_AINDA_NAO_DISPONIVEL);
        }
    }

    /***
     *
     * @param ordenacao ordenação
     * @return lista de produtos
     * @throws NotFoundExceptionProduct Erro ao tentar encontrar os dados
     */
    public List<Produto> listaProdutoOrdenado(int ordenacao) throws NotFoundExceptionProduct {
        try {
            switch (ordenacao) {
                case ORDENCAO_AFABETICA_DEC:
                    return estoqueRepository.listagem().stream()
                            .sorted(Comparator.comparing(Produto::getNome).reversed())
                            .collect(Collectors.toList());
                case ORDENCAO_AFABETICA_CRES:
                    return estoqueRepository.listagem().stream()
                            .sorted(Comparator.comparing(Produto::getNome))
                            .collect(Collectors.toList());
                case ORDENCAO_MAIOR_PRECO:
                    return estoqueRepository.listagem().stream()
                            .sorted(Comparator.comparing(Produto::getValor))
                            .collect(Collectors.toList());
                case ORDENCAO_MENOR_PRECO:
                    return estoqueRepository.listagem().stream()
                            .sorted(Comparator.comparing(Produto::getValor).reversed())
                            .collect(Collectors.toList());
            }
        } catch (Exception e) {
            throw new NotFoundExceptionProduct(PRODUTO_ERRO_ORDENACAO);
        }
        return null;
    }

    /***
     *
     * @param nome nome
     * @param categoria categoria
     * @return lista ordenada
     * @throws NotFoundExceptionProduct Erro ao tentar encontrar os dados
     */
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

    /***
     *
     * @param nome nome
     * @param frete frete
     * @return lista produtos
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     */
    public List<Produto> listaProdutoFiltroNomeFrete(String nome, boolean frete) throws ErrorProcesamentoException{
        return  estoqueRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome()))
                .filter(u -> frete == (u.isFreteGratis()))
                .collect(Collectors.toList());
    }

    /***
     *
     * @param nome nome
     * @param marca marca
     * @return lista produtos
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     */
    public List<Produto> listaProdutoFiltroNomeMarca(String nome, String marca) throws ErrorProcesamentoException{
        return estoqueRepository.listagem().stream()
                .filter(u -> nome.equals(u.getNome()))
                .filter(u -> marca.equals(u.getMarca()))
                .collect(Collectors.toList());
    }

    /***
     *
     * @param frete frete
     * @param categoria categoria
     * @return lista produtos
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     */
    public List<Produto> listaProdutoFiltroFreteCategoria(boolean frete, String categoria) throws ErrorProcesamentoException {
        return estoqueRepository.listagem().stream()
                .filter(u -> frete == (u.isFreteGratis()))
                .filter(u -> categoria.equals(u.getCategoria().getNome()))
                .collect(Collectors.toList());
    }

    /***
     *
     * @param estrelas estrelas
     * @param marca marca
     * @return lista produtos
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     */
    public List<Produto> listaProdutoFiltroMarcaEstrela(int estrelas, String marca) throws ErrorProcesamentoException {
        return estoqueRepository.listagem().stream()
                .filter(u -> marca.equals(u.getNome()))
                .filter(u -> estrelas == (u.getEstrelas()))
                .collect(Collectors.toList());
    }

    /***
     *
     * @param ordenacao ordenação
     * @param categoria categoria
     * @return lista produtos
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     */
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

    /***
     *
     * @param ordenacao ordenação
     * @param marca marca
     * @return lista produtos
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     */
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

    /***
     *
     * @param ordenacao ordenação
     * @param qtdestrelas quantidade estrelas
     * @return lista produtos
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     */
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

    /***
     *
     * @param listaProduto lista de produtos
     * @return produtos salvos
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     */
    public List<Produto> salvaLista(List<Produto> listaProduto) throws ErrorProcesamentoException{
        estoqueRepository.salvaLista(listaProduto);
        return listaProduto;
    }

    /***
     *
     * @param id id produto
     * @return produto
     * @throws NotFoundExceptionProduct Erro ao tentar encontrar os dados
     */
    public Produto obter(Long id) throws NotFoundExceptionProduct {
        Produto prod = estoqueRepository.get(id);
        if (prod == null) throw new NotFoundExceptionProduct("Produto não encontrado");
        return prod;
    }

    /***
     *
     * @param produto produto
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     * @throws RepositoryException Erro ao tentar persistir os dados
     */
    public void salvar(Produto produto) throws ErrorProcesamentoException, RepositoryException {
        verificarDados(produto);
        verificaDuplicidade(produto);
        estoqueRepository.salva(produto);
    }

    /** Método que verifica se há outro objeto igual no repositório e retorna o erro se houver
     *
     * @param produto produto a ser comparado
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     * @throws RepositoryException Erro ao tentar persistir os dados
     */
    private void verificaDuplicidade(Produto produto) throws ErrorProcesamentoException, RepositoryException {
        for (Produto prod: estoqueRepository.listagem()) {
            if (prod.equals(produto))
                throw new RepositoryException("Já existe um Produto com esta ID, favor cadastrar outro.");
        }
    }

    /** Método que verifica se o objeto é valido para ser inserido no repositório e retorna o erro se houver
     *
     * @param prod produto a ser verificado
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     */
    private void verificarDados(Produto prod) throws ErrorProcesamentoException {
        String erros = "";
        if (prod.getNome() == null || prod.getNome().trim().equals("") )
            erros = erros + "Nome não pode estar vazio, ";
        if (prod.getMarca() == null || prod.getMarca().trim().equals("") )
            erros = erros + "Marca não pode estar vazia, ";
        if (erros.length() > 0) {
            erros = "Foram encontrados os seguintes erros: " + erros + "Favor corrigir";
            throw new ErrorProcesamentoException(erros);
        }
    }
}
