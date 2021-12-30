package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.controller;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto.ProdutoDTO;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;import java.util.List;

/***
 * Controller dos métodos do carrinho:<br>
 *  * <b>Lista todos os produtos</b><br>
 *  * <b>Lista por categoria</b><br>
 *  * <b>lista por nome</b><br>
 *  * <b>Lista ordenando</b><br>
 *  * <b>lista ppersonalizada / com dois parametros</b>
 *  *
 *  * @author Leonardo Assuncao
 */

@RestController
@RequestMapping("/loja")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    /***
     *
     * @param uriBuilder
     * @return endpoint para listar todos os produtos
     */
    @PostMapping("/listarProdutos/")
    public ResponseEntity<ProdutoDTO> obterlista(
            UriComponentsBuilder uriBuilder) {
        Produto lista = (Produto) produtoService.listaProduto();
        URI uri = uriBuilder
                .path("/listarProdutos/")
                .buildAndExpand(lista)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converte(lista));
    }

    /***
     *
     * @param categoria
     * @return Lista de produtos filtrados pela categoria
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/listarProdutosCategoria/", method = RequestMethod.GET)
    public ResponseEntity<ProdutoDTO> obterCategoria(
            @RequestParam(value = "categoria", required = true) String categoria,
            UriComponentsBuilder uriBuilder) {
        Produto listaCategoria = (Produto) produtoService.listaProdutoCategoria(categoria);
        URI uri = uriBuilder
                .path("/listarProdutosCategoria/")
                .buildAndExpand(listaCategoria)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaCategoria));
    }

    /***
     *
     * @param ordenacao
     * @param uriBuilder
     * 0 - Alfabetica crescente
     * 1 - Alfabetica decrescente
     * 2 - Maior -> menor preco
     * 3 - Menor -> maior preco
     * @retur Lista de produtos filtrados pela categoria
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/listarProdutosOrdenado/", method = RequestMethod.GET)
    public ResponseEntity<ProdutoDTO> obterListaOrdenada(
            @RequestParam(value = "ordenacao", required = true) String ordenacao,
            @RequestParam(value = "nameOrPreco", required = false) String nameOrPreco,
            UriComponentsBuilder uriBuilder) throws IOException{
        Produto listaOrdenada = (Produto) produtoService.listaProdutoOrdenado(ordenacao, nameOrPreco);
        URI uri = uriBuilder
                .path("/listarProdutosOrdenado/")
                .buildAndExpand(listaOrdenada)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaOrdenada));
    }

    /***
     * @param nome
     * @param categoia
     * @param uriBuilder
     * @return lista filtrada pelo nome e categoria
     * @throws IOException
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/listarProdutosOrdenadoNomeCategoria/", method = RequestMethod.GET)
    public ResponseEntity<ProdutoDTO> obterProdutoDoisParametros(
            @RequestParam(value = "nome", required = true) String nome,
            @RequestParam(value = "categoria", required = true) String categoia,
            UriComponentsBuilder uriBuilder) throws IOException{
        Produto listaOrdenadaPersonaliza = (Produto) produtoService.listaProdutoFiltorPersonalizado(nome,categoia);
        URI uri = uriBuilder
                .path("/listarProdutosOrdenadoNomeCategoria/")
                .buildAndExpand(listaOrdenadaPersonaliza)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaOrdenadaPersonaliza));
    }



}
