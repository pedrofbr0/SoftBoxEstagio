Narrative:
Como um usuário vendedor da loja
Eu quero cadastrar um novo cliente Pessoa Física, perfil básico
De modo que o cliente possa ser utilizado na tela de pedido

Scenario: cadastrar um novo cliente Pessoa Física, perfil básico
Meta:
@tag component:Regressao - RELOH
@context CADASTROS
@base RELOH
@grupoInstancias Pedidos
@issue cad-14
@cad-14

!-- ----------------------------------------------
!-- TELA DE LOGIN
!-- ----------------------------------------------
Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1

!-- ----------------------------------------------
!-- TELA CONSULTA CLIENTE
!-- ----------------------------------------------
And Eu informo um novo CPF
And Eu espero 5000 milisegundos
And Eu clico no botao Novo Cliente
And Eu espero 6000 milisegundos

!-- ----------------------------------------------
!-- TELA CADASTRO CLIENTE
!-- ----------------------------------------------
And Eu informo o nome do cliente Rogerinho do Ingá
And Eu seleciono o grupo BASICO
And Eu informo o numero de telefone residencial 34 32118345 1
And Eu informo o numero de telefone celular 34 999993456 1
And Eu clico no botao Inserir Novo Endereco

!-- ----------------------------------------------
!-- TELA PESQUISA ENDEREÇO
!-- ----------------------------------------------
And Eu informo o CEP 38400-752
And Eu clico no botao Pesquisar Endereco
And Eu espero 5000 milisegundos
And Eu clico no endereco no campo radio
And Eu clico no botao Selecionar

!-- ----------------------------------------------
!-- TELA ENDEREÇO
!-- ----------------------------------------------
And Eu informo o numero da residencia 456
And Eu seleciono o tipo de complemento CASA
And Eu informo a data de residencia 04/2015
And Eu informo o situacao da residencia PROPRIA
And Eu informo o tipo de endereco PRINCIPAL
And Eu clico no botao OK

!-- ----------------------------------------------
!-- TELA CADASTRO CLIENTE
!-- ----------------------------------------------
And Eu clico no botao Salvar cliente
And Eu espero 5000 milisegundos
Then Eu valido a mensagem de sucesso do cadastro do cliente
And Eu salvo o pedido do teste @cad-14 no relatorio