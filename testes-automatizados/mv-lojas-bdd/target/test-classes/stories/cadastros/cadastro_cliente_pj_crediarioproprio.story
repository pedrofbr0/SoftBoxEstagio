Narrative:
Como um usuário vendedor da loja
Eu quero cadastrar um novo cliente Pessoa Jurídica, perfil crediario proprio
De modo que o cliente possa ser utilizado na tela de pedido

Scenario: cadastrar um novo cliente Pessoa Jurídica, perfil crediario proprio
Meta:
@tag component:Regressao - RELOH
@context CADASTROS
@base RELOH
@grupoInstancias Pedidos
@issue cad-19
@cad-19

!-- ----------------------------------------------
!-- TELA DE LOGIN
!-- ----------------------------------------------
Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1

!-- ----------------------------------------------
!-- TELA CONSULTA CLIENTE
!-- ----------------------------------------------
And Eu clico na checkbox de Tipo de Pessoa
And Eu informo um novo CNPJ
And Eu clico no botao Novo Cliente

!-- ----------------------------------------------
!-- TELA CADASTRO CLIENTE
!-- ----------------------------------------------
And Eu espero 20000 milisegundos
And Eu informo o nome do cliente Transportes Alternativos LTDA
And Eu seleciono o grupo CREDIARIO PROPRIO
And Eu informo o nome fantasia Charitas-Gávea
And Eu informo a inscricao estadual 0706561471757
And Eu seleciono o tipo de empresa PRIVADA
And Eu informo o numero de telefone residencial 34 32154567 1
And Eu informo o numero de telefone celular 34 996546789 1
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
And Eu clico aba de Referencias

!-- ----------------------------------------------
!-- TELA REFERÊNCIAS
!-- ----------------------------------------------
And Eu clico botao nova referencia
And Eu informo o nome da referencia PAULO
And Eu informo o telefone da referencia 34 32456678 0
And Eu seleciono o vinculo da referencia CLIENTE
And Eu clico no botao ok da tela Referencias

And Eu clico botao nova referencia
And Eu informo o nome da referencia RICARDO
And Eu informo o telefone da referencia 34 32456676 3
And Eu seleciono o vinculo da referencia PAI
And Eu clico no botao ok da tela Referencias

!-- ----------------------------------------------
!-- TELA CADASTRO CLIENTE
!-- ----------------------------------------------
And Eu clico aba Comprador/Representante

!-- ----------------------------------------------
!-- TELA COMPRADOR/REPRESENTANTE
!-- ----------------------------------------------
And Eu clico botao novo representante
And Eu informo um novo CPF do representante
And Eu informo nome do representante Janiquário
And Eu informo o telefone 1 do representante 34 32445656 1
And Eu informo o telefone 2 do representante 34 32334456 2
And Eu informo cargo do representante Gerente
And Eu seleciono UF do representante MG
And Eu seleciono orgao emissor do representante SSP - SEC SEGURANCA PUBLICA
And Eu informo RG do representante 44.123.345
And Eu seleciono a data de emissao de rg do representante 05/06/2005
And Eu clico no botao Ok salvar representante

!-- ----------------------------------------------
!-- TELA CADASTRO CLIENTE
!-- ----------------------------------------------
And Eu espero 4000 milisegundos
And Eu clico aba Crediario Proprio PJ

!-- ----------------------------------------------
!-- TELA CREDIARIO PROPRIO
!-- ----------------------------------------------
And Eu informo o ponto de referencia PJ AVENIDA MARCIANO DE VILA
And Eu clico botao ok da tela Crediario Proprio PJ

!-- ----------------------------------------------
!-- TELA CADASTRO CLIENTE
!-- ----------------------------------------------
And Eu clico no botao Salvar cliente
And Eu espero 10000 milisegundos
Then Eu valido a mensagem de sucesso do cadastro do cliente