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
    @GetMapping(value = "/listarProdutos")
    public ResponseEntity<List<ProdutoDTO>> obterlista(
            UriComponentsBuilder uriBuilder) {
        List<Produto> lista =  produtoService.listaProduto();
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
            @RequestParam(value = "categoria", required = true) String categoria,
            UriComponentsBuilder uriBuilder) {
        List<Produto> listaCategoria = produtoService.listaProdutoCategoria(categoria);
        URI uri = uriBuilder
                .path("/listarProdutosCategoria")
                .buildAndExpand(listaCategoria)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converteList(listaCategoria));
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
    @GetMapping(value = "/listarProdutosOrdenado")
    public ResponseEntity<List<ProdutoDTO>> obterListaOrdenada(
            @RequestParam(value = "ordenacao") String ordenacao,
            @RequestParam(value = "nameOrPreco") String nameOrPreco,
            @RequestParam(value = "marca") String marca,
            @RequestParam(value = "categoria") String categoria,
            UriComponentsBuilder uriBuilder) throws IOException{
        if(nameOrPreco.isEmpty()){
            nameOrPreco = null;
        }
        List<Produto> listaOrdenada = produtoService.listaProdutoOrdenado(ordenacao, nameOrPreco);
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
     * @throws IOException
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosNomeCategoria")
    public ResponseEntity<List<ProdutoDTO>>  obterProdutoDoisParametros(
            @RequestParam(value = "nome", required = true) String nome,
            @RequestParam(value = "categoria", required = true) String categoia,
            UriComponentsBuilder uriBuilder) throws IOException{
        List <Produto> listaOrdenadaPersonaliza = produtoService.listaProdutoFiltorPersonalizado(nome,categoia);
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
     * @throws IOException
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosNomeFrete")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoDoisParametros2(
            @RequestParam(value = "nome", required = true) String nome,
            @RequestParam(value = "frete", required = true) boolean frete,
            UriComponentsBuilder uriBuilder) throws IOException{
        List <Produto> listaOrdenadaPersonaliza2 = produtoService.listaProdutoFiltorPersonalizado2(nome,frete);
        URI uri = uriBuilder
                .path("/listarProdutosNomeFrete")
                .buildAndExpand(listaOrdenadaPersonaliza2)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converteList(listaOrdenadaPersonaliza2));
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
    @GetMapping(value = "/listarProdutosNomeMarca")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoDoisParametros3(
            @RequestParam(value = "nome", required = true) String nome,
            @RequestParam(value = "marca", required = true) String marca,
            UriComponentsBuilder uriBuilder) throws IOException{
        List <Produto> listaOrdenadaPersonaliza3 = produtoService.listaProdutoFiltorPersonalizado3(nome,marca);
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
            UriComponentsBuilder uriBuilder) throws IOException{
        List <Produto> listaOrdenadaPersonaliza4 = produtoService.listaProdutoFiltorPersonalizado4(frete,categoria);
        URI uri = uriBuilder
                .path("/listarProdutosFreteCategoria")
                .buildAndExpand(listaOrdenadaPersonaliza4)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converteList(listaOrdenadaPersonaliza4));
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
    @GetMapping(value = "/listarProdutosMarcaEstrela")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoDoisParametros5(
            @RequestParam(value = "marca", required = true) String marca,
            @RequestParam(value = "estrela", required = true) int estrela,
            UriComponentsBuilder uriBuilder) throws IOException{
        List <Produto> listaOrdenadaPersonaliza5= produtoService.listaProdutoFiltorPersonalizado5(marca,estrela);
        URI uri = uriBuilder
                .path("/listarProdutosMarcaEstrela")
                .buildAndExpand(listaOrdenadaPersonaliza5)
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converteList(listaOrdenadaPersonaliza5));
    }

}
