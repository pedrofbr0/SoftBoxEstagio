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
@issue trkdev-08
@trkdev-08
@skip
Given Eu acesso a instancia padrao
When Eu faco login na loja 4010 com usuario 7380 e senha 1
And Eu pesquiso o cpf 191
And Eu clico no botao 'Elaborar Pedido'

And Eu seleciono o tipo de venda 1 - A VISTA

And Eu clico no botao adicionar produto
And Eu pesquiso e seleciono o produto 711
And Eu clico no icone detalhes do produto 711
And Eu seleciono o local de empenho LOJA
And Eu seleciono a opcao de entrega NORMAL
And Eu clico no botao Reservar

And Eu clico na aba Serviços
And Eu removo o servico 307085
And Eu removo o servico 307084
And Eu removo o servico 305855
And Eu removo o servico 290795
And Eu removo o servico 153414
And Eu removo o servico 163594
And Eu removo o servico 302075

And Eu clico na aba Planilha
And Eu clico no botao Adicionar Forma Pagamento
And Eu seleciono o 1 º tipo de pagamento V
And Eu seleciono a 1 ª forma de pagamento Dinheiro

Then Eu clico no botao Salvar
And Eu clico no botao Ok do modal
And Eu clico no botao Fechamento Total
And Eu valido o status do pedido 30 - Ag. Receb. ou em Analise p/Troca Minha Casa Melhor 
!-- And Eu salvo o pedido do teste @smk-06 no relatorio

Scenario: Efetua o recebimento do pedido
Meta:
@tag component:Regressão - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias Caixa
@issue trkdev-08
@trkdev-08

Given Eu acesso o pdv web padrao
When Eu faco login no pdv web 111 na loja 4010 com usuario 7380 e senha 1
And Eu acesso o pdv menu Pedido
And No pdv eu pesquiso o pedido
And No pdv eu clico no botao receber pedido
And No pdv eu preencho os valores
And No pdv eu clico no botao Emitir Cupom
Then No pdv eu valido se o pedido foi recebido

Scenario: Elaboração de Pré-recibo
Meta:
@tag component:Regressão - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue trkdev-08
@trkdev-08

Given Eu acesso a instancia padrao
When Eu faco login na loja 4010 com usuario 7380 e senha 1
And Eu acesso o menu Faturamento > Troca/Devolução > Pré-Recibos
And Eu clico no botao novo Pré-recibo

!-- Pesquisando pedido e definindo quais produtos e serviços serao trocados/devolvidos
And Eu informo o numero do pedido
And Eu seleciono a empresa 5 - RN COMERCIO VAREJISTA S.A RELOH
And Eu informo a loja 4010
And Eu seleciono o processo Troca
And Eu clico no botao pesquisar

!-- Remove um produto da solicitacao de troca
And Eu seleciono o produto item 39547
And Eu clico no botao Remover Produto da solicitacao


And Eu clico no botao Ok da tela de pre-recibo

!-- Informando Motivo para Serviços
And Eu seleciono o servico item CURSO MAISABER
And Eu clico no botao Motivo Servico
And Eu informo o motivo 3 - SERVICO NAO UTILIZADO para o servico selecionado
And Eu informo uma observacao OBS MOTIVO para o servico selecionado
And Eu clico no botao Concluir aprovacao de servico

And Eu clico no botao Salvar solicitacao

!-- Pesquisar pre-recibo
And Eu clico no botao Pesquisar pre-recibo
And Eu seleciono o status 2 - AG. APROVACAO SERVICOS do pre-recibo
And Eu pesquiso o pre-recibo
And Eu clico no botao Pesquisar pre-recibo
And Eu seleciono o pre-recibo
And Eu clico no botao Editar pre-recibo

!-- Aprovando Serviços
And Eu seleciono servico 303096 pendente aprovacao
And Eu clico no botao Fluxo Aprovacao de servico
And Eu informo uma observacao para aprovar o servico
And Eu clico no botao Aprovar servico

!-- Concluir Pré-recibo
And Eu clico no botao Ok para concluir pre-recibo