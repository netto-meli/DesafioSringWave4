package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/*** Entidade para persistência de Pedido
 *
 * @author Fernando Netto
 * @author Marcos
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    /***
     * ID do Pedido no tipo Long
     */
    private Long id;
    /***
     * ID do Cliente no tipo Long
     */
    private Long idCliente;
    /***
     * Lista de ItemCarrinho (produto no pedido) no formato BigDecimal
     */
    private List<ItemCarrinho> listaItensCarrinho;
    /***
     * Valor do frete do Pedido no formato BigDecimal
     */
    private BigDecimal valorFrete;
    /***
     * Valor total do Pedido no formato BigDecimal
     */
    private BigDecimal valorTotal;

    /*** Método para buscar dentro do pedido, o produto selecionado
     *
     * @param idProduto ID do Produto para o método buscar os dados de ItemCarinho
     * @return ItemCarrinho contendo o produto buscado e quantidade no carrinho
     */
    public ItemCarrinho getItemCarrinho(Long idProduto) {
        return listaItensCarrinho.stream()
                .filter( ic -> ic.getProduto().getId().equals(idProduto))
                .findAny()
                .orElse(null);
    }

    /*** Método para atualizar o carrinho, conforme foram adicionados ou removidos itens de um produto
     *
     * @param itemCarrinho Produto para atualizar no carrinho
     * @param endereco Endereço do cliente, para calcular o frete atual
     */
    public void atualizaCarrinho(ItemCarrinho itemCarrinho, String endereco) {
        listaItensCarrinho.removeIf(itemCarrinho::equals);
        if (itemCarrinho.getQuantidade() > 0 ) {
            listaItensCarrinho.add(itemCarrinho);
            if (!itemCarrinho.getProduto().isFreteGratis())
                this.calculaValorTotalPedido(endereco);
        }
    }

    /*** Método para calcular o valor total de itens de um produto no carrinho
     *
     * @param endereco Endereço do cliente, para calcular o frete atual
     */
    public void calculaValorTotalPedido(String endereco){
        BigDecimal valorPedido = BigDecimal.ZERO;
        boolean isFreteGratis = true;
        for (ItemCarrinho item : listaItensCarrinho) {
            BigDecimal valorTotalUmProduto = item.calculaValorTotalProduto();
            valorPedido = valorTotalUmProduto.add(valorPedido);
            // Se algum dos produtos no pedido não tiver frete gratis,
            // o pedido precisa calcular o valor do frete
            if (!item.getProduto().isFreteGratis()) isFreteGratis = false;
        }
        calculaFrete(isFreteGratis, endereco);
        this.valorTotal = valorPedido.add(this.valorFrete);
    }

    /*** Método para calcular o frete
     *
     * @param isFreteGratis Informação se o pedido somente contém produtos com frete grátis
     * @param endereco Endereço do cliente, para calcular o frete atual
     */
    private void calculaFrete(boolean isFreteGratis, String endereco) {
        this.valorFrete = BigDecimal.ZERO;
        if (!isFreteGratis) {
            this.valorFrete = mockFreteByEnderecoString(endereco);
        }
    }

    /*** Método MOCK para cálculo do frete baseado nos caracteres do endereço
     *
     * @param endereco Endereço do cliente, para calcular o frete atual
     * @return Valor do Frete calculado
     */
    private BigDecimal mockFreteByEnderecoString(String endereco) {
        long tempValorFrete = 0;
        if (endereco == null) endereco = "a";
        // remove todos os espaços em branco
        String mockFrete = endereco.toLowerCase().replaceAll("\\s", "");
        final String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for(int i=0; i < mockFrete.length(); i++){
            tempValorFrete += (alphabet.indexOf(mockFrete.charAt(i))) + 1;
            // Acrescenta em +1; pois se não houver o caractere na lista,
            // o método indexOf retorna -1.
            // Ex: números, caracteres especiais...
        }
        return BigDecimal.valueOf(tempValorFrete);
    }

}
