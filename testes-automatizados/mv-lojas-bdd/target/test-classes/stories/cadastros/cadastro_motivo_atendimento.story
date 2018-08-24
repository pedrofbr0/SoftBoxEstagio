Narrative:
Como um usuário adm da loja
Eu quero criar um mecanismo para que o usuário seja capaz de manter os motivos de atendimentos
De modo que essa regra será utilizada na tela de atentimento

Scenario: Cadastrar motivos de atendimento - UC014
Meta:
@tag component:Regressao - RELOH
@context CADASTROS
@base RELOH
@grupoInstancias AdmCD
@issue cad-13
@cad-13
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso o menu Administração > Módulo Atendimento Cliente > Motivo de Atendimento
And Eu clico no botao Cadastrar motivo atendimento
And Eu informo a descricao Teste automatizado motivo atendimento
And Eu clico no botao Salvar motivo atendimento
Then Eu valido a mensagem de sucesso de motivo de atendimento

Scenario: Cadastrar motivos de atendimento jah existente
Meta:
@tag component:Regressao - RELOH
@context CADASTROS
@base RELOH
@grupoInstancias AdmCD
@issue cad-13
@cad-13
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso o menu Administração > Módulo Atendimento Cliente > Motivo de Atendimento
And Eu clico no botao Cadastrar motivo atendimento
And Eu informo a descricao Teste automatizado motivo atendimento
And Eu clico no botao Salvar motivo atendimento
Then Eu valido a mensagem de motivo de atendimento ja existente

Scenario: Pesquisar/Editar motivo de atendimento 
Meta:
@tag component:Regressao - RELOH
@context CADASTROS
@base RELOH
@grupoInstancias AdmCD
@issue cad-13
@cad-13
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso o menu Administração > Módulo Atendimento Cliente > Motivo de Atendimento
And Eu informo a descricao Teste automatizado motivo atendimento
And Eu clico no botao Pesquisar motivo atendimento
And Eu clico no icone Editar motivo atendimento
And Eu informo a descricao Teste automatizado motivo atendimento
And Eu clico no botao Salvar motivo atendimento
Then Eu valido a mensagem de sucesso de motivo de atendimento

Scenario: Pesquisar/Excluir motivo de atendimento 
Meta:
@tag component:Regressao - RELOH
@context CADASTROS
@base RELOH
@grupoInstancias AdmCD
@issue cad-13
@cad-13
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso o menu Administração > Módulo Atendimento Cliente > Motivo de Atendimento
And Eu informo a descricao Teste automatizado motivo atendimento
And Eu clico no botao Pesquisar motivo atendimento
And Eu clico no botao Excluir motivo atendimento
And Eu clico no botao Sim modal motivo atendimento
Then Eu valido a mensagem de sucesso exclusao de motivo de atendimento
