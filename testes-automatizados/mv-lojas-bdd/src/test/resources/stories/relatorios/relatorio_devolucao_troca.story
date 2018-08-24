Narrative:
Como um usuário adm da loja
Eu quero gerar um relatorio devolucao/troca
De modo que o relatorio seja criado ao finalizar o processo

Scenario: Gerar relatorio devolucao/troca
Meta:
@tag component:Regressao - RELOH
@context RELATORIOS
@base RELOH
@grupoInstancias AdmCD
@issue rel-04
@rel-04

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 6699 e senha 1
And Eu acesso o menu Caixa > Relatórios > Devolução/Troca
And No relatorio de devolucao e troca eu pesquiso a forma de pagamento 1
And No relatorio de devolucao eu valido a forma de pagamento escolhida 1 - Dinheiro
And No relatorio de devolucao e troca eu informo a loja 78
And No relatorio de devolucao eu valido a loja escolhida F0078 - CACHOEIRO ITAPEMIRIM - ES
And No relatorio de devolucao e troca eu informo a data inicio 01/05/2018
And No relatorio de devolucao e troca eu informo a data fim 15/05/2018
And No relatorio de devolucao e troca eu informo o departamento 1
And No relatorio de devolucao eu valido o departamento escolhido 1 - Audio
And No relatorio de devolucao e troca eu clico no botao Imprimir
Then No relatorio de devolucao e troca eu valido a impressao