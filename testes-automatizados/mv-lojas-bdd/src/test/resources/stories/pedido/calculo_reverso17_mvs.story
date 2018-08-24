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
@issue rgs-90
@rgs-90

Given Eu acesso a instancia padraoProducao
When Eu faco login na loja 6134 com usuario 673 e senha 1111
And Eu pesquiso o cpf 00000000191
!-- And Eu clico no botao 'Elaborar Pedido'

!-- Realizando a Consulta Credito
And Eu clico no botao 'Consultar Crédito'
And Eu realizo a consulta pre simplificada

And Eu seleciono o tipo de venda 66 - CARNE LOSANGO SCRED

And Eu seleciono o plano 6 X

And Eu seleciono a data do primeiro vencimento

And Eu clico na aba Serviços
And Eu removo o servico 163594

And Eu clico na aba Produto
And Eu clico no botao adicionar produto
!-- And Eu pesquiso e seleciono o produto 293117

And Eu pesquiso o produto 223890
And Eu deixo o produto sem promocao
And Eu clico no botao Selecionar Produto

And Eu clico no icone detalhes do produto 223890
And Eu seleciono o local de empenho LOJA
And Eu seleciono a opcao de entrega NORMAL
And Eu clico no botao Reservar
And Eu clico no botao Ok do modal

And Eu clico na aba Planilha
And Eu clico no botao Adicionar Forma Pagamento
And Eu seleciono o 1 º tipo de pagamento F
And Eu seleciono a 1 ª forma de pagamento Carne Losango

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


Scenario: Cancelar pedido
Meta:
@tag component:Processos Ambiente Validacao - MVS
@context PROCESSOS_VALIDACAO
@base MVSH
@grupoInstancias Pedidos
@issue rgs-90
@rgs-90

Given Eu acesso a instancia padraoProducao
When Eu faco login na loja 6134 com usuario 673 e senha 1111
And Eu acesso o menu Consultar > Pedido
And Eu consulto o pedido
And Eu clico no botao Cancelar pedido
!-- Then Eu salvo o pedido do teste @rgs-90 no relatorio