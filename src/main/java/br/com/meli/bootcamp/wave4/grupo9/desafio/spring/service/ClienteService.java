package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Cliente;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.RepositoryException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    public List<Cliente> encontrarTodos() throws ErrorProcesamentoException{
        return repository.listagem();
    }

    public Cliente encontrarPorId(long id) throws ErrorProcesamentoException {
        return repository.listagem().stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void inserir(Cliente obj) throws ErrorProcesamentoException, RepositoryException {
        verificarDados(obj);
        verificarDuplicidade(obj);
        repository.salva(obj);
    }

    private void verificarDuplicidade(Cliente cliente) throws ErrorProcesamentoException, RepositoryException {
        for (Cliente cli: repository.listagem()) {
            if (cli.equals(cliente))
                throw new RepositoryException("Já existe um Cliente com este CPF, favor cadastrar outro.");
        }
    }

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

}
