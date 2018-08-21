Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido a vista
De modo que o pedido seja criado ao finalizar o processo

Scenario: Elaborar um pedido a vista
Meta:
@tag component:Smoke testing - MVSH
@context SMOKE_TESTING
@base MVSH
@grupoInstancias Pedidos
@issue avl-01
@avl-01

Given Eu acesso a instancia padrao
When Eu faco login na loja 1002 com usuario 7380 e senha 1
And Eu pesquiso o cpf 00000000191
And Eu clico no botao 'Elaborar Pedido'

And Eu seleciono o tipo de venda 76 - CREDCASA

And Eu seleciono o plano 8 X

!-- Realizando a Consulta Simplificada
And Eu clico no botao Captura de Limite

And Eu clico na aba Serviços
And Eu removo o servico 302075

And Eu clico na aba Produto
And Eu clico no botao adicionar produto
And Eu pesquiso e seleciono o produto 711
And Eu clico no icone detalhes do produto 711
And Eu seleciono o local de empenho LOJA
And Eu seleciono a opcao de entrega NORMAL
And Eu clico no botao Reservar
And Eu clico no botao Ok do modal

And Eu clico na aba Planilha
And Eu clico no botao Adicionar Forma Pagamento
And Eu seleciono o 1 º tipo de pagamento F
And Eu seleciono a 1 ª forma de pagamento CARNE CRED PROPRIO

Then Eu clico no botao Salvar
And Eu clico no botao Ok do modal
And Eu clico no botao Fechamento Total

And Eu valido o status do pedido 22 - Ag. Lib. Credito Central
And Eu clico no botao Nao realizar analise de credito

!-- Realizando a Comunicacao
When Eu clico no botao Analise de Credito
Then Eu clico no botao Comunicar
And Eu informo observacao Teste Automatizado para comunicacao
And Eu clico no botao Enviar comunicacao
And Eu clico no botao Ok proposta em processamento
And Eu acesso link de aprovacao de propostas
And Eu consulto a comunicacao