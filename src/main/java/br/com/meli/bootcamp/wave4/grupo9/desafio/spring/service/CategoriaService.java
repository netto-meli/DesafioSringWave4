package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Categoria;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.RepositoryException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 * Categoria Service:<br>
 *  <b>Lista todos as categorias</b><br>
 *  <b>Lista por id</b><br>
 *  <b>Insere Categoria</b><br>
 *  Verifica Duplicidade<br>
 *  Verifica Dados<br>
 *
 * @author Marcos Sá
 * @author Fernando Netto
 */
@Service
public class CategoriaService {

    /*** Instancia de Categoria: <b>CategoriaRepository</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    CategoriaRepository repository;

    /***
     *
     * @return listar todos as Categorias
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     */
    public List<Categoria> encontrarTodos() throws ErrorProcesamentoException{
        return repository.listagem();
    }

    /***
     *
     * @param id id
     * @return listar todos as Categorias por id
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     */
    public Categoria encontrarPorId(long id) throws ErrorProcesamentoException {
        return repository.listagem().stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /***
     *
     * @param obj obj
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     * @throws RepositoryException Erro ao tentar persistir os dados
     */
    public void inserir(Categoria obj) throws ErrorProcesamentoException, RepositoryException {
        verificarDados(obj);
        verificaDuplicidade(obj);
        repository.salva(obj);
    }

    /** Método que verifica se há outro objeto igual no repositório e retorna o erro se houver
     *
     * @param categoria categoria a ser comparada
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     * @throws RepositoryException Erro ao tentar persistir os dados
     */
    private void verificaDuplicidade(Categoria categoria) throws ErrorProcesamentoException, RepositoryException {
        for (Categoria cat: repository.listagem()) {
            if (cat.equals(categoria))
                throw new RepositoryException("Já existe esta Categoria, favor cadastrar outra.");
        }
    }

    /** Método que verifica se o objeto é valido para ser inserido no repositório e retorna o erro se houver
     *
     * @param cat categoria a ser verificada
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     */
    private void verificarDados(Categoria cat) throws ErrorProcesamentoException {
        if (cat.getNome() == null || cat.getNome().trim().equals("") )
            throw new ErrorProcesamentoException("Nome não pode estar vazio.");
    }
}
