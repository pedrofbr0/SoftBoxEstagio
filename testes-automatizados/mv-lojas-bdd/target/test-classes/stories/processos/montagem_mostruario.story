Narrative:
Como um usuário administrativo da loja
Eu quero gerar previsao de ordem de montagem
De modo que essas ordens sejam criadas no final do processo


Scenario: Gerar previsao de ordem montagem de mostruario (Validar produto que já possui montagem. (Bloqueio))
Meta:
@tag component:Regressão - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-28
@prcs-28

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso popup via menu Montagem > Mostruário > Gerar Previsão Ordem Montagem
And Eu clico botao Pesquisar produto modal
And Eu pesquiso um produto que possua ordem de montagem
And Eu clico no botao Pesquisar ordens de montagem
And Eu seleciono o produto para ordem de montagem
And Eu clico no botao Adicionar produto na ordem de montagem
Then Eu valido mensagem de bloqueio ja existe ordem de montagem

Scenario: Gerar previsao de ordem montagem de mostruario (Validar produto que ainda não possui montagem, mas também não tem estoque. (Bloqueio))
Meta:
@tag component:Regressão - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-28
@prcs-28

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso popup via menu Montagem > Mostruário > Gerar Previsão Ordem Montagem
And Eu clico botao Pesquisar produto modal
And Eu pesquiso um produto sem estoque e ordem de montagem
And Eu clico no botao Pesquisar ordens de montagem
And Eu seleciono o produto para ordem de montagem
And Eu clico no botao Adicionar produto na ordem de montagem
Then Eu valido mensagem de bloqueio produto sem estoque e ordem de montagem

Scenario: Gerar previsao de ordem montagem de mostruario (Validar produto que ainda não possui montagem e que tenha estoque. (Passa))
Meta:
@tag component:Regressão - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-28
@prcs-28

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso popup via menu Montagem > Mostruário > Gerar Previsão Ordem Montagem
And Eu clico botao Pesquisar produto modal
And Eu pesquiso um produto com estoque e sem ordem de montagem
And Eu clico no botao Pesquisar ordens de montagem
And Eu seleciono o produto para ordem de montagem
And Eu clico no botao Adicionar produto na ordem de montagem
And Eu clico no botao Confirmar ordem de montagem

!-- Informar dados da montadora
And Eu informo a montadora para a ordem de montagem
And Eu informo uma observacao para a montadora
!-- And Eu clico no botao Ok confirmar montadora

Then Eu clico no Ok para confirmar montadora e valido mensagem de sucesso da ordem de montagem

Scenario: Validar produto que já possui montagem. (com privilegio para liberar?) (Passa)
Meta:
@tag component:Regressão - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-28
@prcs-28

Given Eu executo o script de reset montagem_mostruario_privilegio_liberacao.sql
And Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 6699 e senha 1
And Eu acesso popup via menu Montagem > Mostruário > Gerar Previsão Ordem Montagem
And Eu clico botao Pesquisar produto modal
And Eu pesquiso um produto com estoque e com ordem de montagem
And Eu clico no botao Pesquisar ordens de montagem
And Eu seleciono o produto para ordem de montagem
And Eu clico no botao Adicionar produto na ordem de montagem
And Eu clico no botao Ok gerar ordem montagem de produto com ordem jah existente
And Eu clico no botao Confirmar ordem de montagem

!-- Informar dados da montadora
And Eu informo a montadora para a ordem de montagem
And Eu informo uma observacao para a montadora
!-- And Eu clico no botao Ok confirmar montadora

Then Eu clico no Ok para confirmar montadora e valido mensagem de sucesso da ordem de montagem