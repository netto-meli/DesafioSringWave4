package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.config;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.CategoriaRepository;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.ClienteRepository;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.EstoqueRepository;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/***
 * @author Marcos
 */
@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private EstoqueRepository estoqueRepository;

    @Override
    public void run(String... args) throws ErrorProcesamentoException {
        pedidoRepository.listagem();
        clienteRepository.listagem();
        categoriaRepository.listagem();
        estoqueRepository.listagem();
    }
}
