Narrative:
Como um usuário adm da loja
Eu quero gerar um relatorio demonstrativo venda servicos
De modo que o relatorio seja criado ao finalizar o processo

Scenario: Gerar relatorio demonstrativo venda servicos
Meta:
@tag component:Regressao - RELOH
@context RELATORIOS
@base RELOH
@grupoInstancias AdmCD
@issue rel-05
@rel-05

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 6699 e senha 1
And Eu acesso popup via menu Caixa > Relatórios > Demonstrativo de Vendas de Serviços
And No relatorio de demonstrativo de venda de servicos eu clico no botao Periodo
And No relatorio de demonstrativo de venda de servicos eu seleciono periodo encontrado
And No relatorio de demonstrativo de venda de servicos eu clico botao Selecionar
And No relatorio de demonstrativo de venda de servicos eu clico no botao Ok
And No relatorio de demonstrativo de venda de servicos eu clico no botao Imprimir
Then No relatorio de devolucao e troca eu valido a impressao