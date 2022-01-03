# Bootcamp-Meli-W4-G9-Desafio-Spring
Desafio Spring - Grupo 9 - Wave 4 - Bootcamp Meli
## ATIVIDADE
Uma plataforma de vendas de produtos online deseja melhorar as opções de pesquisa e filtragem dos seus produtos. Para isso, decidiu implementar um motor de busca que, a partir das opções que o usuário determina, devolve o(s) produto(s) que lhes corresponde. Obs.: Os produtos devem ser cadastrados a partir de um payload e armazenados num arquivo Json.

Para fazer isso, será necessário desenvolver uma API que forneça:
1. Cadastrar uma lista de produtos.
2. Uma lista de todos os produtos disponíveis.
3. Uma lista de produtos filtrados por categoria.
4. Uma lista que permite a combinação de qualquer um dos filtros.
```
Exemplo: categoria + frete grátis.
```

Por outro lado, visto que se pretende uma boa experiência do usuário no que diz respeito à forma de apresentação dos produtos, será necessário que os resultados fornecidos pela API possam ser ordenados por qualquer um dos seguintes critérios:
+ Alfabético (crescente e decrescente)
+ Preço mais alto
+ Menor preço

Ao mesmo tempo, é necessária uma API que forneça:

5. Possibilidade de envio de pedido de compra. A partir disso, o preço total da requisição feita pode ser recebido como resposta.
+ Considere, para cada uma dessas solicitações, os possíveis "status-code" que podem ser retornados.
```
+ Se um produto que não existe for solicitado, retorne o código de status correspondente.
+ Se houver um problema com o servidor e a conexão não puder ser feita, 
  o código de status correspondente deve ser retornado.
```

## EXTRA
A plataforma afirmou que no futuro gostaria de conseguir realizar o desenvolvimento dos seguintes requisitos como uma melhoria:

6. Para cada solicitação de compra é necessário realizar o controle de estoque disponível.
```
Se forem solicitadas 4 unidades de um produto e houver apenas duas, 
coloque as restrições e avisos correspondentes.
```
7. Permite a utilização de um “carrinho de compras” onde para cada pedido de compra existe um valor total acumulado e devolvido ao usuário.
```
Se um produto de R$ 900 foi enviado em um pedido de compra
e outro produto de R$ 300 foi enviado em outro, 
devo receber a soma dos dois (R$ 1200) como resposta.
```
Ao mesmo tempo, sugere o desenvolvimento de uma nova API que permita o seguinte:

8. Cadastre novos clientes. Para isso, devem ser realizados os controles necessários, por exemplo: cliente existente, cliente com dados incompletos, etc.
9. Obter uma lista completa de todos os clientes.
10. Obter uma lista de todos os clientes filtrados por Estado.

Além disso, a plataforma afirmou que concorda em receber sugestões de melhorias dos desenvolvedores da solução, portanto os convidamos a incluir todas as melhorias que considerem que possam impulsionar o projeto desenvolvido.


***
## Utilização
#### -> GitHub-Pages contendo o JavaDoc [https://netto-meli.github.io/DesafioSringWave4/](https://netto-meli.github.io/DesafioSringWave4/)
#### -> [Link para importar Collection](https://www.getpostman.com/collections/70c04463c04e0599192e) para o Postman (copiar e colar)
#### —> Tabela de [produtos iniciais](https://docs.google.com/spreadsheets/d/1VbpRtZXw6DiYoA7VETG9ezf39ghlsCq4EN0drRTxuS4/edit?usp=sharing)
#### —> [Definições](https://drive.google.com/file/d/1XXDABy-lEhF-MGQkw7Ty91WLscVJ6aQ_/view?usp=sharing) a serem seguidas para completar o desafio.
```sh
POST/GET base -> http://localhost:8080/loja
```

## Repositórios  e Responsabilidades da Equipe 9
<b>Felipe Pereira Bontempo</b>
<br>{Cadastro do Estoque e persistência JSON}
<br>[Repositório Git](https://github.com/fpbontempo)

<b>Fernando José Dias Netto</b>
<br>{Processo de Pedidos e finalização de Compra}
<br>[Repositório Git](https://github.com/netto-meli/DesafioSringWave4)

<b>Leonardo Teixeira Assunção</b>
<br>{Listagem e filtros de Produtos}
<br>[Repositório Git](https://github.com/LeoDevMeli)

<b>Marcos Vinicius Rodrigues De Sá</b>
<br>{Listagen e filtros de Pedidos}
<br>[Repositório Git](https://github.com/marcossa01)

<b>Rafael Menezes Da Silva</b>
<br>{Criação e Manipulação de dados de Cliente}
<br>[Repositório Git](https://github.com/rafaelmenez)

## Meli Bootcamp IT - Wave 4
[Playground](https://br-playground.digitalhouse.com/login) - Digital House
<br>[Workplace](https://meli.workplace.com/) - Mercado Livre