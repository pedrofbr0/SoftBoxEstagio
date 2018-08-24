Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido
De modo que o pedido seja criado uma nota fiscal de venda ao final do processo


Scenario: Nota fiscal de transferencia ao final do processo 25 TRANSFERENCIA - UF2120
Meta:
@tag component:Processos - RELOH
@context NOTAS_FISCAIS
@base RELOH
@grupoInstancias AdmCD
@issue nf-02
@nf-02

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso o menu Faturamento > Cadastros > Autorização
!-- ----------------------------------
!--  CRIANDO NOVA AUTORIZACAO
!-- ----------------------------------

And Na tela de autorizacao eu clico no botao Novo
And Na tela de autorizacao eu seleciono o tipo de autotizacao 1 - NOTA FISCAL
And Na tela de autorizacao eu informo a loja destino 47
And Na tela de autorizacao eu informo a observacao Teste
And Na tela de autorizacao eu informo o tipo de nota fiscal 25 - TRANSFERENCIA - UF2120
And Na tela de autorizacao eu clico no botao Salvar

!-- Registro salvo com sucesso
!-- And Na tela de autorizacao eu clico em Ok no alert

!-- Deseja preencher os itens da autorizacao?
!-- And Na tela de autorizacao eu clico em Sim no alert


!-- ----------------------------------
!-- ADICIONANDO PRODUTO NA AUTORIZACAO
!-- ----------------------------------

And Na tela de autorizacao eu clico no botao Inserir
And No popup de itens de autorizacao eu pesquiso o produto 16397
And No popup de itens de autorizacao eu informo a quantidade solicitada de 1
And No popup de itens de autorizacao eu clico no botao Salvar

!-- Item salvo com sucesso
!-- And No popup de itens de autorizacao eu clico em Ok no alert
!-- And No popup de itens de autorizacao eu clico no botao Sair


!-- ----------------------------------
!-- PROCESSANDO AUTORIZACAO
!-- ----------------------------------

And Na tela de autorizacao eu clico no botao Processar
And Na tela de autorizacao eu clico em Ok no alert

!-- ----------------------------------
!-- PESQUISANDO AUTORIZACAO
!-- ----------------------------------

And Na tela de autorizacao eu clico no botao Pesquisar
And Na tela de autorizacao eu informo a autorizacao
And Na tela de autorizacao eu seleciono o status da autorizacao Pendente
And Na tela de autorizacao eu clico no botao Pesquisar


!-- --------------------------------------
!-- INFORMANDO A QUANTIDADE AUTORIZADA
!-- --------------------------------------

And Na tela de autorizacao eu seleciono a autorizacao
And Na tela de autorizacao eu clico no botao Itens

And No popup de itens de autorizacao eu informo a quantidade autorizada de 1
And No popup de itens de autorizacao eu clico no botao Salvar

!-- Item salvo com sucesso
!-- And No popup de itens de autorizacao eu clico em Ok no alert
!-- And No popup de itens de autorizacao eu clico no botao Sair

!-- Autorizacao processada com sucesso
And Na tela de autorizacao eu clico no botao Processar
And Na tela de autorizacao eu clico em Ok no alert


And Na tela de autorizacao eu seleciono a autorizacao
And Na tela de autorizacao eu clico no botao Processar para preencher nota avulsa


!-- GERAR NF AVULSA DE TRANSFERENCIA
And Eu seleciono o grupo de estoque origem 1 - Loja
And Eu clico no botao Informar Parametros
And Na tela de nota avulsa eu seleciono a empresa 5 - RN COMERCIO VAREJISTA S.A RELOH
And Na tela de nota avulsa eu seleciono a loja destino 601 - CD 47 - CD CONTAGEM 1 - MG
And Na tela de nota avulsa eu seleciono o grupo estoque destino 60 - Transito
And Na tela de parametros da nota avulsa eu clico no botao Concluir

And Na tela de nota avulsa eu clico no botao Concluir
!-- And Na tela de nota avulsa eu clico em Ok no alert


Scenario: Realiza a consulta da nota fiscal
Meta:
@tag component:Processos - RELOH
@context NOTAS_FISCAIS
@base RELOH
@grupoInstancias AdmCD
@issue nf-02
@nf-02

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso o menu Faturamento > Consulta Nota Fiscal
And Eu seleciono somente notas Aguardando Processamento
!-- And Na consulta de nota fiscal eu informo pedido
!-- And Na consulta de nota fiscal eu informo loja
And Na consulta de nota fiscal eu informo o numero da nota
And Na consulta de nota fiscal eu clico no botao Pesquisar
Then Eu valido nota fiscal encontrada 25 - TRANSFERENCIA - UF2120
And Eu salvo a nota fiscal do teste @nf-02 no relatorio