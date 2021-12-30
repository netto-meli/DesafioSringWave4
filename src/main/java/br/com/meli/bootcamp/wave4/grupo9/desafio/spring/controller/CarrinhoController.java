package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.controller;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto.PedidoDTO;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Pedido;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/*** Controller dos métodos do carrinho:<br>
 * <b>Adiciona Produtos no Carrinho</b><br>
 * <b>Retira Produtos do Carrinho</b><br>
 * <b>Exibir Carrinho Aberto</b><br>
 * <b>Limpa Carrinho Aberto</b><br>
 * <b>Fechar Carrinho / Gerar Pedido</b>
 *
 * @author Fernando Netto
 */
@RestController
@RequestMapping("/loja")
public class CarrinhoController {

	/*** Instancia de serviço: <b>CarrinhoService</b> com notação <i>{@literal @}Autowired</i> do lombok
	 */
	@Autowired
	private CarrinhoService carrinhoService;

	/*** Método para adicionar novo produto ao carrinho de compras do cliente.<br>
	 * POST - /loja/adicionaNoCarrinho/{idCliente}{@literal ?}idProduto={idProduto}{@literal &}qtdProduto={qtdProduto}
	 *
	 * @param idCliente ID do Cliente que está fazendo o pedido
	 * @param idProduto ID do Produto que o cliente deseja acrescentar no carrinho de compras
	 * @param qtdProduto Quantos itens do produto selecionado, o Cliente deseja adicionar no carrinho
	 * @param uriBuilder UriComponentsBuilder que gera URI para o ResponseEntity
	 * @return Retorna payload de PedidoDTO em um ResponseEntity com status <b>CREATED</b> e
	 * <i>GET</i>: "/loja/carrinhoAberto/{idCliente}"
	 */
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

	/*** Método para retirar uma quantidadde "<i>qtdRetirar</i>" de um produto no carrinho de compras do cliente.<br>
	 * POST - /loja/retiraDoCarrinho/{idCliente}{@literal ?}idProduto={idProduto}{@literal &}qtdRetirar={qtdRetirar}
	 *
	 * @param idCliente ID do Cliente que está fazendo o pedido
	 * @param idProduto ID do Produto que o cliente deseja acrescentar no carrinho de compras
	 * @param qtdRetirar Quantos itens do produto selecionado, o Cliente deseja retirar do carrinho
	 * @param uriBuilder UriComponentsBuilder que gera URI para o ResponseEntity
	 * @return Retorna payload de PedidoDTO em um ResponseEntity com status <b>CREATED</b> e <i>GET</i>: "/loja/carrinhoAberto/{idCliente}"
	 */
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

	/*** Método para limpar o carrinho de compras do cliente.<br>
	 * POST - /loja/limpaCarrinho/{idCliente}
	 *
	 * @param idCliente ID do Cliente que está fazendo o pedido
	 * @return Retorna a mensagem "Seu carrinho está vazio"
	 */
	@GetMapping("/limpaCarrinho/{idCliente}")
	public String limpaCarrinho(@PathVariable String idCliente){
		carrinhoService.limparCarrinho(idCliente);
		return "Seu carrinho está vazio";
	}

	/*** Método para exibir os produtos do carrinho de compras do cliente.<br>
	 * POST - /loja/carrinhoAberto/{idCliente}
	 *
	 * @param idCliente ID do Cliente que está fazendo o pedido
	 * @return Retorna payload de PedidoDTO em um ResponseEntity com status <b>OK</b>
	 */
	@GetMapping("/carrinhoAberto/{idCliente}")
	public ResponseEntity<PedidoDTO>  exibirCarrinhoAberto(@PathVariable String idCliente){
		Pedido pedidoAberto = carrinhoService.exibirCarrinhoAberto(idCliente);
		return ResponseEntity.ok(PedidoDTO.converte(pedidoAberto));
	}

	/*** Método para o cliente fechar o pedido com os produtos no carrinho.<br>
	 * POST - /loja/fechaCarrinho/{idCliente}
	 *
	 * @param idCliente ID do Cliente que está fazendo o pedido
	 * @param uriBuilder UriComponentsBuilder que gera URI para o ResponseEntity
	 * @return Retorna payload de PedidoDTO em um ResponseEntity com status <b>CREATED</b> e
	 * <i>GET</i>: "/loja/exibePedido/{idPedido}" implementado no Controller: {@link br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto.PedidoDTO GETNOmme}
	 * @see br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto.PedidoDTO GetNOMEE
	 */
	@GetMapping("/fechaCarrinho/{idCliente}")
	//TODO Atualizar metodo no javadoc
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
