Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido para validar o calculo reverso
De modo que o pedido seja criado/recebido ao finalizar o processo

Scenario: Elaborar um pedido carne com empenho em loja/normal
Meta:
@tag component:Processos - RELOH
@context PEDIDOS
@base RELOH
@grupoInstancias Pedidos
@issue rgs-64
@rgs-64

Given Eu acesso a instancia padrao
When Eu faco login na loja 78 com usuario 7380 e senha 1
And Eu pesquiso o cpf 00000000191
And Eu clico no botao 'Elaborar Pedido'

!-- Realizando a Consulta Credito
!-- And Eu clico no botao 'Consultar Crédito'
!-- And Eu realizo a consulta pre simplificada

And Eu seleciono o tipo de venda 66 - CARNE LOSANGO SCRED

And Eu seleciono o plano 6 X

And Eu clico na aba Serviços
And Eu removo o servico 305855
And Eu removo o servico 163594
And Eu removo o servico 302075

And Eu clico na aba Produto
And Eu clico no botao adicionar produto
And Eu pesquiso e seleciono o produto 293117
And Eu clico no icone detalhes do produto 293117
And Eu seleciono o local de empenho LOJA
And Eu seleciono a opcao de entrega NORMAL
And Eu clico no botao Reservar
And Eu clico no botao Ok do modal

And Eu clico na aba Produto
And Eu clico no botao adicionar produto
And Eu pesquiso e seleciono o produto 139550
And Eu clico no icone detalhes do produto 139550
And Eu seleciono o local de empenho LOJA
And Eu seleciono a opcao de entrega NORMAL
And Eu informo o numero de serie 0001
And Eu clico no botao Reservar
And Eu clico no botao Ok do modal

!-- And Eu salvo na sessao o valor unitario do produto 711

And Eu informo um valor de entrada de 133,37

And Eu clico na aba Planilha
And Eu clico no botao Adicionar Forma Pagamento
And Eu seleciono o 1 º tipo de pagamento E
And Eu seleciono a 1 ª forma de pagamento Dinheiro

And Eu clico no botao Adicionar Forma Pagamento
And Eu seleciono o 2 º tipo de pagamento F
And Eu seleciono a 2 ª forma de pagamento Carne Losango

Then Eu clico no botao Salvar
And Eu clico no botao Ok do modal
!-- And Eu valido os valores do calculo reverso na planilha

!-- Apos o fechamento total o valor do produto eh alterado, por isso a validacao do calculo reverso eh feita antes
And Eu clico no botao Fechamento Total

And Eu valido o status do pedido 22 - Ag. Lib. Credito Central
And Eu clico no botao Nao realizar analise de credito

!-- And Eu valido o valor unitario do produto 711 calculo reverso
!-- Given Eu valido os valores do calculo reverso na planilha

!-- Realizando a Comunicacao
When Eu clico no botao Analise de Credito
Then Eu clico no botao Comunicar
And Eu informo observacao Teste Automatizado para comunicacao
And Eu clico no botao Enviar comunicacao
And Eu clico no botao Ok proposta em processamento
And Eu acesso link de aprovacao de propostas
And Eu consulto a comunicacao
!-- And Eu salvo o pedido do teste @rgs-64 no relatorio
