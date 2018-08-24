Narrative:
Como um usuário adm da loja
Eu quero gerar um relatorio de comissao analitico
De modo que o relatorio seja criado ao finalizar o processo

Scenario: Gerar relatorio analítico
Meta:
@tag component:Regressao - RELOH
@context RELATORIOS
@base RELOH
@grupoInstancias AdmCD
@issue rel-01
@rel-01

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 6699 e senha 1
And Eu acesso o menu Fopag > Relatório > Comissão Analítico Especial
And Eu clico no botao pesquisar referencia do comissao analitico especial

And Eu informo a referencia 03/2013 da comissao analitico especial
And Eu clico no botao pesquisar controle comissao analitico especial

And Eu seleciono o controle comissao analitico especial ano 2013 mes 3
And Eu informo o nro funcionario do relatorio de comissao analitico 9018408
And Eu clico no botao gerar relatorio de comissao analitico
!-- Then Eu valido o relatorio comissao analitico