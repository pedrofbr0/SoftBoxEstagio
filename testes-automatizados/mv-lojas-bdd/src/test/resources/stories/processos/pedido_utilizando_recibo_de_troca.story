Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um processo de troca/devolução de apenas um produto de um pedido com 2 produtos 
De modo que o pré-recibo esteja aprovado ao final do processo

!-- *******************
!--   RELOH
!-- *******************

Scenario: Elaborar um pedido a vista
Meta:
@tag component:Regressão - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias Pedidos
@issue trkdev-11
@trkdev-11

Given Eu acesso a instancia padrao
When Eu faco login na loja 4010 com usuario 7380 e senha 1
And Eu pesquiso o cpf 37778670550
And Eu clico no botao 'Elaborar Pedido'

And Eu seleciono o tipo de venda 1 - A VISTA

And Eu clico no botao adicionar produto
And Eu pesquiso e seleciono o produto 287377
And Eu clico no icone detalhes do produto 287377
And Eu seleciono o local de empenho DEPOSITO
And Eu seleciono a opcao de entrega AGENDADA
And Eu clico no botao Agendada
And Eu seleciono uma data disponivel
And Eu clico no botao Reservar

And Eu clico na aba Garantia

And Eu removo a garantia 140358 do produto 287377
And Eu removo a garantia 144658 do produto 287377
And Eu removo a garantia 215914 do produto 287377

And Eu clico na aba Serviços
And Eu removo o servico 307085
And Eu removo o servico 307084
And Eu removo o servico 305855


And Eu removo o servico 290795
And Eu removo o servico 153414

And Eu removo o servico 302075
And Eu removo o servico 163594
!-- And Eu removo o servico 278095
!-- And Eu removo o servico 305038
And Eu removo o servico 303096


And Eu clico na aba Planilha
And Eu clico no botao Adicionar Forma Pagamento
And Eu seleciono o 1 º tipo de pagamento V
And Eu seleciono a 1 ª forma de pagamento Recibo de Troca
And Eu clico no botao pesquisar Recibo de Troca
And Eu informo um pedido com credito
And Eu informo um codigo da loja do pedido com credito
And Eu informo uma empresa do pedido com credito
And Eu clico no botao Verificar Credito
And Eu clico no botao Ok do modal
And Eu clico no botao Utilizar Credito

And Eu clico no botao Adicionar Forma Pagamento
And Eu seleciono o 2 º tipo de pagamento V
And Eu seleciono a 2 ª forma de pagamento Dinheiro

Then Eu clico no botao Salvar
And Eu clico no botao Ok do modal
And Eu clico no botao Fechamento Total
And Eu valido o status do pedido 23 - Ag. Liberação Pós Venda
And Eu clico no botao Ok do modal

And Eu clico no botao Liberar Aguardando Central
And Eu informo uma descricao XYZ para liberacao pos venda
And Eu clico no botao Sim do modal


Scenario: Efetua o recebimento do pedido
Meta:
@tag component:Regressão - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias Caixa
@issue trkdev-11
@trkdev-11

Given Eu acesso o pdv web padrao
When Eu faco login no pdv web 111 na loja 4010 com usuario 7380 e senha 1
And Eu acesso o pdv menu Pedido
And No pdv eu pesquiso o pedido
And No pdv eu clico no botao receber pedido
And No pdv eu preencho os valores
And No pdv eu clico no botao Emitir Cupom
Then No pdv eu valido se o pedido foi recebido

