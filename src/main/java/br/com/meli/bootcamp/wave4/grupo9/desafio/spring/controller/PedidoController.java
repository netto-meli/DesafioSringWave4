package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.controller;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Pedido;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
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
 * @author Marcos
 */
@RestController
@RequestMapping("/loja")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping(value = "/pedidos")
    public ResponseEntity<List<Pedido>> encontrarTodos() {
        try {
            List<Pedido> list = pedidoService.encontrarTodos();
            return ResponseEntity.ok().body(list);
        } catch (ErrorProcesamentoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    @GetMapping(value = "/pedidos/{id}")
    public ResponseEntity<Pedido> encontrarPorId(@PathVariable long id) {
        try {
            Pedido pedido = pedidoService.encontrarPorId(id);
            return ResponseEntity.ok(pedido);
        } catch (ErrorProcesamentoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    @GetMapping(value = "/pedidos/ordena/")
    public ResponseEntity<List<Pedido>> ordenacaoLista(@RequestParam (value = "number") Integer number) {
        try {
            List<Pedido> lista = pedidoService.ordernarLista(number);
            return ResponseEntity.ok(lista);
        } catch (ErrorProcesamentoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }
}
