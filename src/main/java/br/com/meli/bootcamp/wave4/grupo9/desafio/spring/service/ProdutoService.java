package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.EstoqueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    private EstoqueRepository estoqueRepository;
    private Logger logger = null;

    public ProdutoService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
        this.logger = LoggerFactory.getLogger(ProdutoService.class);
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
        //List<Produto> produtos = null;
        try {
            return estoqueRepository.listagem().stream().collect(Collectors.toList());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }



    public Produto obter(Long id) {
        return estoqueRepository.get(id);
    }
}
