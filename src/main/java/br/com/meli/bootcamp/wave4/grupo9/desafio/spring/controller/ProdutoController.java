package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.controller;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto.ProdutoDTO;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.NotFoundExceptionProduct;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.RepositoryException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/***
 * Controller dos métodos do carrinho:<br>
 *  <b>Lista todos os produtos</b><br>
 *  <b>Lista por categoria</b><br>
 *  <b>lista por nome</b><br>
 *  <b>Lista ordenando</b><br>
 *  <b>lista personalizada / com dois parâmetros</b>
 *
 * @author Leonardo Assuncao
 * @author Felipe
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
        try {
            List<Produto> lista = produtoService.listaProduto();
            URI uri = uriBuilder
                    .path("/listarProdutos")
                    .buildAndExpand(lista)
                    .toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(lista));
        } catch (RepositoryException e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /***
     *
     * @param categoria
     * @param uriBuilder
     * @return Lista de produtos filtrados pela categoria
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosCategoria")
    public ResponseEntity<List<ProdutoDTO>> obterCategoria(
            @RequestParam(value = "categoria") String categoria,
            UriComponentsBuilder uriBuilder)  {
        try {
            Long idCategoria = Long.parseLong(categoria);
            List<Produto> listaCategoria = produtoService.listaProdutoCategoria(idCategoria);
            URI uri = uriBuilder
                    .path("/listarProdutosCategoria")
                    .buildAndExpand(listaCategoria)
                    .toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaCategoria));
        } catch (NotFoundExceptionProduct e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /***
     *
     * @param ordenacao
     * @param uriBuilder
     * 0 - Alfabetica crescente
     * 1 - Alfabetica decrescente
     * 2 - Maior - menor preco
     * 3 - Menor - maior preco
     * @return Lista de produtos filtrados pela categoria, nome e marca ordenados
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosOrdenado")
    public ResponseEntity<List<ProdutoDTO>> obterListaOrdenada(
            @RequestParam(value = "ordenacao", required = true) int ordenacao,
            UriComponentsBuilder uriBuilder)  {
        try {
            List<Produto> listaOrdenada = produtoService.listaProdutoOrdenado(ordenacao);
            URI uri = uriBuilder
                    .path("/listarProdutosOrdenado")
                    .buildAndExpand(listaOrdenada)
                    .toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaOrdenada));
        } catch (NotFoundExceptionProduct e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /***
     * @param nome
     * @param categoria
     * @param uriBuilder
     * @return lista filtrada pelo nome e categoria
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosNomeCategoria")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoNomeCategoria(
            @RequestParam(value = "nome") String nome,
            @RequestParam(value = "categoria") String categoria,
            UriComponentsBuilder uriBuilder) {
        try {
            List<Produto> listaOrdenadaPersonaliza = produtoService.listaProdutoFiltroNomeCategoria(nome, categoria);
            URI uri = uriBuilder
                    .path("/listarProdutosNomeCategoria")
                    .buildAndExpand(listaOrdenadaPersonaliza)
                    .toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaOrdenadaPersonaliza));
        } catch (NotFoundExceptionProduct e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().body(null);
        }
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
            UriComponentsBuilder uriBuilder) {
        try {
            List<Produto> listaOrdenadaNomeFrete = produtoService.listaProdutoFiltroNomeFrete(nome, frete);
            URI uri = uriBuilder
                    .path("/listarProdutosNomeFrete")
                    .buildAndExpand(listaOrdenadaNomeFrete)
                    .toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaOrdenadaNomeFrete));
        } catch (ErrorProcesamentoException e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().body(null);
        }
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
    public ResponseEntity<List<ProdutoDTO>> obterProdutoNomeMarca(
            @RequestParam(value = "nome", required = true) String nome,
            @RequestParam(value = "marca", required = true) String marca,
            UriComponentsBuilder uriBuilder) {
        try {
            List<Produto> listaOrdenadaPersonaliza3 = produtoService.listaProdutoFiltroNomeMarca(nome, marca);
            URI uri = uriBuilder
                    .path("/listarProdutosNomeMarca")
                    .buildAndExpand(listaOrdenadaPersonaliza3)
                    .toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaOrdenadaPersonaliza3));
        } catch (ErrorProcesamentoException e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /***
     *
     * @param frete
     * @param categoria
     * @param uriBuilder
     * @return retorna lista filtrada por frete e categoria
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosFreteCategoria")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoFreteCategoria(
            @RequestParam(value = "frete", required = true) boolean frete,
            @RequestParam(value = "categoria", required = true) String categoria,
            UriComponentsBuilder uriBuilder)  {
        try {
            List<Produto> listaOrdenadaPersonaliza4 = produtoService.listaProdutoFiltroFreteCategoria(frete, categoria);
            URI uri = uriBuilder
                    .path("/listarProdutosFreteCategoria")
                    .buildAndExpand(listaOrdenadaPersonaliza4)
                    .toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaOrdenadaPersonaliza4));
        } catch (ErrorProcesamentoException e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosOrdenadosCategoria")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoOrdenaCategoria(
            @RequestParam(value = "ordenacao") int ordenacao,
            @RequestParam(value = "categoria") String categoria,
            UriComponentsBuilder uriBuilder)  {
        try {
            List<Produto> listaOrdenadaPersonaliza5 = produtoService.ordenaCategoria(ordenacao, categoria);
            URI uri = uriBuilder
                    .path("/listarProdutosOrdenadosCategoria")
                    .buildAndExpand(listaOrdenadaPersonaliza5)
                    .toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaOrdenadaPersonaliza5));
        } catch (ErrorProcesamentoException e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().body(null);
        }
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
    public ResponseEntity<List<ProdutoDTO>> obterProdutoOrdenaMarca(
            @RequestParam(value = "ordenacao") int ordenacao,
            @RequestParam(value = "marca") String marca,
            UriComponentsBuilder uriBuilder)  {
        try {
            List<Produto> listaOrdenadaPersonaliza7 = produtoService.ordenaMarca(ordenacao, marca);
            URI uri = uriBuilder
                    .path("/listarProdutosOrdenadosMarca")
                    .buildAndExpand(listaOrdenadaPersonaliza7)
                    .toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaOrdenadaPersonaliza7));
        } catch (ErrorProcesamentoException e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosOrdenadosQtdEstrelas")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoOrdenaEstrelas(
            @RequestParam(value = "ordenacao") int ordenacao,
            @RequestParam(value = "qtdestrelas") int qtdestrelas,
            UriComponentsBuilder uriBuilder) {
        try {
            List<Produto> listaOrdenadaPersonaliza8 = produtoService.ordenaEstrelas(ordenacao, qtdestrelas);
            URI uri = uriBuilder
                    .path("/listarProdutosOrdenadosQtdEstrelas")
                    .buildAndExpand(listaOrdenadaPersonaliza8)
                    .toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaOrdenadaPersonaliza8));
        } catch (ErrorProcesamentoException e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().body(null);
        }

    }

    @PostMapping("/produto/cadastrarlista")
    public ResponseEntity<List<ProdutoDTO>> cadastrar(@RequestBody List<ProdutoDTO> form,
                                                      UriComponentsBuilder uriBuilder) {
        try {
            List<Produto> listaProduto = ProdutoDTO.converteDTO(form);
            listaProduto = produtoService.salvaLista(listaProduto);

            URI uri = uriBuilder.path("/produto/").buildAndExpand("").toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaProduto));
        } catch (ErrorProcesamentoException e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<ProdutoDTO> obter(@PathVariable Long id) {
        return ResponseEntity.ok(ProdutoDTO.converte(produtoService.obter(id)));
    }

    @PostMapping("/produto/cadastrar")
    public ResponseEntity<ProdutoDTO> cadastrar(
            @RequestBody ProdutoDTO form,
            UriComponentsBuilder uriBuilder) {
        try {
            Produto produto = ProdutoDTO.converte(form);
            produtoService.salvar(produto);
            URI uri = uriBuilder
                    .path("/produto/{id}")
                    .buildAndExpand(produto.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(produto));
        } catch (ErrorProcesamentoException e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }


}
