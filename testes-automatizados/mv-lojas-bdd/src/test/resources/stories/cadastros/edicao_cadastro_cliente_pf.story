Narrative:
Como um usuário vendedor da loja
Eu quero editar o cadastro de um cliente Pessoa Física, perfil crediário próprio

Scenario: editar o cadastro de um cliente Pessoa Física, perfil crediário próprio
Meta:
@tag component:Regressao - RELOH
@context CADASTROS
@base RELOH
@grupoInstancias Pedidos
@issue cad-20
@cad-20

!-- ----------------------------------------------
!-- TELA DE LOGIN
!-- ----------------------------------------------
Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1

!-- ----------------------------------------------
!-- TELA CONSULTA CLIENTE
!-- ----------------------------------------------
And Eu informo CPF 499.396.010-77
And Eu espero 10000 milisegundos
And Eu clico no botao pesquisar cliente
And Eu clico no radio pesquisa cliente

!-- ----------------------------------------------
!-- TELA CADASTRO CLIENTE
!-- ----------------------------------------------
And Eu clico no botao editar

!-- ----------------------------------------------
!-- TELA ENDEREÇO
!-- ----------------------------------------------
And Eu informo o situacao da residencia ALUGADA
And Eu clico no botao OK

!-- ----------------------------------------------
!-- TELA CADASTRO CLIENTE
!-- ----------------------------------------------
And Eu clico aba de Identificação/Trabalho

!-- ----------------------------------------------
!-- TELA IDENTIFICAÇÃO/TRABALHO
!-- ----------------------------------------------
And Eu espero 5000 milisegundos
And Eu seleciono tipo de comprovante de renda Declaracao do contador
And Eu clico no botao ok da tela Identificacao/Trabalho

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
And Eu clico aba Crediario Proprio

!-- ----------------------------------------------
!-- TELA CREDIARIO PROPRIO
!-- ----------------------------------------------
And Eu informo ponto de referencia AVENIDA NICOMEDES
And Eu clico botao ok da tela Crediario Proprio

!-- ----------------------------------------------
!-- TELA CADASTRO CLIENTE
!-- ----------------------------------------------
And Eu clico no botao Salvar cliente
And Eu espero 12000 milisegundos
Then Eu valido a mensagem de sucesso do cadastro do cliente