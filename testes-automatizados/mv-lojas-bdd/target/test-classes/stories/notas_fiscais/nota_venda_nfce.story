Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido
De modo que o pedido seja criado uma nota fiscal de venda ao final do processo

Scenario: Nota fiscal de venda ao final do processo 5 - VENDA NFCE
Meta:
@tag component:Smoke testing - RELOH
@context NOTAS_FISCAIS
@base RELOH
@grupoInstancias Pedidos
@issue nf-03
@nf-03

Given Eu acesso a instancia padrao
When Eu faco login na loja 4010 com usuario 7380 e senha 1
And Eu pesquiso o cpf 37778670550

And Eu clico no botao 'Elaborar Pedido'

And Eu seleciono o tipo de venda 1 - A VISTA

And Eu clico no botao adicionar produto
And Eu pesquiso e seleciono o produto 711
And Eu clico no icone detalhes do produto 711
And Eu seleciono o local de empenho LOJA
And Eu seleciono a opcao de entrega NORMAL
And Eu informo a flag entrega como NÃO
And Eu clico no botao Reservar

And Eu clico na aba Serviços
And Eu removo o servico 307085
And Eu removo o servico 307084
And Eu removo o servico 305855

And Eu removo o servico 303096
!-- And Eu removo o servico 290795
And Eu removo o servico 153414

!-- And Eu removo o servico 302075

And Eu clico na aba Planilha
And Eu clico no botao Adicionar Forma Pagamento
And Eu seleciono o 1 º tipo de pagamento V
And Eu seleciono a 1 ª forma de pagamento Dinheiro

Then Eu clico no botao Salvar
And Eu clico no botao Ok do modal
And Eu clico no botao Fechamento Total
And Eu valido o status do pedido 30 - Ag. Receb. ou em Analise p/Troca Minha Casa Melhor


Scenario: Efetua o recebimento do pedido
Meta:
@tag component:Regressão - RELOH
@context NOTAS_FISCAIS
@base RELOH
@grupoInstancias Caixa
@issue nf-03
@nf-03

Given Eu acesso o pdv web padrao
When Eu faco login no pdv web 111 na loja 4010 com usuario 7380 e senha 1
And Eu acesso o pdv menu Pedido
And No pdv eu pesquiso o pedido
And No pdv eu clico no botao receber pedido
And No pdv eu preencho os valores
And No pdv eu clico no botao Emitir Cupom
Then No pdv eu valido se o pedido foi recebido


Scenario: Realiza a consulta da nota fiscal
Meta:
@tag component:Processos - RELOH
@context NOTAS_FISCAIS
@base RELOH
@grupoInstancias AdmCD
@issue nf-03
@nf-03

Given Eu acesso a instancia padrao
When Eu faco login na loja 4010 com usuario 7380 e senha 1
And Eu acesso o menu Faturamento > Consulta Nota Fiscal
And Eu seleciono somente notas Processado
And Na consulta de nota fiscal eu informo pedido
And Na consulta de nota fiscal eu informo loja
And Na consulta de nota fiscal eu clico no botao Pesquisar
Then Eu valido nota fiscal encontrada 5	- VENDA NFCE
And Eu salvo o cupom fiscal do teste @nf-03 no relatorio