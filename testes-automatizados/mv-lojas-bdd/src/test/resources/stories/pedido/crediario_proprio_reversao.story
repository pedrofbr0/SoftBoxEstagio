Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido para validar o calculo reverso
De modo que o pedido seja criado/recebido ao finalizar o processo

Scenario: Elaborar um pedido CREDIARIO PROPRIO com empenho em loja/normal
Meta:
@tag component:Processos - MVS
@context PROCESSOS_VALIDACAO
@base MVSH
@grupoInstancias Pedidos
@issue rgs-91
@rgs-91

Given Eu acesso a instancia padraoProducao
When Eu faco login na loja 1290 com usuario 673 e senha 1111
And Eu pesquiso o cpf 00000000191
And Eu clico no botao 'Elaborar Pedido'

And Eu seleciono o tipo de venda 76 - CREDCASA

And Eu seleciono o plano 12 X

!-- Realizando a Consulta Simplificada
And Eu clico no botao Captura de Limite

And Eu clico na aba Produto
And Eu clico no botao adicionar produto
And Eu pesquiso e seleciono o produto 299643
And Eu clico no icone detalhes do produto 299643
And Eu seleciono o local de empenho LOJA
And Eu seleciono a opcao de entrega NORMAL
And Eu clico no botao Reservar
And Eu clico no botao Ok do modal

And Eu clico na aba Produto
And Eu clico no botao adicionar produto
And Eu pesquiso e seleciono o produto 294795
And Eu clico no icone detalhes do produto 294795
And Eu seleciono o local de empenho LOJA
And Eu seleciono a opcao de entrega NORMAL
And Eu clico no botao Reservar
And Eu clico no botao Ok do modal

And Eu clico na aba Produto
And Eu clico no botao adicionar produto

And Eu pesquiso o produto 224450
And Eu deixo o produto sem promocao
And Eu clico no botao Selecionar Produto

And Eu clico no icone detalhes do produto 224450
And Eu seleciono o local de empenho LOJA
And Eu seleciono a opcao de entrega NORMAL
And Eu clico no botao Reservar
And Eu clico no botao Ok do modal


And Eu clico na aba Garantia
And Eu removo a garantia 215921 do produto 299643
And Eu removo a garantia 215920 do produto 294795
And Eu removo a garantia 144682 do produto 294795
And Eu informo o usuario 534 e senha 1 para validar exclusao
And Eu removo a garantia 215921 do produto 224450


And Eu clico na aba Produto
And Eu clico no produto 294795
And Eu clico na aba Garantia
And Eu clico no botao Adicionar Garantia
And Eu seleciono a garantia 144681

And Eu clico na aba Serviços
And Eu removo o servico 303096
And Eu informo o usuario 534 e senha 1 para validar exclusao
And Eu removo o servico 302075
And Eu informo o usuario 534 e senha 1 para validar exclusao

And Eu clico em adicionar servico
And Eu adiciono o servico 297315


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

Scenario: Cancelar pedido
Meta:
@tag component:Processos Ambiente Validacao - MVS
@context PROCESSOS_VALIDACAO
@base MVSH
@grupoInstancias Pedidos
@issue rgs-91
@rgs-91

Given Eu acesso a instancia padraoProducao
When Eu faco login na loja 1290 com usuario 673 e senha 1111
And Eu acesso o menu Consultar > Pedido
And Eu consulto o pedido
And Eu clico no botao Cancelar pedido
!-- Then Eu salvo o pedido do teste @rgs-91 no relatorio
