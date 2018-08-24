Narrative:
Como um usuário adm da loja
Eu quero pesquisar/editar uma regra de tratativa
De modo que essa regra será utilizada na tela de atentimento

Scenario: Pesquisar/Editar uma tratativa - UC001 - Manter Tratativas - Fluxo Principal
Meta:
@tag component:Regressao - RELOH
@context CADASTROS
@base RELOH
@grupoInstancias AdmCD
@issue cad-11
@cad-11
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso o menu Administração > Módulo Atendimento Cliente > Editar Tratativa
And Eu informo o filtro descricao trativa Arrependimento
And Eu clico no botao Pesquisar regra de tratativa
And Eu clico no icone Editar regra de tratativa
And Eu informo descricao Arrependimento regra de tratativa
And Eu informo dias 3 regra de tratativa
And Eu informo horas 0 regra de tratativa
And Eu informo minutos 0 regra de tratativa
And Eu clico no botao Salvar regra de tratativa
Then Eu valido a mensagem de sucesso de regra de tratativa