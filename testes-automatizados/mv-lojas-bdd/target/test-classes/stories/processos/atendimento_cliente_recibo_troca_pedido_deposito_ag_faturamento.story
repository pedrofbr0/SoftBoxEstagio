Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido a vista com empenho em loja/normal
De modo que o pedido seja criado ao finalizar o processo

Scenario: Elaborar um pedido a vista com empenho em loja/normal
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias Pedidos
@issue prcs-07
@prcs-07
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu pesquiso o cpf 00000000191
And Eu clico no botao 'Elaborar Pedido'

And Eu seleciono o tipo de venda 1 - A VISTA

And Eu clico no botao adicionar produto
And Eu pesquiso e seleciono o produto 711
And Eu clico no icone detalhes do produto 711
And Eu seleciono o local de empenho DEPOSITO
And Eu seleciono a opcao de entrega AGENDADA
And Eu clico no botao Agendada
And Eu seleciono uma data disponivel
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
@issue prcs-07
@prcs-07
@skip

Given Eu acesso o pdv web padrao
When Eu faco login no pdv web 110 na loja 291 com usuario 7349 e senha 1
And Eu acesso o pdv menu Pedido
And No pdv eu pesquiso o pedido
And No pdv eu clico no botao receber pedido
And No pdv eu preencho os valores
And No pdv eu clico no botao Emitir Cupom
Then No pdv eu valido se o pedido foi recebido

Scenario: Elaborar um atendimento para o pedido realizado
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-07
@prcs-07
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

!-- And Eu valido botao Troca simplificada desabilitado

And No atendimento ao cliente eu seleciono o tipo de troca Recibo de troca
And Eu clico botao Sim no modal de atendimento

!-- Recibo de troca
And No recibo de troca eu informo o local do produto Loja
And No recibo de troca eu informo o motivo do atendimento Cancelamento antes de 7 dias
And No recibo de troca eu clico no botao Salvar motivo
And No recibo de troca eu clico no botao Sim
And No recibo de troca eu clico no botao Ok

And No recibo de troca eu clico no icone Aprovar
And Nos dados da avaliacao eu informo a observacao Teste Automatizado
And Nos dados da avaliacao eu clico no botao Ok

And Eu valido mensagem de sucesso recibo de troca


Scenario: Elaborar um pedido utilizando o recibo de troca
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias Pedidos
@issue prcs-07
@prcs-07
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
And Eu seleciono a 1 ª forma de pagamento Recibo de Troca
And Eu clico no botao pesquisar Recibo de Troca
And Eu informo o pedido com credito
And Eu informo o codigo da loja do pedido com credito
And Eu informo a empresa do pedido com credito
And Eu clico no botao Verificar Credito
And Eu clico no botao Ok do modal
And Eu clico no botao Utilizar Credito

Then Eu clico no botao Salvar
And Eu clico no botao Ok do modal
And Eu clico no botao Fechamento Total
And Eu valido o status do pedido 30 - Ag. Receb. ou em Analise p/Troca Minha Casa Melhor

Scenario: Efetua o recebimento do pedido feito com recibo de troca
Meta:
@tag component:Regressão - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias Caixa
@issue prcs-07
@prcs-07
@skip

Given Eu acesso o pdv web padrao
When Eu faco login no pdv web 110 na loja 291 com usuario 7349 e senha 1
And Eu acesso o pdv menu Pedido
And No pdv eu pesquiso o pedido
And No pdv eu clico no botao receber pedido
And No pdv eu preencho os valores
And No pdv eu clico no botao Emitir Cupom
Then No pdv eu valido se o pedido foi recebido