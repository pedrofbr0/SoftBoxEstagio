Narrative:
Como um usuário vendedor da loja
Eu quero editar o cadastro de um cliente Pessoa Jurídica, perfil crediário próprio

Scenario: editar o cadastro de um cliente Pessoa Jurídica, perfil crediário próprio
Meta:
@tag component:Regressao - RELOH
@context CADASTROS
@base RELOH
@grupoInstancias Pedidos
@issue cad-21
@cad-21

!-- ----------------------------------------------
!-- TELA DE LOGIN
!-- ----------------------------------------------
Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1

!-- ----------------------------------------------
!-- TELA CONSULTA CLIENTE
!-- ----------------------------------------------
And Eu clico na checkbox de Tipo de Pessoa
And Eu informo CNPJ 01.981.967/0001-64
And Eu espero 10000 milisegundos
And Eu clico no botao pesquisar cliente
And Eu clico no radio pesquisa cliente

!-- ----------------------------------------------
!-- TELA CADASTRO CLIENTE
!-- ----------------------------------------------
And Eu espero 6000 milisegundos
And Eu clico no botao editar

!-- ----------------------------------------------
!-- TELA ENDEREÇO
!-- ----------------------------------------------
And Eu informo o situacao da residencia ALUGADA
And Eu clico no botao OK

!-- ----------------------------------------------
!-- TELA CADASTRO CLIENTE
!-- ----------------------------------------------
And Eu clico aba de Referencias

!-- ----------------------------------------------
!-- TELA REFERÊNCIAS
!-- ----------------------------------------------
And Eu clico no botao editar referencia
And Eu seleciono o vinculo da referencia AVO
And Eu clico no botao ok da tela Referencias

!-- ----------------------------------------------
!-- TELA CADASTRO CLIENTE
!-- ----------------------------------------------
And Eu clico aba Comprador/Representante

!-- ----------------------------------------------
!-- TELA COMPRADOR/REPRESENTANTE
!-- ----------------------------------------------

And Eu clico no botao editar representante
And Eu informo cargo do representante ADM
And Eu clico no botao Ok salvar representante

!-- ----------------------------------------------
!-- TELA CADASTRO CLIENTE
!-- ----------------------------------------------
And Eu espero 4000 milisegundos
And Eu clico aba Crediario Proprio PJ

!-- ----------------------------------------------
!-- TELA CREDIARIO PROPRIO
!-- ----------------------------------------------
And Eu informo o ponto de referencia PJ AV NICOMEDES
And Eu clico botao ok da tela Crediario Proprio PJ

!-- ----------------------------------------------
!-- TELA CADASTRO CLIENTE
!-- ----------------------------------------------
And Eu clico no botao Salvar cliente
And Eu espero 10000 milisegundos
Then Eu valido a mensagem de sucesso do cadastro do cliente