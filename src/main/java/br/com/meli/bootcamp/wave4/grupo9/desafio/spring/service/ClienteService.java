package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Cliente;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.RepositoryException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/***
 * Cliente Service:<br>
 *  <b>Lista todos as clientes</b><br>
 *  <b>Lista por id</b><br>
 *  <b>Insere Cliente</b><br>
 *  Verifica Duplicidade<br>
 *  Verifica Dados<br>
 *
 * @author Rafael
 * @author Marcos Sá
 * @author Fernando Netto
 */
@Service
public class ClienteService {

    /*** Instancia de repositório: <b>ClienteRepository</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    ClienteRepository repository;

    /***
     *
     * @return listar todos as Categorias
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     */
    public List<Cliente> encontrarTodos() throws ErrorProcesamentoException{
        return repository.listagem();
    }

    /***
     *
     * @param id id
     * @return listar todos as Categorias por id
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     */
    public Cliente encontrarPorId(long id) throws ErrorProcesamentoException {
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
    public void inserir(Cliente obj) throws ErrorProcesamentoException, RepositoryException {
        verificarDados(obj);
        verificarDuplicidade(obj);
        repository.salva(obj);
    }

    /** Método que verifica se há outro objeto igual no repositório e retorna o erro se houver
     *
     * @param cliente cliente a ser comparado
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     * @throws RepositoryException Erro ao tentar persistir os dados
     */
    private void verificarDuplicidade(Cliente cliente) throws ErrorProcesamentoException, RepositoryException {
        for (Cliente cli: repository.listagem()) {
            if (cli.equals(cliente))
                throw new RepositoryException("Já existe um Cliente com este CPF, favor cadastrar outro.");
        }
    }

    /** Método que verifica se o objeto é valido para ser inserido no repositório e retorna o erro se houver
     *
     * @param cliente cliente a ser verificado
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     */
    private void verificarDados(Cliente cliente) throws ErrorProcesamentoException {
        String erros = "";
        if (cliente.getNome() == null || cliente.getNome().trim().equals("") )
            erros = erros + "Nome não pode estar vazio, ";
        if (cliente.getCpf() == null || cliente.getCpf().trim().equals("") )
            erros = erros + "CPF não pode estar vazio, ";
        if (!cliente.getCpf().matches("[0-9]+") )
            erros = erros + "CPF deve ser numérico, ";
        if (cliente.getEndereco() == null || cliente.getEndereco().trim().equals("") )
            erros = erros + "Endereço não pode estar vazio, ";
        if (cliente.getEstado() == null || cliente.getEstado().trim().equals("") )
            erros = erros + "Estado não pode estar vazio, ";
        if (cliente.getEstado().length() != 2 )
            erros = erros + "Estado deve conter 2 caracteres, ";
        if (erros.length() > 0) {
            erros = "Foram encontrados os seguintes erros: " + erros + "Favor corrigir";
            throw new ErrorProcesamentoException(erros);
        }
    }

    /*** Método que ordena clientes pelo estado
     *
     * @param ordem
     * 0 - crescente
     * 1 - decrescente
     * @return Lista de pedidos ordenada
     * @throws ErrorProcesamentoException Erro ao tentar buscar os dados
     */
    public List<Cliente> ordenarLista(Integer ordem) throws ErrorProcesamentoException {
        try {
            switch (ordem) {
                case 0:
                    return repository.listagem().stream()
                            .sorted(Comparator.comparing(Cliente::getEstado))
                            .collect(Collectors.toList());
                case 1:
                    return repository.listagem().stream()
                            .sorted(Comparator.comparing(Cliente::getEstado).reversed())
                            .collect(Collectors.toList());
            }
        } catch (Exception e){
            throw new ErrorProcesamentoException("Erro na Ordenação");
        }
        return null;
    }
}
