package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.config;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Categoria;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Cliente;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Pedido;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.CategoriaRepository;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.ClienteRepository;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.EstoqueRepository;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

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
    public void run(String... args) throws Exception {


        pedidoRepository.listagem();
        clienteRepository.listagem();
        categoriaRepository.listagem();
        estoqueRepository.listagem();
        //runPedido();
        //runCliente();/*
        //runEstoque();

        //System.out.println(p1.getValorTotal());

    }

    private void runPedido() {
        Categoria cat1 = new Categoria(  1L, "Ferramentas");
        categoriaRepository.salva(cat1);

        Pedido p1 = new Pedido(1L, 1L, null, null, null);
        pedidoRepository.salva(p1);

        Pedido p2 = new Pedido(2L, 1L, null, null, null);
        pedidoRepository.salva(p2);

        Pedido p3 = new Pedido(3L, 0L, null, null, null);
        pedidoRepository.salva(p3);

        Pedido p4 = new Pedido(4L, 0L, null, null, null);
        pedidoRepository.salva(p4);


        for(Pedido p : pedidoRepository.listagem()) {
            System.out.println(p);
        }
    }

    private void runEstoque() throws IOException {

        Categoria c = new Categoria(1L,"");

        Produto p1 = new Produto(1L, "", c, "", BigDecimal.ZERO,true,1,3);
        estoqueRepository.salva(p1);

        Produto p2 = new Produto(2L, "", c, "", BigDecimal.ZERO,true,1,3);
        estoqueRepository.salva(p2);

        Produto p3 = new Produto(3L, "", c, "", BigDecimal.ZERO,true,1,3);
        estoqueRepository.salva(p3);

        Produto p4 = new Produto(4L, "", c, "", BigDecimal.ZERO,true,1,3);
        estoqueRepository.salva(p4);

/*
        for(Produto p : estoqueRepository.listagem()) {
            System.out.println(p);
        }*/
    }

    private void runCliente() {
        Cliente p1 = new Cliente(1L,"","","","");
        clienteRepository.salva(p1);
        System.out.println(p1);
    }
}
