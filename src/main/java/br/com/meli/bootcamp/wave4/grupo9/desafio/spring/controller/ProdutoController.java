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
 *  * <b>Lista todos os produtos</b><br>
 *  * <b>Lista por categoria</b><br>
 *  * <b>lista por nome</b><br>
 *  * <b>Lista ordenando</b><br>
 *  * <b>lista ppersonalizada / com dois parametros</b>
 *  *
 *  * @author Leonardo Assuncao
 *  * @author Fernando Netto
 */
@RestController
@RequestMapping("/loja")
public class ProdutoController {

    /*** Instancia de produto: <b>ProdutoService</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    private ProdutoService produtoService;

    /***
     *
     * @param uriBuilder uri
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
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /***
     *
     * @param categoria categoria
     * @param uriBuilder uri
     * @return Lista de produtos filtrados pela categoria
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosCategoria")
    public ResponseEntity<List<ProdutoDTO>> obterCategoria(
            @RequestParam(value = "categoria") String categoria,
            UriComponentsBuilder uriBuilder) {
        try {
            Long idCategoria = Long.parseLong(categoria);
            List<Produto> listaCategoria = produtoService.listaProdutoCategoria(idCategoria);
            URI uri = uriBuilder
                    .path("/listarProdutosCategoria")
                    .buildAndExpand(listaCategoria)
                    .toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaCategoria));
        } catch (NotFoundExceptionProduct e) {
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /***
     *
     * @param ordenacao ordenacao
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
            UriComponentsBuilder uriBuilder) {
        try {
            List<Produto> listaOrdenada = produtoService.listaProdutoOrdenado(ordenacao);
            URI uri = uriBuilder
                    .path("/listarProdutosOrdenado")
                    .buildAndExpand(listaOrdenada)
                    .toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaOrdenada));
        } catch (NotFoundExceptionProduct e) {
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /***
     * @param nome nome
     * @param categoria categoria
     * @param uriBuilder uri
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
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /***
     *
     * @param nome nome
     * @param frete frete
     * @param uriBuilder uri
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
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /***
     *
     * @param nome nome
     * @param marca marca
     * @param uriBuilder uri
     * @return filtra por nome e marca
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosNomeMarca")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoNomeMarca(
            @RequestParam(value = "nome", required = true) String nome,
            @RequestParam(value = "marca", required = true) String marca,
            UriComponentsBuilder uriBuilder) {
        try {
            List<Produto> listaProdutoMarca = produtoService.listaProdutoFiltroNomeMarca(nome, marca);
            URI uri = uriBuilder
                    .path("/listarProdutosNomeMarca")
                    .buildAndExpand(listaProdutoMarca)
                    .toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaProdutoMarca));
        } catch (ErrorProcesamentoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /***
     *
     * @param frete frete
     * @param categoria categoria
     * @param uriBuilder uri
     * @return retorna lista filtrada por frete e categoria
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosFreteCategoria")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoFreteCategoria(
            @RequestParam(value = "frete", required = true) boolean frete,
            @RequestParam(value = "categoria", required = true) String categoria,
            UriComponentsBuilder uriBuilder)  {
        try {
            List<Produto> listaCategoriaFrete= produtoService.listaProdutoFiltroFreteCategoria(frete, categoria);
            URI uri = uriBuilder
                    .path("/listarProdutosFreteCategoria")
                    .buildAndExpand(listaCategoriaFrete)
                    .toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaCategoriaFrete));
        } catch (ErrorProcesamentoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /***
     *
     * @param ordenacao ordenacao
     * @param categoria categoria
     * @param uriBuilder uri
     * @return Lista de produto ordenado por categoria
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosOrdenadosCategoria")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoOrdenadoCategoria(
            @RequestParam(value = "ordenacao") int ordenacao,
            @RequestParam(value = "categoria") String categoria,
            UriComponentsBuilder uriBuilder)  {
        try {
            List<Produto> listaOrdenaCategoria = produtoService.ordenaCategoria(ordenacao, categoria);
            URI uri = uriBuilder
                    .path("/listarProdutosOrdenadosCategoria")
                    .buildAndExpand(listaOrdenaCategoria)
                    .toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaOrdenaCategoria));
        } catch (ErrorProcesamentoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /***
     * @param ordenacao ordenacao
     * @param marca marca
     * @param uriBuilder uri
     * @return lista de produtos ordenados por marca
     */
    @GetMapping(value = "/listarProdutosOrdenadosMarca")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoOrdenadoMarca(
            @RequestParam(value = "ordenacao") int ordenacao,
            @RequestParam(value = "marca") String marca,
            UriComponentsBuilder uriBuilder)  {
        try {
            List<Produto> listaOrdenaMarca = produtoService.ordenaMarca(ordenacao, marca);
            URI uri = uriBuilder
                    .path("/listarProdutosOrdenadosMarca")
                    .buildAndExpand(listaOrdenaMarca)
                    .toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaOrdenaMarca));
        } catch (ErrorProcesamentoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /***
     *
     * @param ordenacao ordenacao
     * @param qtdestrelas quantidade de estrtelas
     * @param uriBuilder uri
     * @return lista de produtos ordenados por quantidade de estrelas
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosOrdenadosQtdEstrelas")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoOrdenadoQtdEstrelas(
            @RequestParam(value = "ordenacao") int ordenacao,
            @RequestParam(value = "qtdestrelas") int qtdestrelas,
            UriComponentsBuilder uriBuilder) {
        try {
            List<Produto> listaOrdenadaQtdEstrela = produtoService.ordenaEstrelas(ordenacao, qtdestrelas);
            URI uri = uriBuilder
                    .path("/listarProdutosOrdenadosQtdEstrelas")
                    .buildAndExpand(listaOrdenadaQtdEstrela)
                    .toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaOrdenadaQtdEstrela));
        } catch (ErrorProcesamentoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /***
     * @param estrelas estrtelas
     * @param marca marca
     * @param uriBuilder uri
     * @return Lista de marca ordenada por qtd de estrelas
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listarProdutosOrdenadosMarcaQtdEstrelas")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoMarcaQtdEstrelas(
            @RequestParam(value = "estrelas") int estrelas,
            @RequestParam(value = "marca") String marca,
            UriComponentsBuilder uriBuilder) {
        try {
            List<Produto> listaMarcaQtdestrelas = produtoService.listaProdutoFiltroMarcaEstrela(estrelas, marca);
            URI uri = uriBuilder
                    .path("/listarProdutosMarcaQtdEstrelas")
                    .buildAndExpand(listaMarcaQtdestrelas)
                    .toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaMarcaQtdestrelas));
        } catch (ErrorProcesamentoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /***
     * @param form  Uma lista de produtos que deseja cadastrar
     * @param uriBuilder uri
     * @return Lista de produtos recém-cadastrados.
     */
    @PostMapping("/produto/cadastrarlista")
    public ResponseEntity<List<ProdutoDTO>> cadastrar(@RequestBody List<ProdutoDTO> form,
                                                      UriComponentsBuilder uriBuilder) {
        try {
            List<Produto> listaProduto = ProdutoDTO.converteDTO(form);
            listaProduto = produtoService.salvaLista(listaProduto);

            URI uri = uriBuilder.path("/produto/").buildAndExpand("").toUri();
            return ResponseEntity.created(uri).body(ProdutoDTO.converte(listaProduto));
        } catch (ErrorProcesamentoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /***
     * @param id id
     * @return Produdo um produto requisitado pelo ID.
     */
    @GetMapping("/produto/{id}")
    public ResponseEntity<ProdutoDTO> obter(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ProdutoDTO.converte(produtoService.obter(id)));
        } catch (NotFoundExceptionProduct e) {
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /***
     *
     * @param form Produto aser cadastrado
     * @param uriBuilder uri
     * @return Produto cadastrado
     */
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
        } catch (ErrorProcesamentoException | RepositoryException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }
}