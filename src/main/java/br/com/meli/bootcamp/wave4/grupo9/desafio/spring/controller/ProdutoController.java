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
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/listarProdutos/", method = RequestMethod.GET)
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
     * @param nameOrPreco
     * @param marca
     * @param categoria
     * @param uriBuilder
     * 0 - Alfabetica crescente
     * 1 - Alfabetica decrescente
     * 2 - Maior -> menor preco
     * 3 - Menor -> maior preco
     * @return Lista de produtos filtrados pela categoria, nome e marca ordenados
     * @throws IOException
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/listarProdutosOrdenado/", method = RequestMethod.GET)
    public ResponseEntity<ProdutoDTO> obterListaOrdenada(
            @RequestParam(value = "ordenacao", required = true) String ordenacao,
            @RequestParam(value = "nameOrPreco", required = false) String nameOrPreco,
            @RequestParam(value = "marca", required = false) String marca,
            @RequestParam(value = "categoria", required = false) String categoria,
            UriComponentsBuilder uriBuilder) throws IOException{
        Produto listaOrdenada = (Produto) produtoService.listaProdutoOrdenado(ordenacao, nameOrPreco, marca, categoria);
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
    @RequestMapping(value = "/listarProdutosNomeCategoria/", method = RequestMethod.GET)
    public ResponseEntity<ProdutoDTO> obterProdutoDoisParametros(
            @RequestParam(value = "nome", required = true) String nome,
            @RequestParam(value = "categoria", required = true) String categoia,
            UriComponentsBuilder uriBuilder) throws IOException{
        Produto listaOrdenadaPersonaliza = (Produto) produtoService.listaProdutoFiltorPersonalizado(nome,categoia);
        URI uri = uriBuilder
                .path("/listarProdutosNomeCategoria/")
                .buildAndExpand(listaOrdenadaPersonaliza)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaOrdenadaPersonaliza));
    }

    /***
     *
     * @param nome
     * @param frete
     * @param uriBuilder
     * @return filtra por nome e frete
     * @throws IOException
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/listarProdutosNomeFrete/", method = RequestMethod.GET)
    public ResponseEntity<ProdutoDTO> obterProdutoDoisParametros2(
            @RequestParam(value = "nome", required = true) String nome,
            @RequestParam(value = "frete", required = true) boolean frete,
            UriComponentsBuilder uriBuilder) throws IOException{
        Produto listaOrdenadaPersonaliza2 = (Produto) produtoService.listaProdutoFiltorPersonalizado2(nome,frete);
        URI uri = uriBuilder
                .path("/listarProdutosNomeFrete/")
                .buildAndExpand(listaOrdenadaPersonaliza2)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaOrdenadaPersonaliza2));
    }

    /***
     *
     * @param nome
     * @param marca
     * @param uriBuilder
     * @return filtra por nome e marca
     * @throws IOException
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/listarProdutosNomeMarca/", method = RequestMethod.GET)
    public ResponseEntity<ProdutoDTO> obterProdutoDoisParametros3(
            @RequestParam(value = "nome", required = true) String nome,
            @RequestParam(value = "marca", required = true) String marca,
            UriComponentsBuilder uriBuilder) throws IOException{
        Produto listaOrdenadaPersonaliza3 = (Produto) produtoService.listaProdutoFiltorPersonalizado3(nome,marca);
        URI uri = uriBuilder
                .path("/listarProdutosNomeMarca/")
                .buildAndExpand(listaOrdenadaPersonaliza3)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaOrdenadaPersonaliza3));
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
    @RequestMapping(value = "/listarProdutosFreteCategoria/", method = RequestMethod.GET)
    public ResponseEntity<ProdutoDTO> obterProdutoDoisParametros4(
            @RequestParam(value = "frete", required = true) boolean frete,
            @RequestParam(value = "categoria", required = true) String categoria,
            UriComponentsBuilder uriBuilder) throws IOException{
        Produto listaOrdenadaPersonaliza4 = (Produto) produtoService.listaProdutoFiltorPersonalizado4(frete,categoria);
        URI uri = uriBuilder
                .path("/listarProdutosFreteCategoria/")
                .buildAndExpand(listaOrdenadaPersonaliza4)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaOrdenadaPersonaliza4));
    }

    /***
     *
     * @param marca
     * @param estrela
     * @param uriBuilder
     * @return lista filtrada por marca e estrela
     * @throws IOException
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/listarProdutosMarcaEstrela/", method = RequestMethod.GET)
    public ResponseEntity<ProdutoDTO> obterProdutoDoisParametros5(
            @RequestParam(value = "marca", required = true) String marca,
            @RequestParam(value = "estrela", required = true) int estrela,
            UriComponentsBuilder uriBuilder) throws IOException{
        Produto listaOrdenadaPersonaliza5= (Produto) produtoService.listaProdutoFiltorPersonalizado5(marca,estrela);
        URI uri = uriBuilder
                .path("/listarProdutosMarcaEstrela/")
                .buildAndExpand(listaOrdenadaPersonaliza5)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaOrdenadaPersonaliza5));
    }

}
