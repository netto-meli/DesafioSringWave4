package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Categoria;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * @author Marcos
 */
@Repository
public class CategoriaRepository implements OurRepository<Categoria, Long>{

    List<Categoria> categorias = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "categorias.json";

    public Cliente salva(Categoria categoria) {
        try {
            /*Mesclar duas ArrayList<>
            * List<String> newList = new ArrayList<String>(listOne);
                newList.addAll(listTwo);
            * */
            categorias = listagem();
            if ( categoria.getId() == null ) categoria.setId(getMaxId()+1L);
            List<Categoria> novaLista2 =new ArrayList<>();
            novaLista2.add(categoria);
            List<Categoria> novaLista = new ArrayList<>(categorias);
            novaLista.addAll(novaLista2);
            objectMapper.writeValue(new File(PATH), novaLista);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Categoria> listagem() {
        try {
            File file = new File(PATH);
            FileInputStream is = new FileInputStream(file);
            categorias = new ArrayList<>(Arrays.asList(objectMapper.readValue(is, Categoria[].class)));
            return categorias;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Categoria get(Long id) {
        return categorias.stream()
                .filter(u -> u.getId().equals(id) )
                .findAny()
                .orElse(null); // null se nao existe produto
    }

    private Long getMaxId(){
        Long id = 0L;
        for ( Categoria p : categorias ) {
            if (p.getId() != null && p.getId().compareTo(id) > 0 ){
                id = p.getId();
            }
        }
        return id;
    }
}
