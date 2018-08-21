Narrative:
Como um usuário adm da loja
Eu quero gerar um relatorio de deposito
De modo que o relatorio seja criado ao finalizar o processo

Scenario: Gerar relatorio deposito
Meta:
@tag component:Regressao - RELOH
@context RELATORIOS
@base RELOH
@grupoInstancias AdmCD
@issue rel-03
@rel-03

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 6699 e senha 1
And Eu acesso popup via menu Caixa > Controle Depósito > Relatório Depósito
And Eu informo a dta inicio do bordero 01/03/2018
And Eu informo a dta fim do bordero 26/03/2018
And Eu informo o cod loja do bordero
And Eu clico no botao Imprimir
Then Eu valido a impressao do relatorio de deposito do bordero