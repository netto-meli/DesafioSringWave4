package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.*;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.NotFoundExceptionProduct;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.ClienteRepository;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/***
 * Service Pedido:<br>
 *  <b>Lista todos os pedidos</b><br>
 *  <b>Lista por id</b><br>
 *  <b>lista ordenada - 0 - Id Cliente crescente / 1 - Id Cliente Descrecente / 2 - Valor Total crescente / 3 - Valor Total descrecente</b><br>
 *
 * @author Marcos Sá
 */
@Service
public class PedidoService {

    /*** Instancia de pedido: <b>PedidoRepository</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    private PedidoRepository pedidoRepository;

    /*** Instancia de Cliente: <b>ClienteRepository</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    private ClienteRepository clienteRepository;

    /***s
     *
     * @return todos os pedidos
     */
    public List<Pedido> encontrarTodos() throws ErrorProcesamentoException {
        return pedidoRepository.listagem();
    }

    /***
     *
     * @param id
     * @return Lista de pedidos por id
     * @throws NotFoundExceptionProduct
     */
    public Pedido encontrarPorId(long id) throws ErrorProcesamentoException {
        Optional<Pedido> pedido = pedidoRepository.listagem().stream().filter(x -> x.getId() == id).findFirst();
        return pedido.orElse(null);
    }

    /***
     *
     * @param number
     * 0 - Id Cliente crescente
     * 1 - Id Cliente decrescente
     * 2 - Valor total crescente
     * 3 - Valor Total descrecente
     * @return Lista de pedidos ordenada
     */
    public List<Pedido> ordernarLista(Integer number) throws ErrorProcesamentoException{
        List<Pedido> lista = new ArrayList<>();

        // TODO poderia ser switch?
        if (number == 0) {
            lista = pedidoRepository.listagem().stream().sorted((p1, p2) -> sortCliente(p1,p2) ).collect(Collectors.toList());
        }
        else if(number == 1) {
            lista = pedidoRepository.listagem().stream().sorted((p2, p1) -> sortCliente(p2,p1)).collect(Collectors.toList());
        }
        else if(number == 2) {
            lista = pedidoRepository.listagem().stream().sorted((p1, p2) -> p1.getValorTotal().compareTo(p2.getValorTotal())).collect(Collectors.toList());
        }
        else if(number == 3) {
            lista = pedidoRepository.listagem().stream().sorted((p2, p1) -> p2.getValorTotal().compareTo(p1.getValorTotal())).collect(Collectors.toList());
        }

        return lista;
    }

    /***
     *
     * @param p1
     * @param p2
     * 0 - Id Cliente crescente
     * 1 - Id Cliente decrescente
     * 2 - Valor total crescente
     * 3 - Valor Total descrecente
     * @return Método que faz ordenação da lista de acordo com o id do cliente
     */
    private int sortCliente(Pedido p1, Pedido p2){
        Cliente cli1 = clienteRepository.get(p1.getIdCliente());
        Cliente cli2 = clienteRepository.get(p2.getIdCliente());
        return cli1.getNome().compareTo(cli2.getNome());
    }
}
