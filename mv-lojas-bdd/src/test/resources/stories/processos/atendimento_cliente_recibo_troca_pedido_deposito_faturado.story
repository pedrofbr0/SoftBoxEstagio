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
@issue prcs-08
@prcs-08
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu pesquiso o cpf 37778670550
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
@issue prcs-08
@prcs-08
@skip

Given Eu acesso o pdv web padrao
When Eu faco login no pdv web 110 na loja 291 com usuario 7349 e senha 1
And Eu acesso o pdv menu Pedido
And No pdv eu pesquiso o pedido
And No pdv eu clico no botao receber pedido
And No pdv eu preencho os valores
And No pdv eu clico no botao Emitir Cupom
Then No pdv eu valido se o pedido foi recebido


Scenario: Cria a carga para associar ao controle de faturamento
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-08
@prcs-08
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 47 com usuario 7380 e senha 1
And Eu acesso o menu Faturamento > Cadastros > Carga X Veículo
And No cadastro de carga eu clico no botao Novo
And No cadastro de carga eu informo a descricao Teste Automatizado
And No cadastro de carga eu informo a cidade de destino IBIRITE

And No faturamento transporte eu informo o motorista Jose da Silva
And No faturamento transporte eu informo o telefone motorista 3432326969
And No faturamento transporte eu informo um veiculo ativo

And No cadastro de carga eu clico no botao Salvar

And Eu associo a carga do deposito 601 ao controle de faturamento da loja 1101


Scenario: Cria a nota de transferencia do CD 47 para o CD 221
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-08
@prcs-08
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 47 com usuario 7380 e senha 1
And Eu acesso o menu Faturamento > Controles > Faturamento
And No controle de faturamento eu pesquiso a carga
And No controle de faturamento eu seleciono a carga
And No controle de faturamento eu seleciono o tipo de frete Emitente
And No controle de faturamento eu seleciono o controle
And No controle de faturamento eu clico no botao Faturar
And No controle de faturamento eu clico no botao Ok para iniciar o processo de faturamento
And No controle de faturamento eu clico no botao Cancelar para informar uma observacao para as Nota Fiscais


Scenario: Processa a nota de transferencia a nota no CD 221
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-08
@prcs-08
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 221 com usuario 7380 e senha 1
And Eu acesso o menu Faturamento > Notas Transferências
And No recebimento de Notas de transferencias eu clico no botao Pesquisar Nota
And Na consulta de Notas de transferencias eu informo o numero da Nota do deposito 601 da loja 1101 do pedido
And Na consulta de Notas de transferencias eu limpo o campo loja destino
And Na consulta de Notas de transferencias eu clico no botao Pesquisar Nota
And Na consulta de Notas de transferencias eu seleciono a Nota

And No recebimento de Notas de transferencias eu informo o grupo de estoque 40 - Base
And No recebimento de Notas de transferencias eu informo a quantidade conferida 1
And No recebimento de Notas de transferencias eu informo a observacao Teste Automatizado
And No recebimento de Notas de transferencias eu clico no botao Processar

And No recebimento de Notas de transferencias eu clico no botao Cancelar para Realizar o processamento para liberacao de pre-disponivel
And No recebimento de Notas de transferencias eu clico no botao Ok para confirmar processamento da nota

And No recebimento de Notas de transferencias eu salvo a carga do deposito 764 loja do pedido 1101


Scenario: Fatura a nota de transferencia a nota no CD 221
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-08
@prcs-08
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 221 com usuario 7380 e senha 1
And Eu acesso o menu Faturamento > Controles > Faturamento
And No controle de faturamento eu pesquiso a carga
And No controle de faturamento eu seleciono a carga
And No controle de faturamento eu seleciono o tipo de frete Emitente
And No controle de faturamento eu seleciono o controle
And No controle de faturamento eu clico no botao Faturar
And No controle de faturamento eu clico no botao Ok para iniciar o processo de faturamento
And No controle de faturamento eu clico no botao Cancelar para informar uma observacao para as Nota Fiscais


Scenario: Elaborar um atendimento para o pedido realizado
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-08
@prcs-08
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
And No recibo de troca eu informo o local do produto Cliente
And No recibo de troca eu informo o motivo do atendimento Cancelamento antes de 7 dias
And No recibo de troca eu clico no botao Salvar motivo
And No recibo de troca eu clico no botao Sim
And No recibo de troca eu clico no botao Ok

And No recibo de troca eu clico no icone Agendamento de Entrega
And No recibo de troca eu clico botao Confirmar Endereco Cliente

And No recibo de troca eu clico no icone Aprovar
And Nos dados da avaliacao eu informo a observacao Teste Automatizado
And Nos dados da avaliacao eu clico no botao Ok

And Eu valido mensagem de sucesso recibo de troca

Scenario: Cria a carga para associar ao controle de faturamento  
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-05
@prcs-05
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 47 com usuario 7380 e senha 1
And Eu acesso o menu Faturamento > Cadastros > Carga X Veículo
And No cadastro de carga eu clico no botao Novo
And No cadastro de carga eu informo a descricao Teste Automatizado
And No cadastro de carga eu informo a cidade de destino IBIRITE

And No faturamento transporte eu informo o motorista Jose da Silva
And No faturamento transporte eu informo o telefone motorista 3432326969
And No faturamento transporte eu informo um veiculo ativo

And No cadastro de carga eu clico no botao Salvar

And Eu associo a carga do deposito 601 ao controle de faturamento da loja 1101

Scenario: Cria a nota de transferencia do CD 47 para o CD 221
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-05
@prcs-05
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 47 com usuario 7380 e senha 1
And Eu acesso o menu Faturamento > Controles > Faturamento
And No controle de faturamento eu pesquiso a carga
And No controle de faturamento eu seleciono a carga
And No controle de faturamento eu seleciono o tipo de frete Emitente
And No controle de faturamento eu seleciono o controle
And No controle de faturamento eu clico no botao Faturar
And No controle de faturamento eu clico no botao Ok para iniciar o processo de faturamento
And No controle de faturamento eu clico no botao Cancelar para informar uma observacao para as Nota Fiscais


Scenario: Processa a nota de transferencia a nota no CD 221
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-05
@prcs-05
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 221 com usuario 7380 e senha 1
And Eu acesso o menu Faturamento > Notas Transferências
And No recebimento de Notas de transferencias eu clico no botao Pesquisar Nota
And Na consulta de Notas de transferencias eu informo o numero da Nota do deposito 601 da loja 1101 do pedido
And Na consulta de Notas de transferencias eu limpo o campo loja destino
And Na consulta de Notas de transferencias eu clico no botao Pesquisar Nota
And Na consulta de Notas de transferencias eu seleciono a Nota

And No recebimento de Notas de transferencias eu informo o grupo de estoque 40 - Base
And No recebimento de Notas de transferencias eu informo a quantidade conferida 1
And No recebimento de Notas de transferencias eu informo a observacao Teste Automatizado
And No recebimento de Notas de transferencias eu clico no botao Processar

And No recebimento de Notas de transferencias eu clico no botao Cancelar para Realizar o processamento para liberacao de pre-disponivel
And No recebimento de Notas de transferencias eu clico no botao Ok para confirmar processamento da nota

!-- And No recebimento de Notas de transferencias eu salvo a carga do deposito 764 loja do pedido 1101
And Salvar a carga do deposito 764 pedido loja 1101 tipo V controle faturamento


Scenario: Fatura a nota de transferencia a nota no CD 221
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-05
@prcs-05
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 221 com usuario 7380 e senha 1
And Eu acesso o menu Faturamento > Controles > Faturamento
And No controle de faturamento eu pesquiso a carga
And No controle de faturamento eu seleciono a carga
And No controle de faturamento eu seleciono o tipo de frete Emitente
And No controle de faturamento eu seleciono o controle
And No controle de faturamento eu clico no botao Faturar
And No controle de faturamento eu clico no botao Ok para iniciar o processo de faturamento
And No controle de faturamento eu clico no botao Cancelar para informar uma observacao para as Nota Fiscais


Scenario: Realiza o Recolhimento de Produto
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-05
@prcs-05
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso o menu Faturamento > Controles > Recolhimento Produto
And Eu aprovo a nota da loja 764 do controle de faturamento da loja 1101 do tipo controle D
And No controle de recolhimento eu informo o pedido
And No controle de recolhimento eu informo a loja 291 - DURVAL DE BARROS
And No controle de recolhimento eu clico no botao Pesquisar
And No controle de recolhimento eu seleciono a nota de devolucao
And No controle de recolhimento eu seleciono o status Processado
And No controle de recolhimento eu seleciono o grupo de estoque 1 - Loja
And No controle de recolhimento eu informo a observacao Teste Automatizado de Recolhimento
And No controle de recolhimento eu clico no botao Ok
And No controle de recolhimento eu seleciono o recolhimento
!-- And No controle de recolhimento eu clico no botao Salvar recolhimento
And No controle de recolhimento eu clico no botao Autorizar Recibo
And No controle de recolhimento eu clico no botao Ok modal sucesso
And No controle de recolhimento eu clico no botao Ok modal sucesso
!-- Then No controle de recolhimento eu valido a autorizacao do recibo

Scenario: Elaborar um pedido utilizando o recibo de troca
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias Pedidos
@issue prcs-04
@prcs-04
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
@issue prcs-04
@prcs-04
@skip

Given Eu acesso o pdv web padrao
When Eu faco login no pdv web 110 na loja 291 com usuario 7349 e senha 1
And Eu acesso o pdv menu Pedido
And No pdv eu pesquiso o pedido
And No pdv eu clico no botao receber pedido
And No pdv eu preencho os valores
And No pdv eu clico no botao Emitir Cupom
Then No pdv eu valido se o pedido foi recebido