package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.controller;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto.PedidoDTO;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Pedido;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/loja")
public class CarrinhoController {

	@Autowired
	private CarrinhoService carrinhoService;

	@PostMapping("/adicionaNoCarrinho/{idCliente}")
	public ResponseEntity<PedidoDTO> adicionaProdutosNoCarrinho(
			@PathVariable String idCliente,
			@RequestParam(value = "idProduto", defaultValue = "0") String idProduto,
			@RequestParam(value = "qtdProduto", defaultValue = "0") String qtdProduto,
			UriComponentsBuilder uriBuilder) {
		Pedido pedidoAberto = carrinhoService.adicionarProdutosNoCarrinho(idCliente,idProduto,qtdProduto);
		URI uri = uriBuilder
				.path("/carrinhoAberto/{idCliente}")
				.buildAndExpand(pedidoAberto.getIdCliente())
				.toUri();
		return ResponseEntity.created(uri).body(PedidoDTO.converte(pedidoAberto));
	}

	@PostMapping("/retiraDoCarrinho/{idCliente}")
	public ResponseEntity<PedidoDTO>  retiraProdutosDoCarrinho(
			@PathVariable String idCliente,
			@RequestParam(value = "idProduto", defaultValue = "0") String idProduto,
			@RequestParam(value = "qtdRetirar", defaultValue = "0") String qtdRetirar,
			UriComponentsBuilder uriBuilder){
		Pedido pedidoAberto = carrinhoService.retirarProdutosDoCarrinho(idCliente,idProduto,qtdRetirar);
		URI uri = uriBuilder
				.path("/carrinhoAberto/{idCliente}")
				.buildAndExpand(pedidoAberto.getIdCliente())
				.toUri();
		return ResponseEntity.created(uri).body(PedidoDTO.converte(pedidoAberto));
	}

	@GetMapping("/limpaCarrinho/{idCliente}")
	public String limpaCarrinho(@PathVariable String idCliente){
		carrinhoService.limparCarrinho(idCliente);
		return "Seu carrinho está vazio";
	}

	@GetMapping("/carrinhoAberto/{idCliente}")
	public ResponseEntity<PedidoDTO>  exibirCarrinhoAberto(@PathVariable String idCliente){
		Pedido pedidoAberto = carrinhoService.exibirCarrinhoAberto(idCliente);
		return ResponseEntity.ok(PedidoDTO.converte(pedidoAberto));
	}

	@GetMapping("/fechaCarrinho/{idCliente}")
	public ResponseEntity<PedidoDTO> fecharPedido(@PathVariable String idCliente,
												   UriComponentsBuilder uriBuilder){
		Pedido pedido = carrinhoService.fecharCarrinho(idCliente);
		URI uri = uriBuilder
				.path("/exibePedido/{idPedido}")
				.buildAndExpand(pedido.getId())
				.toUri();
		return ResponseEntity.created(uri).body(PedidoDTO.converte(pedido));
	}
}
