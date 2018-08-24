Narrative:
Como um usuário administrativo da loja
Eu quero gerar previsao de ordem de desmontagem
De modo que essas ordens sejam criadas no final do processo


Scenario: Validar desmontagem de produtos montados.
Meta:
@tag component:Regressão - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-29
@prcs-29

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso popup via menu Montagem > Mostruário > Gerar Previsão Ordem Desmontagem
And Eu clico botao Pesquisar produto modal
And Eu pesquiso um produto que possua ordem de montagem
And Eu clico no botao Pesquisar ordens de montagem
And Eu seleciono o produto para ordem de montagem
And Eu clico no botao Adicionar produto na ordem de desmontagem
And Eu clico no botao Confirmar ordem de desmontagem

!-- Informar dados da montadora
And Eu informo a montadora para a ordem de montagem
And Eu informo uma observacao para a montadora
And Eu clico no botao Ok confirmar montadora


Scenario: Validar desmontagem de produtos que não existe ordem de montagem. (Bloqueio)
Meta:
@tag component:Regressão - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-29
@prcs-29

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso popup via menu Montagem > Mostruário > Gerar Previsão Ordem Desmontagem
And Eu clico botao Pesquisar produto modal
And Eu pesquiso um produto com estoque e sem ordem de montagem
And Eu clico no botao Pesquisar ordens de montagem
Then Eu valido mensagem produto sem ordem de montagem