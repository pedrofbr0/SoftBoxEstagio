Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido a vista com empenho em LOJA/NORMAL
De modo que o pedido seja criado ao finalizar o processo

Scenario: Elaborar um pedido a vista com LOJA/NORMAL
Meta:
@tag component:Regressão - RELOH
@context PEDIDOS
@base RELOH
@grupoInstancias Pedidos
@issue rgs-10
@rgs-10

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 69930 e senha 1
And Eu pesquiso o cnpj 51727323000112
And Eu clico no botao 'Elaborar Pedido'

And Eu seleciono o tipo de venda 2 - PESSOA JURIDICA

And Eu clico no botao adicionar produto
And Eu pesquiso e seleciono o produto 139550
And Eu clico no icone detalhes do produto 139550
And Eu seleciono o local de empenho LOJA
And Eu seleciono a opcao de entrega NORMAL
And Eu informo a flag entrega como SIM
And Eu informo o numero de serie 0001
And Eu clico no botao Reservar
And Eu clico no botao Ok do modal

And Eu clico na aba Garantia
And Eu valido se a garantia 143191 foi adicionada para o produto 139550

And Eu clico na aba Serviços
And Eu valido se o servico 104141 ja foi adicionado
And Eu altero o valor unitario do servico 104141 para 50,00

And Eu clico na aba Planilha
And Eu clico no botao Adicionar Forma Pagamento
And Eu seleciono o 1 º tipo de pagamento F
And Eu seleciono a 1 ª forma de pagamento Duplicata AON

Then Eu clico no botao Salvar
And Eu clico no botao Ok do modal
And Eu clico no botao Fechamento Total
And Eu valido o status do pedido 30 - Ag. Receb. ou em Analise p/Troca Minha Casa Melhor

Scenario: Efetua a reabertura do pedido feito
Meta:
@tag component:Regressão - RELOH
@context PEDIDOS
@base RELOH
@grupoInstancias Pedidos
@issue rgs-10
@rgs-10

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 69930 e senha 1
And Eu acesso o menu Consultar > Pedido
And Eu consulto o pedido
And Eu clico no botao Reabertura

And Eu clico no botao adicionar produto
And Eu pesquiso e seleciono o produto 187574
And Eu clico no icone detalhes do produto 187574
And Eu seleciono o local de empenho DEPOSITO
And Eu seleciono a opcao de entrega AGENDADA CLIENTE
And Eu informo a flag entrega como SIM
And Eu informo uma data e um turno disponivel
And Eu clico no botao Reservar
And Eu clico no botao Ok do modal

And Eu clico no icone detalhes do produto 139550
And Eu clico no botao Cancelar Reserva
And Eu clico no botao Ok do modal
And Eu clico no icone detalhes do produto 139550
And Eu seleciono o local de empenho LOJA
And Eu seleciono a opcao de entrega RETIRADA LOJA COM RETENCAO
And Eu informo a flag entrega como SIM
And Eu informo uma data de retencao disponivel
And Eu clico no botao Reservar
And Eu clico no botao Ok do modal

And Eu clico na aba Garantia
And Eu removo a garantia 140358 do produto 187574
And Eu valido se a garantia 149535 foi adicionada para o produto 187574

And Eu clico na aba Serviços
And Eu valido se o servico 104141 ja foi adicionado

And Eu clico na aba Planilha
And Eu removo todas as planilhas
And Eu clico no botao Adicionar Forma Pagamento
And Eu seleciono o 1 º tipo de pagamento F
And Eu seleciono a 1 ª forma de pagamento Duplicata AON

Then Eu clico no botao Salvar
And Eu clico no botao Ok do modal
And Eu clico no botao Fechamento Total
And Eu valido o status do pedido 30 - Ag. Receb. ou em Analise p/Troca Minha Casa Melhor
!-- And Eu salvo o pedido do teste @rgs-10 no relatorio
