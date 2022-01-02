package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto.CategoriaDTO;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Categoria;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 * Categoria Service:<br>
 *  <b>Lista todos as categorias</b><br>
 *  <b>Lista por id</b><br>
 *  <b>Insere Categoria</b><br>
 *
 * @author Marcos Sá
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
     */
    public List<Categoria> encontrarTodos() throws ErrorProcesamentoException{
        return repository.listagem();
    }

    /***
     *
     * @param id
     * @return listar todos as Categorias por id
     */
    public Categoria encontrarPorId(long id) throws ErrorProcesamentoException {
        return repository.listagem().stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /***
     *
     * @param obj
     * @return inserir categorias
     */
    public void inserir(Categoria obj) throws ErrorProcesamentoException{
        // TODO ver isso
        //obj.seId(repository.listarCategoria().size());
        repository.salva(obj);
    }

    /***
     *
     * @param objDto
     * @return Método que faz conversão de CategoriaDTO para Categoria
     */
    public Categoria fromDTO(CategoriaDTO objDto) {
        return new Categoria(objDto.getId(), objDto.getNome());
    }

}
