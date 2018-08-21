Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido
De modo que o pedido seja criado uma nota fiscal de venda ao final do processo

Scenario: Nota fiscal de venda ao final do processo 10	VENDA NOTA FISCAL - UF2010
Meta:
@tag component:Smoke testing - RELOH
@context NOTAS_FISCAIS
@base RELOH
@grupoInstancias Pedidos
@issue nf-57
@nf-57

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 69930 e senha 1
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
@issue nf-57
@nf-57

Given Eu acesso o pdv web padrao
When Eu faco login no pdv web 110 na loja 291 com usuario 7349 e senha 1
And Eu acesso o pdv menu Pedido
And No pdv eu pesquiso o pedido
And No pdv eu clico no botao receber pedido
And No pdv eu preencho os valores
And No pdv eu clico no botao Emitir Cupom
Then No pdv eu valido se o pedido foi recebido


Scenario: Fatura o pedido recebido 
Meta:
@tag component:Processos - RELOH
@context NOTAS_FISCAIS
@base RELOH
@grupoInstancias AdmCD
@issue nf-57
@nf-57

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso o menu Faturamento > Controles > Faturamento Pdv
And Eu informo o filtro numero do pedido controle de faturamento
And Eu clico no botao pesquisar controle de faturamento
And Eu seleciono o controle de faturamento
And Eu clico no botao Faturar controle de faturamento


Scenario: Realiza a consulta da nota fiscal
Meta:
@tag component:Processos - RELOH
@context NOTAS_FISCAIS
@base RELOH
@grupoInstancias AdmCD
@issue nf-57
@nf-57

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso o menu Faturamento > Edição de Nota Fiscal
And Na edicao de nota fiscal eu informo numero da nf
And Na edicao de nota fiscal eu informo a serie 291 da nf
And Na edicao de nota fiscal eu informo a loja 291 da nf
And Na edicao de nota fiscal eu clico no botao Pesquisar
And Na edicao de nota fiscal eu seleciono a nota encontrada
And Na edicao de nota fiscal eu clico no botao Editar nf
And Na edicao de nota fiscal eu informo o valor base ICMS 300
And Na edicao de nota fiscal eu clico no botao Salvar
Then Eu valido a mensagem de sucesso da edicao de nota
And Eu salvo a nota fiscal do teste @nf-57 no relatorio