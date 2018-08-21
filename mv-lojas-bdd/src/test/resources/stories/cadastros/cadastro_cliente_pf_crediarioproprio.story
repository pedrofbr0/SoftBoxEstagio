Narrative:
Como um usuário vendedor da loja
Eu quero cadastrar um novo cliente Pessoa Física, perfil crediario proprio
De modo que o cliente possa ser utilizado na tela de pedido

Scenario: cadastrar um novo cliente Pessoa Física, perfil crediario proprio
Meta:
@tag component:Regressao - RELOH
@context CADASTROS
@base RELOH
@grupoInstancias Pedidos
@issue cad-16
@cad-16

!-- ----------------------------------------------
!-- TELA DE LOGIN
!-- ----------------------------------------------
Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1

!-- ----------------------------------------------
!-- TELA CONSULTA CLIENTE
!-- --------------------------------------------
And Eu informo um novo CPF
And Eu espero 5000 milisegundos
And Eu clico no botao Novo Cliente

!-- ----------------------------------------------
!-- TELA CADASTRO CLIENTE
!-- ----------------------------------------------
And Eu informo o nome do cliente Rogerinho do Ingá
And Eu seleciono o grupo CREDIARIO PROPRIO
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
And Eu clico aba de Identificação/Trabalho

!-- ----------------------------------------------
!-- TELA IDENTIFICAÇÃO/TRABALHO
!-- ----------------------------------------------
And Eu seleciono o orgao emissor SSP - SEC SEGURANCA PUBLICA
And Eu seleciono a UF MG
And Eu informo o RG 44.123.345
And Eu seleciono data de emissao 05/06/2005

And Eu informo o nome do pai Baiano
And Eu informo o nome da mae Maria de Jesus
And Eu seleciono data de nascimento 18/11/1986
And Eu seleciono sexo Masculino
And Eu seleciono nacionalidade BRASILEIRO
And Eu seleciono naturalidade BA BONITO
And Eu seleciono Estado Civil SOLTEIRO(A)

And Eu seleciono ocupacao ASSALARIADO
And Eu seleciono profissao BABÁ
And Eu seleciono tipo de comprovante de renda Extrato Bancario
And Eu seleciono mes/ano do comprovante de renda 08/2018
And Eu informo renda liquida 1500
And Na tela de Identificao/Trabalho, eu informo o CEP 38400-752
And Eu seleciono trabalha desde 04/2010
And Eu informo local de trabalho PADARIA
And Eu informo numero do local de trabalho 345
And Eu informo numero de telefone do trabalho 34 32445566 1
And Eu clico no botao ok da tela Identificacao/Trabalho

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
!-- TELA CREDIARIO PROPRIO
!-- ----------------------------------------------
And Eu clico aba Crediario Proprio
And Eu seleciono grau de escolaridade SUPERIOR
And Eu informo numero da carteira de trabalho 67374703508
And Eu seleciono tipo de telefone residencial PROPRIO
And Eu informo serie da carteira de trabalho 234566
And Eu informo ponto de referencia AVENIDA MARCIANO DE VILA
And Eu clico botao ok da tela Crediario Proprio

!-- ----------------------------------------------
!-- TELA CADASTRO CLIENTE
!-- ----------------------------------------------
And Eu clico no botao Salvar cliente
Then Eu valido a mensagem de sucesso do cadastro do cliente