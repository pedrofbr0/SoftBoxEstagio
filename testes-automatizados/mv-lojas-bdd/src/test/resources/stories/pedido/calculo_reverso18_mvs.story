Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido para validar o calculo reverso
De modo que o pedido seja criado/recebido ao finalizar o processo

Scenario: Elaborar um pedido carne com empenho em loja/normal
Meta:
@tag component:Processos - MVS
@context PROCESSOS_VALIDACAO
@base MVSH
@grupoInstancias Pedidos
@issue rgs-92
@rgs-92

Given Eu acesso a instancia padraoProducao
When Eu faco login na loja 6134 com usuario 673 e senha 1111
And Eu pesquiso o cpf 00000000191
And Eu clico no botao 'Elaborar Pedido'

And Eu seleciono o tipo de venda 53 - CARTAO DE CREDITO

And Eu seleciono o plano 10 X

And Eu clico na aba Produto
And Eu clico no botao adicionar produto
!-- And Eu pesquiso e seleciono o produto 293117

And Eu pesquiso o produto 308395
And Eu deixo o produto sem promocao
And Eu clico no botao Selecionar Produto

And Eu clico no icone detalhes do produto 308395
And Eu seleciono o local de empenho LOJA
And Eu seleciono a opcao de entrega NORMAL
And Eu informo o numero de serie 0001
And Eu clico no botao Reservar
And Eu clico no botao Ok do modal

And Eu clico na aba Garantia
And Eu removo a garantia 144736 do produto 308395
And Eu informo o usuario 534 e senha 1 para validar exclusao
And Eu removo a garantia 215919 do produto 308395
And Eu removo a garantia 306618 do produto 308395

And Eu clico na aba Serviços
And Eu removo o servico 303096
And Eu informo o usuario 534 e senha 1 para validar exclusao
And Eu removo o servico 302075
And Eu informo o usuario 534 e senha 1 para validar exclusao
And Eu removo o servico 163594

And Eu clico na aba Planilha
And Eu clico no botao Adicionar Forma Pagamento
And Eu seleciono o 1 º tipo de pagamento F
And Eu seleciono a 1 ª forma de pagamento Cartao Mastercard

Then Eu clico no botao Salvar
And Eu clico no botao Ok do modal
And Eu clico no botao Fechamento Total
And Eu valido o status do pedido 30 - Ag. Receb. ou em Analise p/Troca Minha Casa Melhor


Scenario: Cancelar pedido
Meta:
@tag component:Processos Ambiente Validacao - MVS
@context PROCESSOS_VALIDACAO
@base MVSH
@grupoInstancias Pedidos
@issue rgs-92
@rgs-92

Given Eu acesso a instancia padraoProducao
When Eu faco login na loja 6134 com usuario 673 e senha 1111
And Eu acesso o menu Consultar > Pedido
And Eu consulto o pedido
And Eu clico no botao Cancelar pedido
!-- Then Eu salvo o pedido do teste @rgs-92 no relatorio