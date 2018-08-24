Narrative:
Como um usuário adm da loja
Eu quero realizar a confirmação de depósito
De modo que a confirmação de depósito seja realizada ao finalizar o processo

Scenario: Elaborar confirmação de depósito
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base MVSH
@grupoInstancias AdmCD
@issue prcs-11
@prcs-11

Given Eu acesso a instancia padrao
When Eu faco login na loja 1002 com usuario 7380 e senha 1
And Eu acesso o menu Caixa > Controle Depósito > Confirmação Depósito
And Eu seleciono o bordero para confirmacao
And Eu clico no botao Confirmar deposito

And Eu informo a data do deposito
And Eu informo a hora do deposito 12:00
And Eu informo o valor de deposito em dinheiro
And Eu seleciono checkbox banco fora do padrao
And Eu seleciono o banco BANCO DO BRASIL S.A.
And Eu informo o numero do comprovante 2222222222 deposito em dinheiro
And Eu informo a observacao da confirmacao deposito AUTOMAÇÃO
And Eu clico no botao Ok confirmar deposito

Then Eu valido a mensagem de sucesso da confirmacao
And Eu valido o banco fora do padrao 1


Scenario: Consultar depósitos confirmados
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base MVSH
@grupoInstancias AdmCD
@issue prcs-11
@prcs-11

Given Eu acesso a instancia padrao
When Eu faco login na loja 1002 com usuario 7380 e senha 1
And Eu acesso o menu Caixa > Controle Depósito > Consulta Depósitos Confirmados
And Eu informo o numero do bordero
And Eu clico no botao pesquisar depositos confirmados
Then Eu valido o bordero confirmado