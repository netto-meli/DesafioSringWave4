package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.controller;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Pedido;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * @author Marcos
 */
@RestController
@RequestMapping("/loja")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping(value = "/pedidos")
    public ResponseEntity<List<Pedido>> encontrarTodos()
            throws ErrorProcesamentoException{
        List<Pedido> list = pedidoService.encontrarTodos();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/pedidos/{id}")
    public ResponseEntity<Pedido> encontrarPorId(@PathVariable long id)
            throws ErrorProcesamentoException{
        Pedido pedido = pedidoService.encontrarPorId(id);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping(value = "/pedidos/ordena/")
    public ResponseEntity<List<Pedido>> ordenacaoLista(@RequestParam (value = "number") Integer number)
            throws ErrorProcesamentoException {
        List<Pedido> lista = pedidoService.ordernarLista(number);
        return ResponseEntity.ok(lista);
    }
}
