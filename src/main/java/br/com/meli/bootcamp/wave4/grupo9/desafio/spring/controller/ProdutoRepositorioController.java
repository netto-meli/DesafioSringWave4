package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.controller;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto.ProdutoDTO;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.EstoqueRepository;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service.ProdutoRepositorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

/***
 * @author Felipe
 */
@RestController
@RequestMapping("/api")
public class ProdutoRepositorioController {

	@Autowired
	private EstoqueRepository estoqueRepository;
	@Autowired
	private ProdutoRepositorioService produtoRepositorioService;

	@GetMapping("/produto/{id}")
	public ResponseEntity<ProdutoDTO> obter(@PathVariable Long id) {
		return ResponseEntity.ok(ProdutoDTO.converte(produtoRepositorioService.obter(id)));
	}

	@GetMapping("/produto")
	private ResponseEntity<List<ProdutoDTO>> getList() {
		//estoque.teste();
		List<Produto> listaProduto = produtoRepositorioService.lista();
		return ResponseEntity.ok(
				ProdutoDTO.
				converte(listaProduto));
	}

	@PostMapping("/produto/cadastrar")
	public ResponseEntity<ProdutoDTO> cadastrar(
			@RequestBody ProdutoDTO form,
			UriComponentsBuilder uriBuilder)
			throws IOException {
		Produto produto = ProdutoDTO.converte(form);
		estoqueRepository.salva(produto);
		URI uri = uriBuilder
				.path("/produto/{id}")
				.buildAndExpand(produto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(ProdutoDTO.converte(produto));
	}


}
