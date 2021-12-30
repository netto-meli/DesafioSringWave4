package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.EstoqueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Felipe
 */
@Service
public class ProdutoRepositorioService {
    private EstoqueRepository estoqueRepository;
    private Logger logger = null;

    public ProdutoRepositorioService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
        this.logger = LoggerFactory.getLogger(ProdutoRepositorioService.class);
    }

    public void salvar(Produto produto) {
        if(true) {
            try {
                estoqueRepository.salva(produto);
                logger.debug("produto salvo");
            }catch(IOException e) {
                logger.error(e.getMessage());
            }
        }else {
            throw new RuntimeException("======");
        }
    }

    public List<Produto> lista(){
        try {
            return estoqueRepository.listagem().stream().collect(Collectors.toList());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }


    public Produto obter(long id) {
        return estoqueRepository.get(id);
    }
}
