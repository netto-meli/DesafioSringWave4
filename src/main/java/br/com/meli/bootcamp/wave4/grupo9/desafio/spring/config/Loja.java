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
 * Classe implementa CommandLineRunner e inicia todos os repositórios na memória
 *
 * @author Marcos
 * @author Fernando Netto
 * @see org.springframework.boot.CommandLineRunner
 */
@Configuration
public class Loja implements CommandLineRunner {

    /*** Instancia de serviço: <b>PedidoRepository</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    private PedidoRepository pedidoRepository;
    /*** Instancia de serviço: <b>ClienteRepository</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    private ClienteRepository clienteRepository;
    /*** Instancia de serviço: <b>CategoriaRepository</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    private CategoriaRepository categoriaRepository;
    /*** Instancia de serviço: <b>EstoqueRepository</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    private EstoqueRepository estoqueRepository;

    /*** Inicializa todos repositórios
     *
     * @param args metodo padrào
     * @throws ErrorProcesamentoException Exceção ao carregar os JSON em memória.
     */
    @Override
    public void run(String... args) throws ErrorProcesamentoException {
        pedidoRepository.listagem();
        clienteRepository.listagem();
        categoriaRepository.listagem();
        estoqueRepository.listagem();
    }
}
