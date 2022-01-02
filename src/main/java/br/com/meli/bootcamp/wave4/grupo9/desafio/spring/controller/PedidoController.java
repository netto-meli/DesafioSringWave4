package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.controller;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Pedido;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.NotFoundExceptionProduct;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * Controller Pedido:<br>
 *  <b>Lista todos os pedidos</b><br>
 *  <b>Lista por id</b><br>
 *  <b>lista ordenada - 0 - Id Cliente crescente / 1 - Id Cliente Descrecente / 2 - Valor Total crescente / 3 - Valor Total descrecente</b><br>
 *
 * @author Marcos Sá
 */
@RestController
@RequestMapping("/loja")
public class PedidoController {

    /*** Instancia de pedido: <b>PedidoService</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    private PedidoService pedidoService;

    /***s
     *
     * @return endpoint para listar todos os pedidos
     */
    @GetMapping(value = "/pedidos")
    public ResponseEntity<List<Pedido>> encontrarTodos()
            throws ErrorProcesamentoException{
        List<Pedido> list = pedidoService.encontrarTodos();
        return ResponseEntity.ok().body(list);
    }

    /***
     *
     * @param id
     * @return Lista de pedidos por id
     */
    @GetMapping(value = "/pedidos/{id}")
    public ResponseEntity<Pedido> encontrarPorId(@PathVariable long id)
            throws ErrorProcesamentoException{
        Pedido pedido = pedidoService.encontrarPorId(id);
        return ResponseEntity.ok(pedido);
    }

    /***
     *
     * @param number
     * 0 - Id Cliente crescente
     * 1 - Id Cliente decrescente
     * 2 - Valor total crescente
     * 3 - Valor Total descrecente
     * @return Lista de pedidos
     */
    @GetMapping(value = "/pedidos/ordena/")
    public ResponseEntity<List<Pedido>> ordenacaoLista(@RequestParam (value = "number") Integer number)
            throws ErrorProcesamentoException {
        List<Pedido> lista = pedidoService.ordernarLista(number);
        return ResponseEntity.ok(lista);
    }
}
