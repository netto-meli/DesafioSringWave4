package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.controller;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto.ProdutoDTO;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.NotFoundExceptionProduct;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
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
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutos")
    public ResponseEntity<List<ProdutoDTO>> obterlista(
            UriComponentsBuilder uriBuilder) {
        List<Produto> lista = produtoService.listaProduto();
        URI uri = uriBuilder
                .path("/listarProdutos")
                .buildAndExpand(lista)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converteList(lista));
    }

    /***
     *
     * @param categoria
     * @return Lista de produtos filtrados pela categoria
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosCategoria")
    public ResponseEntity<List<ProdutoDTO>> obterCategoria(
            @RequestParam(value = "categoria") String categoria,
            UriComponentsBuilder uriBuilder) throws NotFoundExceptionProduct {
        List<Produto> listaCategoria = produtoService.listaProdutoCategoria(categoria);
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
     * @return Lista de produtos filtrados pela categoria, nome e marca ordenados
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosOrdenado")
    public ResponseEntity<List<ProdutoDTO>> obterListaOrdenada(
            @RequestParam(value = "ordenacao", required = true) int ordenacao,
            UriComponentsBuilder uriBuilder) throws NotFoundExceptionProduct {
        List<Produto> listaOrdenada = produtoService.listaProdutoOrdenado(ordenacao);
        URI uri = uriBuilder
                .path("/listarProdutosOrdenado")
                .buildAndExpand(listaOrdenada)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converteList(listaOrdenada));
    }

    /***
     * @param nome
     * @param categoia
     * @param uriBuilder
     * @return lista filtrada pelo nome e categoria
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosNomeCategoria")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoDoisParametros(
            @RequestParam(value = "nome") String nome,
            @RequestParam(value = "categoria") String categoia,
            UriComponentsBuilder uriBuilder) throws NotFoundExceptionProduct {
        List<Produto> listaOrdenadaPersonaliza = produtoService.listaProdutoFiltorPersonalizado(nome, categoia);
        URI uri = uriBuilder
                .path("/listarProdutosNomeCategoria")
                .buildAndExpand(listaOrdenadaPersonaliza)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converteList(listaOrdenadaPersonaliza));
    }

    /***
     *
     * @param nome
     * @param frete
     * @param uriBuilder
     * @return filtra por nome e frete
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosNomeFrete")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoNomeFrete(
            @RequestParam(value = "nome") String nome,
            @RequestParam(value = "frete") boolean frete,
            UriComponentsBuilder uriBuilder) throws IOException {
        List<Produto> listaOrdenadaNomeFrete = produtoService.listaProdutoFiltoNomeFrete(nome, frete);
        URI uri = uriBuilder
                .path("/listarProdutosNomeFrete")
                .buildAndExpand(listaOrdenadaNomeFrete)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converteList(listaOrdenadaNomeFrete));
    }
///-------------Validado ate aqui___________________
    /***
     *
     * @param nome
     * @param marca
     * @param uriBuilder
     * @return filtra por nome e marca
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosNomeMarca")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoDoisParametros3(
            @RequestParam(value = "nome", required = true) String nome,
            @RequestParam(value = "marca", required = true) String marca,
            UriComponentsBuilder uriBuilder) throws IOException {
        List<Produto> listaOrdenadaPersonaliza3 = produtoService.listaProdutoFiltorPersonalizado3(nome, marca);
        URI uri = uriBuilder
                .path("/listarProdutosNomeMarca")
                .buildAndExpand(listaOrdenadaPersonaliza3)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converteList(listaOrdenadaPersonaliza3));
    }

    /***
     *
     * @param frete
     * @param categoria
     * @param uriBuilder
     * @return retorna lista filtrada por frete e categoria
     * @throws IOException
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosFreteCategoria")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoDoisParametros4(
            @RequestParam(value = "frete", required = true) boolean frete,
            @RequestParam(value = "categoria", required = true) String categoria,
            UriComponentsBuilder uriBuilder) throws ErrorProcesamentoException {
        List<Produto> listaOrdenadaPersonaliza4 = produtoService.listaProdutoFiltorPersonalizado4(frete, categoria);
        URI uri = uriBuilder
                .path("/listarProdutosFreteCategoria")
                .buildAndExpand(listaOrdenadaPersonaliza4)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converteList(listaOrdenadaPersonaliza4));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosOrdenadosCategoria")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoDoisParametros5(
            @RequestParam(value = "ordenacao") int ordenacao,
            @RequestParam(value = "categoria") String categoria,
            UriComponentsBuilder uriBuilder) throws ErrorProcesamentoException {
        List<Produto> listaOrdenadaPersonaliza5 = produtoService.ordenaCategoria(ordenacao, categoria);
        URI uri = uriBuilder
                .path("/listarProdutosOrdenadosCategoria")
                .buildAndExpand(listaOrdenadaPersonaliza5)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converteList(listaOrdenadaPersonaliza5));
    }

    /*@ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosOrdenadosFrete")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoDoisParametros6(
            @RequestParam(value = "ordenacao") int ordenacao,
            @RequestParam(value = "frete") boolean frete,
            UriComponentsBuilder uriBuilder) {
        List<Produto> listaOrdenadaPersonaliza6 = produtoService.listaProdutoFiltorPersonalizadoOrdenacao2(ordenacao, frete);
        URI uri = uriBuilder
                .path("/listarProdutosOrdenadosFrete")
                .buildAndExpand(listaOrdenadaPersonaliza6)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converteList(listaOrdenadaPersonaliza6));
    }*/

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosOrdenadosMarca")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoDoisParametros6(
            @RequestParam(value = "ordenacao") String ordenacao,
            @RequestParam(value = "marca") String marca,
            UriComponentsBuilder uriBuilder) {
        List<Produto> listaOrdenadaPersonaliza7 = produtoService.listaProdutoFiltorPersonalizadoOrdenacao3(ordenacao, marca);
        URI uri = uriBuilder
                .path("/listarProdutosOrdenadosMarca")
                .buildAndExpand(listaOrdenadaPersonaliza7)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converteList(listaOrdenadaPersonaliza7));
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosOrdenadosQtdEstrelas")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoDoisParametros7(
            @RequestParam(value = "ordenacao") String ordenacao,
            @RequestParam(value = "qtdestrelas") int qtdestrelas,
            UriComponentsBuilder uriBuilder) {
        List<Produto> listaOrdenadaPersonaliza8 = produtoService.listaProdutoFiltorPersonalizadoOrdenacao4(ordenacao, qtdestrelas);
        URI uri = uriBuilder
                .path("/listarProdutosOrdenadosQtdEstrelas")
                .buildAndExpand(listaOrdenadaPersonaliza8)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converteList(listaOrdenadaPersonaliza8));

    }
}
