package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.controller;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto.ProdutoDTO;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/***
 * @author Leo
 */
@RestController
@RequestMapping("/store")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    /***
     *
     * @return endpoint para listar todos os produtos
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/listarProdutos", method = RequestMethod.GET)
    public ResponseEntity<List<ProdutoDTO>> obter() {
        return ResponseEntity.ok(ProdutoDTO.converte(produtoService.listaProduto()));
    }

    /***
     *
     * @param categoria cat
     * @return Lista de produtos filtrados pela categoria
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/listarProdutosCategoria", method = RequestMethod.GET)
    public ResponseEntity<List<ProdutoDTO>> obterCategoria(@RequestParam(value = "categoria", required = true) String categoria) {
        return ResponseEntity.ok(ProdutoDTO.converte(produtoService.listaProdutoCategoria(categoria)));
    }

    /***
     *
     * @param ordenacao Parametro de ordenação
     * @return Lista de produtos filtrados pela categoria
     * 0 - Alfabetica crescente
     * 1 - Alfabetica decrescente
     * 2 - Maior para menor preco
     * 3 - Menor para maior preco
     * @throws IOException exc
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/listarProdutosOrdenado", method = RequestMethod.GET)
    public ResponseEntity<List<ProdutoDTO>> obterListaOrdenada(
            @RequestParam(value = "ordenacao", required = true) String ordenacao) throws IOException {
        return ResponseEntity.ok(ProdutoDTO.converte(produtoService.listaProdutoOrdenado(ordenacao)));
    }

    /***
     *
     * @param nome nome
     * @param categoria cat
     * @return lista filtrada pelo nome e categoria
     * @throws IOException exceção
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/listarProdutosOrdenado")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoDoisParametros(@RequestParam String nome, String categoria) throws IOException {
        return ResponseEntity.ok(ProdutoDTO.converte(produtoService.listaProdutoFiltorPersonalizado(nome, categoria)));
    }


}
