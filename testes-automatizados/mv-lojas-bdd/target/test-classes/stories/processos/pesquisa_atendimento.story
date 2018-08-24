Narrative:
Como um usuário adm da loja
Eu quero pesquisar um atendimento
De modo que sejam apresentados os atendimentos criados conforme filtro da pesquisa

Scenario: UC015 - Consultar atendimentos
Meta:
@tag component:Regressao - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-01
@prcs-01
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso o menu Administração > Módulo Atendimento Cliente > Consulta Atendimento
And Eu informo o filtro protoco atendimento 621 consulta atendimento
And Eu informo o filtro data inicio 24/08/2017 consulta atendimento
And Eu informo o filtro data fim 24/08/2017 consulta atendimento
And Eu informo o filtro cpf/cnpj 191 consulta atendimento
And Eu informo o filtro loja 102 - ABILIO MACHADO - MG consulta atendimento
And Eu informo o filtro pedido 4647183 consulta atendimento
And Eu informo o filtro status Em Andamento consulta atendimento
And Eu informo o filtro tratativa Arrependimento consulta atendimento
And Eu informo o filtro acao Troca consulta atendimento
And Eu informo o filtro uf MG - Minas Gerais consulta atendimento
And Eu clico no botao Pesquisar atendimento
And Eu clico no icone Editar atendimento
Then Eu valido a informacao protocolo 621 em detalhes do atendimento