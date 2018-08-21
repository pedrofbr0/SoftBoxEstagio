Narrative:
Como um usuário vendedor da loja
Eu quero elaborar o UC017 - Realizar a troca simplificada
De modo que processo seja realizado conforme solicitado

Scenario: Elaborar um pedido a vista com empenho em loja/normal
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias Pedidos
@issue prcs-03
@prcs-03
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu pesquiso o cpf 00000000191
And Eu clico no botao 'Elaborar Pedido'

And Eu seleciono o tipo de venda 1 - A VISTA

And Eu clico no botao adicionar produto
And Eu pesquiso e seleciono o produto 711
And Eu clico no icone detalhes do produto 711
And Eu seleciono o local de empenho LOJA
And Eu seleciono a opcao de entrega NORMAL
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
@context PROCESSOS
@base RELOH
@grupoInstancias Caixa
@issue prcs-03
@prcs-03
@skip

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
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-03
@prcs-03
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso o menu Faturamento > Controles > Faturamento Pdv
And Eu informo o filtro numero do pedido controle de faturamento
And Eu clico no botao pesquisar controle de faturamento
And Eu seleciono o controle de faturamento
And Eu clico no botao Faturar controle de faturamento

Scenario: Elaborar um atendimento para o pedido realizado
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-03
@prcs-03
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso o menu Administração > Módulo Atendimento Cliente > Atendimento ao Cliente
And No atendimento ao cliente eu informo o filtro pedido
And No atendimento ao cliente eu informo o filtro loja 291
And No atendimento ao cliente eu clico no botao Buscar pedidos
And No atendimento ao cliente eu seleciono o item 711-0-0
And No atendimento ao cliente eu clico no botao Proximo

!-- Tratativa
And No atendimento ao cliente eu seleciono a tratativa Arrependimento
And No atendimento ao cliente eu informo a observacao Teste Automatizado
And No atendimento ao cliente eu clico no botao Proximo

!-- Acao
And No atendimento ao cliente eu seleciono a acao Troca
And No atendimento ao cliente eu clico no botao Concluir

!-- Consulta atendimento
And Eu informo o filtro loja 291 consulta atendimento
And Na consulta atendimento eu informo o filtro pedido
And Eu clico no botao Pesquisar atendimento
And Eu clico no icone Editar atendimento

And No detalhes do atendimento eu clico no botao Proximo

And No atendimento ao cliente eu seleciono o tipo de troca Troca simplificada
And Eu clico botao Sim no modal de atendimento

!-- Recibo de troca
!-- And No recibo de troca eu informo o local do produto Loja
And No recibo de troca eu informo o motivo do atendimento Cancelamento antes de 7 dias
And No recibo de troca eu clico no botao Salvar motivo
!-- And No recibo de troca eu clico no botao Sim
!-- And No recibo de troca eu clico no botao Ok

And No recibo de troca eu clico no icone Aprovar
And Nos dados da avaliacao eu informo a observacao Teste Automatizado
And Nos dados da avaliacao eu clico no botao Ok

And No recibo de troca eu clico no botao Ok
And No recibo de troca eu clico no botao Imprimir
And Eu valido mensagem de sucesso recibo de troca simplificada

Scenario: Fatura o pedido criado na troca simplificada 
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-03
@prcs-03
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso o menu Faturamento > Controles > Faturamento Pdv
And Eu informo o filtro numero do pedido controle de faturamento
And Eu clico no botao pesquisar controle de faturamento
And Eu seleciono o controle de faturamento
And Eu clico no botao Faturar controle de faturamento

Scenario: Realiza o Recolhimento de Produto
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-03
@prcs-03
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso o menu Faturamento > Controles > Recolhimento Produto
And Eu aprovo a nota da loja 1101 do controle de faturamento da loja 1101 do tipo controle D
And No controle de recolhimento eu informo o pedido
And No controle de recolhimento eu informo a loja 291 - DURVAL DE BARROS
And No controle de recolhimento eu clico no botao Pesquisar
And No controle de recolhimento eu seleciono a nota de devolucao
And No controle de recolhimento eu seleciono o status Processado
And No controle de recolhimento eu seleciono o grupo de estoque 1 - Loja
And No controle de recolhimento eu informo a observacao Teste Automatizado de Recolhimento
And No controle de recolhimento eu clico no botao Ok
And No controle de recolhimento eu seleciono o recolhimento
And No controle de recolhimento eu clico no botao Autorizar Recibo
And No controle de recolhimento eu clico no botao Ok modal sucesso