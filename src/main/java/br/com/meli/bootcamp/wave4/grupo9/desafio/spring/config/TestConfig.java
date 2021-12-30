package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.config;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Categoria;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Pedido;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.CategoriaRepository;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = new Categoria(1L, "Ferramentas");
        categoriaRepository.salvarCategoria(cat1);

        Pedido p1 = new Pedido(1L, null, null, BigDecimal.ZERO);
        pedidoRepository.salvarPedido(p1);

        Pedido p2 = new Pedido(2L, null, null, BigDecimal.ZERO);
        pedidoRepository.salvarPedido(p2);

        Pedido p3 = new Pedido(3L, null, null, BigDecimal.ZERO);
        pedidoRepository.salvarPedido(p3);

        Pedido p4 = new Pedido(4L, null, null, BigDecimal.ZERO);
        pedidoRepository.salvarPedido(p4);


        for(Pedido p : pedidoRepository.listarPedido()) {
            System.out.println(p);
        }

        //System.out.println(p1.getValorTotal());

    }
}
