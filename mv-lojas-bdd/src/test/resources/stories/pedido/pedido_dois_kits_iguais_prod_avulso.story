Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido a vista com empenho em DEPOSITO/RETIRADA OUTRA LOJA COM RESERVA DEPOSITO, e duas formas de pagamento cartão crédito e boleto
De modo que o pedido seja criado ao finalizar o processo

Scenario: Elaborar um pedido 2 Kits iguais
Meta:
@tag component:Regressão - RELOH
@context PEDIDOS
@base RELOH
@grupoInstancias Pedidos
@issue rgs-78
@rgs-78

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 69930 e senha 1
And Eu pesquiso o cpf 37778670550
And Eu clico no botao 'Elaborar Pedido'

And Eu seleciono o tipo de venda 1 - A VISTA

And Eu clico no botao adicionar produto
And Eu pesquiso o produto 187574
And Eu deixo o produto sem promocao
And Eu clico no botao Selecionar Produto

And Eu clico no icone detalhes do produto 187574
And Eu seleciono o local de empenho OUTRA LOJA
And Eu seleciono a opcao de entrega NORMAL
And Eu informo a flag entrega como NÃO
And Eu clico no botao Reservar
And Eu seleciono a loja disponivel 286

And Eu clico no botao adicionar produto
And Eu pesquiso e seleciono o produto 201196
And Eu clico no icone detalhes de produto nao empenhado 201196
And Eu seleciono o local de empenho DEPOSITO
And Eu seleciono a opcao de entrega AGENDADA
And Eu clico no botao Agendada
And Eu seleciono uma data disponivel
And Eu clico no botao Reservar

And Eu clico no botao adicionar produto
And Eu pesquiso e seleciono o produto 711
And Eu clico no icone detalhes do produto 711
And Eu seleciono o local de empenho OUTRA LOJA
And Eu seleciono a opcao de entrega NORMAL
And Eu clico no botao Reservar
And Eu seleciono a loja disponivel 286

And Eu clico na aba Liberações
And Eu seleciono a liberacao LIBERACAO DE RESERVA OUTRA LOJA com flags A;C;D;G;R;V para o produto 711
And Eu clico no botao Liberar
And Eu clico no botao Ok do modal
And Eu clico no botao Ok do modal
And Eu clico na aba Liberações
And Eu seleciono a liberacao LIBERACAO DE RESERVA OUTRA LOJA com flags A;C;D;G;R;V para o produto 711
And Eu clico no botao Liberar
And Eu informo a observacao OBS LIBERACAO da liberacao
And Eu clico no botao Ok do modal

And Eu clico na aba Liberações
And Eu seleciono a liberacao LIBERACAO DE RESERVA OUTRA LOJA com flags A;C;D;G;R;V para o produto 187574
And Eu clico no botao Liberar
And Eu clico no botao Ok do modal
And Eu clico no botao Ok do modal
And Eu clico na aba Liberações
And Eu seleciono a liberacao LIBERACAO DE RESERVA OUTRA LOJA com flags A;C;D;G;R;V para o produto 187574
And Eu clico no botao Liberar
And Eu informo a observacao OBS LIBERACAO da liberacao
!-- And Eu informo o usuario 291 e senha 1 para validar liberacao
And Eu clico no botao Ok do modal


And Eu clico na aba Planilha
And Eu clico no botao Adicionar Forma Pagamento
And Eu seleciono o 1 º tipo de pagamento V
And Eu seleciono a 1 ª forma de pagamento Dinheiro

Then Eu clico no botao Salvar
And Eu clico no botao Ok do modal
And Eu clico no botao Fechamento Total
And Eu valido o status do pedido 30 - Ag. Receb. ou em Analise p/Troca Minha Casa Melhor
!-- And Eu salvo o pedido do teste @rgs-78 no relatorio
