package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Categoria;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.RepositoryException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repository;

    public List<Categoria> encontrarTodos() throws ErrorProcesamentoException{
        return repository.listagem();
    }

    public Categoria encontrarPorId(long id) throws ErrorProcesamentoException {
        return repository.listagem().stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void inserir(Categoria obj) throws ErrorProcesamentoException, RepositoryException {
        verificarDados(obj);
        verificaDuplicidade(obj);
        repository.salva(obj);
    }

    private void verificaDuplicidade(Categoria categoria) throws ErrorProcesamentoException, RepositoryException {
        for (Categoria cat: repository.listagem()) {
            if (cat.equals(categoria))
                throw new RepositoryException("Já existe esta Categoria, favor cadastrar outra.");
        }
    }

    private void verificarDados(Categoria cat) throws ErrorProcesamentoException {
        if (cat.getNome() == null || cat.getNome().trim().equals("") )
            throw new ErrorProcesamentoException("Nome não pode estar vazio.");
    }

}
