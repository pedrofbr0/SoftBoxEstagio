Narrative:
Como um usuário adm da loja
Eu quero criar um mecanismo para que o usuário seja capaz de associar as tratativas com o Grupo de acesso e ainda suas regras.
De modo que essa regra será utilizada na tela de atentimento

Scenario: Cadastrar Associação tratativa x Grupo de Acesso x Regras - UC012
Meta:
@tag component:Regressao - RELOH
@context CADASTROS
@base RELOH
@grupoInstancias AdmCD
@issue cad-12
@cad-12
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso o menu Administração > Módulo Atendimento Cliente > Grupo Acesso x Tratativa
And Eu clico no botao Cadastrar grupo acesso tratativa
And Eu informo o ticket medio 10 grupo acesso tratativa
And Eu informo a tratativa Arrependimento grupo acesso tratativa
And Eu informo o grupo acesso Gerente de Lojas grupo acesso tratativa
And Eu informo a data base Data base grupo acesso tratativa
And Eu informo a linha de produto Linha de produto 1 grupo acesso tratativa
And Eu informo o local de retirada regra local retirada grupo acesso tratativa
And Eu marco a opcao Troca regra grupo acesso tratativa
And Eu marco a opcao Devolução regra grupo acesso tratativa
And Eu marco a opcao Abertura regra grupo acesso tratativa
And Eu marco a opcao Aprovação regra grupo acesso tratativa
And Eu marco a opcao Permite alterar forma de reembolso regra grupo acesso tratativa
And Eu clico Adicionar regra grupo acesso tratativa
And Eu clico no botao Salvar grupo acesso tratativa
Then Eu valido a mensagem de sucesso de grupo acesso tratativa

Scenario: Editar Associação tratativa x Grupo de Acesso x Regras
Meta:
@tag component:Regressao - RELOH
@context CADASTROS
@base RELOH
@grupoInstancias AdmCD
@issue cad-12
@cad-12
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso o menu Administração > Módulo Atendimento Cliente > Grupo Acesso x Tratativa
And Eu informo filtro grupo acesso Gerente de Lojas grupo acesso tratativa
And Eu informo a tratativa Arrependimento grupo acesso tratativa
And Eu clico no botao Pesquisar grupo acesso tratativa
And Eu clico no icone Editar grupo acesso tratativa
And Eu informo o ticket medio 10 grupo acesso tratativa
And Eu informo a data base Data base grupo acesso tratativa
And Eu informo a linha de produto Linha de produto 1 grupo acesso tratativa
And Eu informo o local de retirada regra local retirada grupo acesso tratativa
And Eu marco a opcao Troca regra grupo acesso tratativa
And Eu marco a opcao Devolução regra grupo acesso tratativa
And Eu marco a opcao Abertura regra grupo acesso tratativa
And Eu marco a opcao Aprovação regra grupo acesso tratativa
And Eu marco a opcao Permite alterar forma de reembolso regra grupo acesso tratativa
And Eu clico Adicionar regra grupo acesso tratativa
And Eu clico no botao Salvar grupo acesso tratativa
Then Eu valido a mensagem de sucesso de grupo acesso tratativa

Scenario: Excluir Associação tratativa x Grupo de Acesso x Regras
Meta:
@tag component:Regressao - RELOH
@context CADASTROS
@base RELOH
@grupoInstancias AdmCD
@issue cad-12
@cad-12
@skip

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso o menu Administração > Módulo Atendimento Cliente > Grupo Acesso x Tratativa
And Eu informo filtro grupo acesso Gerente de Lojas grupo acesso tratativa
And Eu informo a tratativa Arrependimento grupo acesso tratativa
And Eu clico no botao Pesquisar grupo acesso tratativa
And Eu clico icone Excluir grupo acesso tratativa
And Eu clico botao Sim modal grupo acesso tratativa
Then Eu valido mensagem sucesso exclusao grupo acesso tratativa