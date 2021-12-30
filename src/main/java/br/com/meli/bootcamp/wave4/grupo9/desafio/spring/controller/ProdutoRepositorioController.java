package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.controller;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto.ProdutoDTO;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service.ProdutoRepositorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * @author Felipe
 */
@RestController
@RequestMapping("/api")
public class ProdutoRepositorioController {

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
}
